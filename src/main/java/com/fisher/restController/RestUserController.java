package com.fisher.restController;

import com.fisher.exceptions.UserNotFoundException;
import com.fisher.domain.User;
import com.fisher.dto.UserDTO;
import com.fisher.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestUserController {

    private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);

    @Autowired
    private final UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> showAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/DTO")
    public ResponseEntity<?> showAllUsersDTO(){
        List<UserDTO> usersDTO = userService.getAllUsersDTO();
        if (usersDTO.isEmpty()){
            logger.error("List of usersDTO is empty");
            throw new UserNotFoundException("List of usersDTO is empty");
        }
        return  new ResponseEntity<>(usersDTO,HttpStatus.OK);
    }

    @GetMapping("users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String email){
        User user = userService.findUserByEmail(email);
        if (user == null) {
            logger.error("User with email {} not found", email);
            throw new UserNotFoundException("User not found");
        }
        logger.info("Return User with email: {}", email);

        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
        User user = userService.findUserById(id);
        if (user == null) {
            logger.error("User with id {} not found", id);
            throw new UserNotFoundException("User not found");
        }
        logger.info("Return Client with id: {}", id);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/saveUsers")
    public void saveOrUpdateUser(@RequestBody User user){
       userService.saveOrUpadteUser(user);
    }

    @DeleteMapping("users/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id){
        if(userService.deleteUserById(id) > 0){
            logger.info("Delete User with id: {}", id);
            return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
        }
        logger.info("User with id {} not exists", id);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //todo change REST API
}
