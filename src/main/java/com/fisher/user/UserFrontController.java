package com.fisher.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserFrontController {

    @Autowired
    private final UserService userService;

    public UserFrontController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String viewHomePage(){
        return "index";

    }

    @GetMapping("addRole")
    public String addRoles( Model model){
        model.addAttribute("userrole",new UserRole());
        return "roles";
    }

    @PostMapping("/saveRole")
    public String get(){
        userService.createRole();
        return "savedRole";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user",new User());
        return "sign_up_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        userService.saveUserWithDeafultRole(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/edit{id}")
    public String editUser(@PathVariable("id") Long id, Model model){
        User user = userService.getUser(id);
        List<UserRole> rolesList = userService.getRoles();

        model.addAttribute("user",user);
        model.addAttribute("rolesList",rolesList);
        return "user_form";

    }
    @PostMapping ("/user/save")
    public String saveUser(User user){
        userService.saveOrUpadteUser(user);
        return "redirect:/list_users";
    }

}
