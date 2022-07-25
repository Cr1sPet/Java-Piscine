package edu.school21.chat.models;

import java.time.LocalDateTime;

public class Message {

    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        return "Message{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", room='" + room + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
