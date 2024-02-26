package net.javaguides.userservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
