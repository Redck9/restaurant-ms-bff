package com.redck.restaurantmsbff.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
//@Table(name = "clients")
public class Client
{

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "uid", unique = true)
    private String uid;

    @NotBlank(message = "Username is required")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "role")
    private String role;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Column(name = "refreshToken", length = 2048)
    private String refreshToken;

    @Column(name = "favoriteRestaurants")
    private List<String> favoriteRestaurants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Builder User for id.
     * @param id id to build.
     * @return user with id.
     */
    public Client id(final long id){
        this.id = id;
        return this;
    }

    /**
     * User Get uid
     * @return uid
     */
    public String getUid()
    {
        return uid;
    }

    /**
     * User Set uid
     * @param uid user uid
     */
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    /**
     * Builder for uid
     * @param uid uid
     * @return user uid
     */
    public Client uid(final String uid)
    {
        this.uid = uid;
        return this;
    }

    /**
     * User Get role
     * @return role
     */
    public String getRole()
    {
        return role;
    }

    /**
     * User Set role
     * @param role user role
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * Builder for role
     * @param role role
     * @return user role
     */
    public Client role(final String role)
    {
        this.role = role;
        return this;
    }

    /**
     * User Get username
     * @return username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * User Set username
     * @param username user username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Builder for username
     * @param username username
     * @return user username
     */
    public Client username(final String username)
    {
        this.username = username;
        return this;
    }

    /**
     * User Get picture
     * @return picture
     */
    public String getPicture()
    {
        return picture;
    }

    /**
     * User Set picture
     * @param picture user picture
     */
    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    /**
     * Builder for picture
     * @param picture picture
     * @return user picture
     */
    public Client picture(final String picture)
    {
        this.picture = picture;
        return this;
    }

    /**
     * User Get name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * User Set name
     * @param name user name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Builder for name
     * @param name name
     * @return user name
     */
    public Client name(final String name)
    {
        this.name = name;
        return this;
    }

    /**
     * User Get email
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * User Set email
     * @param email user email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Builder for email
     * @param email email
     * @return user email
     */
    public Client email(final String email)
    {
        this.email = email;
        return this;
    }

    /**
     * User get password
     * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * User Set password
     * @param password user password
     */
    public void setPassword(String password)
    {
        if (!password.startsWith("$2a$")) // Check if password is already encoded
        {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(password);
        }
        else
        {
            this.password = password;
        }
    }

    /**
     * Builder for password
     * @param password password
     * @return user password
     */
    public Client password(final String password)
    {
        this.password = password;
        return this;
    }

    // Getters and setters
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Add a fluent setter for chaining
    public Client refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    /**
     * User get list of favorite restaurants
     * @return list of favorite restaurants
     */
    public List<String> getFavoriteRestaurants()
    {
        return favoriteRestaurants;
    }

    /**
     * User Set favorite restaurants
     * @param favoriteRestaurants favorite restaurants
     */
    public void setFavoriteRestaurants(List<String> favoriteRestaurants)
    {
        this.favoriteRestaurants = favoriteRestaurants;
    }

    /**
     * Builder for favorite restaurants
     * @param favoriteRestaurants favorite restaurants
     * @return favorite restaurants
     */
    public Client favoriteRestaurants(List<String> favoriteRestaurants)
    {
        this.favoriteRestaurants = favoriteRestaurants;
        return this;
    }


    @Override
    public String toString()
    {
        return "Client {" +
                "id = " + id +
                ", username = " + username +
                ", role = " + role +
                ", email = " + email +
                ", password = " + password +
                ", name = " + name +
                ", picture = " + picture +
                ", refresh token = " + refreshToken +
                " }";
    }
}
