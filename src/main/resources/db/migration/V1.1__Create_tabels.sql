
create TABLE labels(
    id  SERIAL PRIMARY KEY ,
    name VARCHAR(50)
       );

 create TABLE writers(
    id  SERIAL PRIMARY KEY ,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
       );


create TABLE posts(
    id  SERIAL PRIMARY KEY ,
    content VARCHAR(50),
    created BIGINT,
    updated BIGINT,
    status VARCHAR(50),
    writer_id INT,
    FOREIGN KEY (writer_id)  REFERENCES writers (id)
    );

create TABLE posts_labels(
post_id INT,
labellist_id INT,
   FOREIGN KEY (post_id)  REFERENCES posts (id),
   FOREIGN KEY (labellist_id)  REFERENCES labels (id));

 /*  create TABLE writers_posts(
writer_id INT,
posts_id INT,
   FOREIGN KEY (writer_id)  REFERENCES writers (id),
   FOREIGN KEY (posts_id)  REFERENCES posts (id));*/
