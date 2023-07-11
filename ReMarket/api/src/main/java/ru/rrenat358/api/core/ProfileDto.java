package ru.rrenat358.api.core;

public class ProfileDto {
    private String username;
//    private String password;
//    private String email;

//    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileDto() {
    }

    public ProfileDto(String username) {
        this.username = username;
    }
}
