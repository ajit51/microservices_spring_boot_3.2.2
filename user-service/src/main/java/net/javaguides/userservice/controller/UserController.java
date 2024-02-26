package net.javaguides.userservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.javaguides.userservice.dto.DepartmentDto;
import net.javaguides.userservice.dto.ResponseDto;
import net.javaguides.userservice.dto.UserDto;
import net.javaguides.userservice.entity.User;
import net.javaguides.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    int retryCount = 1;


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    //@CircuitBreaker(name = "userCircuitBreaker", fallbackMethod = "userFallback")
    //@Retry(name = "userRetryService", fallbackMethod = "userFallback")
    @RateLimiter(name = "userRateLimiterService", fallbackMethod = "userFallback")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
        logger.info("Retry count : {} " + retryCount);
        retryCount++;
        logger.info(environment.getProperty("local.server.port"));
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<ResponseDto> userFallback(Long userId, Exception e) {
        logger.info("Fallback is executed because service is down : {} " + e.getMessage());
        DepartmentDto departmentDto = DepartmentDto.builder()
                .id(11111L)
                .departmentName("Dummy Department")
                .departmentCode("Dummy code")
                .departmentAddress("Dummy Address")
                .build();

        UserDto userDto = UserDto.builder()
                .id(1000001l)
                .firstName("Dummy Name")
                .lastName("Dummy Last Name")
                .email("dummy@gmail.com")
                .build();

        ResponseDto responseDto = ResponseDto.builder()
                .department(departmentDto)
                .user(userDto)
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
