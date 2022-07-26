package edu.school21.chat.models;

import java.time.LocalDateTime;

public class Message {

    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;


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
        return "Message{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", room='" + room + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
