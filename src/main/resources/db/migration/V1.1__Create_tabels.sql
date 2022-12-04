
 create TABLE files(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50),
    filePath VARCHAR(150)
       );


create TABLE users(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50)
    );
/*CREATE SEQUENCE users_id_seq START WITH 1 INCREMENT BY 1;*/

create TABLE events(
id SERIAL PRIMARY KEY ,
user_id INT,
file_id INT,
   FOREIGN KEY (user_id)  REFERENCES users (id) ON DELETE CASCADE,
   FOREIGN KEY (file_id)  REFERENCES files (id) ON DELETE CASCADE
   );


