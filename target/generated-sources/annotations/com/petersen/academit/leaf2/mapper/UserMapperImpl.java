package com.petersen.academit.leaf2.mapper;

import com.petersen.academit.leaf2.DTO.UserDTO;
import com.petersen.academit.leaf2.DTO.UserLoginDTO;
import com.petersen.academit.leaf2.entity.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-26T23:00:03-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO entityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }

    @Override
    public UserLoginDTO entityToLoginDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO();

        userLoginDTO.setId( user.getId() );
        userLoginDTO.setName( user.getName() );
        userLoginDTO.setLastName( user.getLastName() );

        return userLoginDTO;
    }

    @Override
    public User dtoToEntity(UserDTO usuario) {
        if ( usuario == null ) {
            return null;
        }

        User user = new User();

        user.setId( usuario.getId() );
        user.setName( usuario.getName() );
        user.setLastName( usuario.getLastName() );
        user.setEmail( usuario.getEmail() );
        user.setPassword( usuario.getPassword() );

        return user;
    }
}
