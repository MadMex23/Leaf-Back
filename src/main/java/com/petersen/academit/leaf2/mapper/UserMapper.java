package com.petersen.academit.leaf2.mapper;


import com.petersen.academit.leaf2.DTO.AccountDTO;
import com.petersen.academit.leaf2.DTO.UserDTO;
import com.petersen.academit.leaf2.DTO.UserLoginDTO;
import com.petersen.academit.leaf2.entity.Account;
import com.petersen.academit.leaf2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface UserMapper {

        UserDTO entityToDTO(User user);

        UserLoginDTO entityToLoginDTO(User user);
        User dtoToEntity(UserDTO usuario);

}
