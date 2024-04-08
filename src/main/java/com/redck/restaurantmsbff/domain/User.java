package com.redck.restaurantmsbff.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User
{

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "uid", unique = true)
    private String uid;


    @Column(name = "username", unique = true)
    private String username;


    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

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
    public User id(final long id){
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
    public User uid(final String uid)
    {
        this.uid = uid;
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
    public User username(final String username)
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
    public User picture(final String picture)
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
    public User name(final String name)
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
    public User email(final String email)
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
    public User password(final String password)
    {
        this.password = password;
        return this;
    }

    @Override
    public String toString()
    {
        return "User {" +
                "id = " + id +
                ", username = " + username +
                ", email = " + email +
                ", password = " + password +
                ", name = " + name +
                ", picture = " + picture +
                " }";
    }
}
