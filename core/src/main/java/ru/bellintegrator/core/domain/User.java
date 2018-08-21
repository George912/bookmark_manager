package ru.bellintegrator.core.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Пользователь.
 * Таблица: users
 */
@Entity
@Table(schema = "bookmark_manager_schema", name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "USERS_SEQ"
    )
    @SequenceGenerator(
            name = "USERS_SEQ"
            , sequenceName = "USERS_SEQ"
            , allocationSize = 1
            , schema = "BOOKMARK_MANAGER_SCHEMA"
    )
    private Short id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    public User() {
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
