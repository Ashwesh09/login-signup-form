package com.app.loginsignup.sampletest;

import com.app.loginsignup.models.User;
import com.app.loginsignup.repositories.RegistrationRepository;
import com.app.loginsignup.services.RegistrationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestRegistrationService {

    @InjectMocks
    RegistrationService registrationService;

    @Mock
    RegistrationRepository registrationRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getEmployeeByIdTest()
    {
        when(registrationRepository.findById(1)).thenReturn(Optional.of(new User(1, "abc@gmail.com", "abc", "abcde")));

        User user = registrationService.getUserData(1);

        assertEquals("abc@gmail.com", user.getEmailID());
        assertEquals("abcde", user.getPassword());
        assertEquals("abc", user.getUsername());
    }

    @Test
    public void createUserTest()
    {
        User user = new User(2,"ashwesh@gmail.com","ashwesh","ashwesh");

        registrationService.saveUser(user);

        verify(registrationRepository, times(1)).save(user);
    }
}
