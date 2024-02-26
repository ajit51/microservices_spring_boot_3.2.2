package net.javaguides.userservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.userservice.dto.DepartmentDto;
import net.javaguides.userservice.dto.ResponseDto;
import net.javaguides.userservice.dto.UserDto;
import net.javaguides.userservice.entity.User;
import net.javaguides.userservice.repository.UserRepository;
import net.javaguides.userservice.service.APIClient;
import net.javaguides.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    //private RestTemplate restTemplate;
    //private WebClient webClient;
    private APIClient apiClient;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();

        //ResponseEntity<DepartmentDto> responseEntity = restTemplate
        //      .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId(), DepartmentDto.class);
        // DepartmentDto departmentDto = responseEntity.getBody();
        // logger.info("StatusCode>>>>" + responseEntity.getStatusCode());

     /*   DepartmentDto departmentDto = webClient
                .get()
                .uri("http://localhost:8080/api/departments/" + user.getDepartmentId())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();*/
        DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());

        UserDto userDto = mapToUser(user);
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
