package com.staslabs.xstas.data;

import com.staslabs.xstas.data.entity.User;

public interface UserRepository {
    void save(User user);

    User getByUsername(String username);
}
