package com.redck.restaurantmsbff.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redck.restaurantmsbff.domain.User;

import java.io.Serializable;

/**
 * User class.
 */

public class UserDTO implements Serializable {

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;


    /**
     * User Get uid.
     * @return user uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * User Set uid.
     * @param uid user uid;
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * Builder UserDTO for uid.
     * @param uid uid to build.
     * @return UserDTO with uid.
     */
    public UserDTO uid(final String uid){
        this.uid = uid;
        return this;
    }


    /**
     * User get Username.
     * @return user name.
     */
    public String getUsername() {
        return username;
    }

    /**
     * User set username.
     * @param username username.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Builder for username.
     * @param username username.
     * @return user username.
     */
    public UserDTO username(final String username){
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
    public UserDTO picture(final String picture)
    {
        this.picture = picture;
        return this;
    }

    /**
     * User get Name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * User set Name.
     * @param name name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder for name.
     * @param name name.
     * @return user name.
     */
    public UserDTO name(final String name){
        this.name = name;
        return this;
    }

    /**
     * User get email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * User set email.
     * @param email email.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Builder for email.
     * @param email email.
     * @return user email.
     */
    public UserDTO email(final String email){
        this.email = email;
        return this;
    }

    /**
     * User get Password.
     * @return user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * User set password.
     * @param password password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Builder for password.
     * @param password password.
     * @return user password.
     */
    public UserDTO password(final String password){
        this.password = password;
        return this;
    }
}