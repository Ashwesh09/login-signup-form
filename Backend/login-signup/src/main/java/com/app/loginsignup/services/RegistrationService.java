package com.app.loginsignup.services;

import com.app.loginsignup.models.User;
import com.app.loginsignup.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    public User saveUser(User user){
        return registrationRepository.save(user);
    }

    public User featchUserByEmailId(String email){
        return registrationRepository.findByEmailID(email);
    }

    public User findByEmailIDAndPassword(String email, String password){
        return registrationRepository.findByEmailIDAndPassword(email,password);
    }

    public User getUserData(Integer id){
        return registrationRepository.findById(id).get();
    }

    public void deleteUser(Integer id){
        registrationRepository.delete(getUserData(id));
    }

    public List<User> getAllUserFromDb(){
        return registrationRepository.findAll();
    }
}
