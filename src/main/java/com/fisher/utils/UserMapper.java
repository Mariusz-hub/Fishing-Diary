package com.fisher.utils;

import com.fisher.domain.User;
import com.fisher.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DTOMapper <User, UserDTO>{

    private final static Logger logger = LoggerFactory.getLogger(UserMapper.class);

    @Override
    public UserDTO from (User from) {
        UserDTO userDTO = new UserDTO.Builder()
                .email(from.getEmail())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .phoneNumber(from.getPhoneNumber())
                .registrationDate(from.getRegistrationDate())
                .enabled(from.isEnabled())
                .build();
        logger.info("Created UserDTO: {}", userDTO.toString());
        return  userDTO;
    }
}
