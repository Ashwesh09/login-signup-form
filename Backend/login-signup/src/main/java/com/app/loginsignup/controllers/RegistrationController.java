package com.app.loginsignup.controllers;

import com.app.loginsignup.models.User;
import com.app.loginsignup.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class RegistrationController {

    @GetMapping
    public String welcomeUser(){
        return "<h1>Hi Ashwesh! Its working.";
    }
    @Autowired
    private RegistrationService registrationService;
    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user) throws Exception {
        if(!user.getEmailID().equals("") && user.getEmailID() != null){
            User userObj = registrationService.featchUserByEmailId(user.getEmailID());
            if(userObj != null){
                throw new Exception("User with Email : "+user.getEmailID()+" already exists");
            }
        }
        return registrationService.saveUser(user);
    }
    @PostMapping("/loginUser")
    public User loginUser (@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailID();
        String password = user.getPassword();
        User userObj = null;

        if (tempEmailId != null && password != null) {
            userObj = registrationService.findByEmailIDAndPassword(tempEmailId, password);
        }
        if(userObj == null){
            throw new Exception("Bad credentials (User with email or password does not exist, try registration)");
        }
        return userObj;
    }

    @GetMapping("/getUser/{id}")
    public User getUserData(@PathVariable Integer id){
        return registrationService.getUserData(id);
    }

    @DeleteMapping("/getUser/{id}")
    public Boolean deleteUser(@PathVariable Integer id){
        try {
            registrationService.deleteUser(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return registrationService.getAllUserFromDb();
    }
}