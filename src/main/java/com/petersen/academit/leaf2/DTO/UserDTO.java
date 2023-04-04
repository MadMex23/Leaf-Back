package com.petersen.academit.leaf2.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String name;
    private String lastName;
    private String email;
    private String password;

}
