package com.staslabs.xstas.web;

import com.staslabs.xstas.data.entity.User;
import com.staslabs.xstas.data.UserRepository;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {

    @Test
    public void shouldProcessRegistration() throws Exception {
        User user = new User("John", "Snow", "bastard@spittr.com", "bastard", "123");

        UserRepository repository = mock(UserRepository.class);
        when(repository.getByEmail("bastard")).thenReturn(user);

        UserController controller = new UserController(repository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/user/register")
                .param("firstName", "John")
                .param("lastName", "Snow")
                .param("username", "bastard")
                .param("password", "123"))
                .andExpect(redirectedUrl("/user/profile/bastard"));

        verify(repository, atLeastOnce()).save(user);
    }

}