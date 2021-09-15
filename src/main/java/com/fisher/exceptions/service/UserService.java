package com.fisher.exceptions.service;

import com.fisher.repository.UserRepository;
import com.fisher.repository.UserRoleRepository;
import com.fisher.user.User;
import com.fisher.user.UserDTO;
import com.fisher.user.UserMapper;
import com.fisher.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
    }


    public void saveUserWithDeafultRole(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserRole userRole = userRoleRepository.findByName("USER");
        user.addRole(userRole);
        userRepository.save(user);

    }
    public void saveOrUpadteUser(User user){
        userRepository.findUserByEmail(user.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUser(Long id){

       return userRepository.findById(id).get();
    }
    public List<UserRole> getRoles(){
        return userRoleRepository.findAll();
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    public List<UserDTO> getAllUsersDTO(){
        List<UserDTO> usersDTO = userRepository.findAll()
                .stream()
                .map(userMapper::from)
                .collect(Collectors.toList());
        return  usersDTO;
    }

    @Transactional
    public int deleteUserById(long id) {
        return userRepository.deleteUserById(id);
    }

    public void createRole(){
        UserRole admin = new UserRole("ADMIN");
        UserRole user = new UserRole("USER");
        userRoleRepository.saveAll(List.of(admin,user));

    }

}
