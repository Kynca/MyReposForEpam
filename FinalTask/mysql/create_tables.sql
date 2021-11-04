USE document_handler;

CREATE TABLE program_user
(
    id       INTEGER     NOT NULL AUTO_INCREMENT,
    login    VARCHAR(40) NOT NULL UNIQUE,
    password NCHAR(32)   NOT NULL,
    /*
     * 0 - admin
     * 1 - student
     * 2 - dean
     */
    role     TINYINT     NOT NULL CHECK (role IN (0, 1, 2)),
    PRIMARY KEY (id)
);

CREATE TABLE university
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    CONSTRAINT PK_university PRIMARY KEY (id)
);

CREATE TABLE dean
(
    id            INT          NOT NULL AUTO_INCREMENT,
    faculty       VARCHAR(50)  NOT NULL,
    address       VARCHAR(120) NOT NULL UNIQUE,
    phone_number  BIGINT       NOT NULL,
    university_id INT          NOT NULL,
    CONSTRAINT PK_dean PRIMARY KEY (id),
    CONSTRAINT FK_deanUniversity FOREIGN KEY (university_id)
        REFERENCES university (id)
);

CREATE TABLE student
(
    user_id    INT          NOT NULL,
    name       VARCHAR(15)  NOT NULL,
    lastname   VARCHAR(25)  NOT NULL,
    patronymic VARCHAR(20)  NOT NULL,
    birthdate  DATE         NOT NULL,
    mail       VARCHAR(322) NOT NULL UNIQUE,
    dean_id    INT,
    CONSTRAINT PK_student PRIMARY KEY (user_id),
    CONSTRAINT FK_studentUser FOREIGN KEY (user_id)
        REFERENCES program_user (id) ON DELETE CASCADE,
    CONSTRAINT FK_studentDean FOREIGN KEY (dean_id)
        REFERENCES dean (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE subject
(
    id   INT       NOT NULL,
    name NCHAR(50) NOT NULL,
    CONSTRAINT PK_subject PRIMARY KEY (id)
);

CREATE TABLE marks
(
    subject_id INT    NOT NULL,
    rate       DOUBLE NOT NULL,
    issue_date DATE   NOT NULL,
    student_id INT    NOT NULL,
    CONSTRAINT PK_marks PRIMARY KEY (subject_id, student_id),
    CONSTRAINT FK_marksStudent FOREIGN KEY (student_id)
        REFERENCES student (user_id) ON DELETE CASCADE,
    CONSTRAINT FK_marksSubject FOREIGN KEY (subject_id)
        REFERENCES subject (id)
);

CREATE TABLE document_type
(
    id   INT         NOT NULL,
    type VARCHAR(30) NOT NULL,
    CONSTRAINT PK_documentType PRIMARY KEY (id)
);

CREATE TABLE document
(
    id            INTEGER NOT NULL AUTO_INCREMENT,
    order_date    DATE    NOT NULL,
    type_id       INT     NOT NULL,
    status        BOOLEAN NOT NULL,
    delivery_type BOOLEAN NOT NULL,
    comment       TEXT,
    document      VARCHAR(322),
    student_id    INTEGER,
    receiver_name VARCHAR(60),
    receiver_mail VARCHAR(322),
    CONSTRAINT PK_document PRIMARY KEY (id),
    CONSTRAINT FK_documentClient FOREIGN KEY (student_id)
        REFERENCES student (user_id) ON DELETE CASCADE ,
    CONSTRAINT FK_documentDocument_type FOREIGN KEY (type_id)
        REFERENCES document_type (id)

);






