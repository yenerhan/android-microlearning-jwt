package com.microlearning.model.user;

public class LoginUserRequestDTO {

    private String email;
    private String password;

    public LoginUserRequestDTO() {
    }

    public LoginUserRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
