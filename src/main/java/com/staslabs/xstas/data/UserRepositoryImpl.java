package com.staslabs.xstas.data;

import com.staslabs.xstas.data.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("John", "Snow", "user@xstas.com", "user", "password"));
        users.add(new User("Bruce", "Lee", "admin@xstas.com", "admin", "password"));
    }

    @Override
    public void save(User user) {
        users.add(user);

    }

    @Override
    public User getByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
