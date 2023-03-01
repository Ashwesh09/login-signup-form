package com.app.loginsignup.repositories;

import com.app.loginsignup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<User, Integer> {

    public User findByEmailID(String emailId);
    public User findByEmailIDAndPassword(String emailId, String password);
}
