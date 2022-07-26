INSERT INTO users (login, password) VALUES ('user1', '12345');
INSERT INTO users (login, password) VALUES ('user2', '12345');
INSERT INTO users (login, password) VALUES ('user3', '12345');
INSERT INTO users (login, password) VALUES ('user4', '12345');
INSERT INTO users (login, password) VALUES ('user5', '12345');


INSERT INTO chatroom (chat_owner, chat_name) VALUES (1, 'chat1');
INSERT INTO chatroom (chat_owner, chat_name) VALUES (2, 'chat2');
INSERT INTO chatroom (chat_owner, chat_name) VALUES (3, 'chat3');
INSERT INTO chatroom (chat_owner, chat_name) VALUES (4, 'chat4');
INSERT INTO chatroom (chat_owner, chat_name) VALUES (5, 'chat5');

INSERT INTO messages (room_id, author_id, message, time) VALUES (4, 1, 'message to room 4', '2016-06-22 19:10:25-07');
INSERT INTO messages (room_id, author_id, message, time) VALUES (5, 3, 'message to room 5', '2017-06-22 19:10:25-07');
INSERT INTO messages (room_id, author_id, message, time) VALUES (3, 5, 'message to room 3', '2018-06-22 19:10:25-07');
INSERT INTO messages (room_id, author_id, message, time) VALUES (2, 4, 'message to room 2', '2019-06-22 19:10:25-07');
INSERT INTO messages (room_id, author_id, message, time) VALUES (1, 2, 'message to room 1', '2020-06-22 19:10:25-07');
