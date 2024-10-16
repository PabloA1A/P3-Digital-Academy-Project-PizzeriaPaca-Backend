package org.factoriaf5.pizzeriapaca.users;

import java.util.Set;

public class UserDto {

    private String username;
    private String password; 
    private Set<String> roles;
    private String email;

    public UserDto() {
    }

    public UserDto(String username, String password, Set<String> roles, String email) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;  
    }

    public UserDto(String username, Set<String> roles, String email) {
        this.username = username;
        this.roles = roles;
        this.email = email;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {  
        return email;
    }

    public void setEmail(String email) {  
        this.email = email;
    }
}
