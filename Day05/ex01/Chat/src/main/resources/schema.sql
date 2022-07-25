DROP TABLE IF EXISTS messages, chatroom, users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY ,
    login TEXT NOT NULL ,
    password TEXT
);

CREATE TABLE IF NOT EXISTS chatroom (
    id SERIAL PRIMARY KEY ,
    chat_owner INT NOT NULL REFERENCES users(id) ,
    chat_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    id SERIAL PRIMARY KEY ,
    author_id INT NOT NULL REFERENCES users(id) ,
    room_id INT NOT NULL REFERENCES chatroom(id) ,
    message TEXT NOT NULL ,
    time TIMESTAMP
);