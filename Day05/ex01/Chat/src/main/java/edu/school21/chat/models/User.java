package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private String password;
    private List<Chatroom> chatrooms;
    private List<Chatroom> activeChatrooms;


    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(List<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }

    public List<Chatroom> getActiveChatrooms() {
        return activeChatrooms;
    }

    public void setActiveChatrooms(List<Chatroom> activeChatrooms) {
        this.activeChatrooms = activeChatrooms;
    }

    public User(Long id, String login, String password, List<Chatroom> chatrooms, List<Chatroom> activeChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.chatrooms = chatrooms;
        this.activeChatrooms = activeChatrooms;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", chatrooms=" + chatrooms +
                ", activeChatrooms=" + activeChatrooms +
                '}';
    }
}
