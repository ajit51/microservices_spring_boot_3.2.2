package net.javaguides.userservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
