
 create TABLE files(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50),
    filePath VARCHAR(150)
       );


create TABLE users(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50)
    );

create TABLE events(
user_id INT,
file_id INT,
   FOREIGN KEY (user_id)  REFERENCES users (id),
   FOREIGN KEY (file_id)  REFERENCES files (id)
   );

 /*  create TABLE writers_posts(
writer_id INT,
posts_id INT,
   FOREIGN KEY (writer_id)  REFERENCES writers (id),
   FOREIGN KEY (posts_id)  REFERENCES posts (id));*/
