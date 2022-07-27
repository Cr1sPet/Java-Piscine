package edu.school21.models;

public class User {

    private Integer id;
    private String login;
    private String password;
    private Boolean authSuccess;

    public User(Integer id, String login, String password, Boolean authSuccess) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authSuccess = authSuccess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAuthSuccess() {
        return authSuccess;
    }

    public void setAuthSuccess(Boolean authSuccess) {
        this.authSuccess = authSuccess;
    }
}
