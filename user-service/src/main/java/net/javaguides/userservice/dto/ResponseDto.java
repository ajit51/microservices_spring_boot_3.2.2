package net.javaguides.userservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private DepartmentDto department;
    private UserDto user;
}
