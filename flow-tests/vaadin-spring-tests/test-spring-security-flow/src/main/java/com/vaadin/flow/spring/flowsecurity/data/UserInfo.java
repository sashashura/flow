package com.vaadin.flow.spring.flowsecurity.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserInfo {

    private String username;
    private String encodedPassword;
    private String fullName;
    private String imageUrl;

    private List<String> roles;

    public UserInfo(String username, String encodedPassword, String fullName,
            String imageUrl, String... roles) {
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.roles = new ArrayList<>();
        Collections.addAll(this.roles, roles);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}
