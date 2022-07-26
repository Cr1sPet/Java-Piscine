package edu.school21.chat.models;

import java.util.List;

public class Chatroom {

    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

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
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", messages=" + messages +
                '}';
    }
}
