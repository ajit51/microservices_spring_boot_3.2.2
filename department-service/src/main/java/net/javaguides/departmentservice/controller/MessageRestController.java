package net.javaguides.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class MessageRestController {

    //@Value("${message:Hello default}")
    @Value("${message}")
    private String message;

    @GetMapping("/message")
    String getMessage() {
        return this.message;
    }
}
