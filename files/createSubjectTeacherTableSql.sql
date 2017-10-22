CREATE TABLE Subject_Teacher(
Subject_code VARCHAR(10) NOT NULL,
Teacher_id INT NOT NULL,
week INT NOT NULL,
classroom VARCHAR(45) NOT NULL,
PRIMARY KEY (Subject_code, Teacher_id),
INDEX Teacher_id_idx (Teacher_id ASC),
CONSTRAINT Subject_code
    FOREIGN KEY (Subject_code) REFERENCES Subject(code)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
CONSTRAINT Teacher_id
    FOREIGN KEY (Teacher_id) REFERENCES Teacher(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);