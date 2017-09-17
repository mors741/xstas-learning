package com.staslabs.xstas.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

    private static long idGen = 0;

    @Id
    @Column(name = "user_id")
    @Getter @Setter private long id;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "first_name")
    @Getter @Setter private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(name = "surname")
    @Getter @Setter private String lastName;

    @Size(min = 2, max = 30)
    @Column(name = "patronymic")
    @Getter @Setter private String patronymic;

    @Email
    @Column(name = "email")
    @Getter @Setter private String email;

    @NotNull
    @Size(min = 5, max = 25)
    @Getter @Setter private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    @Getter @Setter private Role role = Role.ROLE_STUDENT;

    public User(String firstName, String lastName, String email, String username, String password) {
        this.id = idGen++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
