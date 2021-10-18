USE document_handle;

INSERT INTO program_user (id, login, password, role)
VALUES (1, 'user1', 'password1', 0),
       (2, 'user2', 'password2', 1),
       (3, 'user3', 'password3', 1),
       (4, 'user4', 'password4', 1),
       (5, 'user5', 'password5', 1),
       (6, 'user6', 'password6', 1),
       (7, 'user7', 'password7', 1),
       (8, 'user8', 'password8', 2),
       (9, 'user9', 'password9', 2),
       (10, 'user10', 'password10', 1),
       (11, 'user11', 'password11', 1),
       (12, 'user12', 'password12', 2),
       (13, 'user13', 'password13', 2),
       (14, 'user14', 'password14', 1),
       (15, 'user15', 'password15', 1),
       (16, 'user16', 'password15', 1),
       (17, 'user17', 'password16', 1),
       (18, 'user18', 'password17', 1),
       (19, 'user19', 'password18', 1),
       (20, 'user20', 'password19', 1),
       (21, 'user21', 'password20', 1);

INSERT INTO university (id, name)
VALUES (1, 'BSUIR'),
       (2, 'BNTU'),
       (3, 'BSEU'),
       (4, 'BSU');

INSERT INTO dean (id, faculty, address, phone_number, university_id)
VALUES (1, 'IEF', 'Centralnaya10-214', 375292223001, 4),
       (2, 'EF', 'Platnov37-131', 375773600222, 1),
       (3, 'TRUN', 'Platnov36-917', 375773600221, 1),
       (4, 'POIM', 'Akademich12-210', 375172200221, 2),
       (5, 'SLON', 'Akademoka1-101', 375171233456, 1),
       (6, 'KSIS', 'Neponyat-213', 375297000221, 3),
       (7, 'POIT', 'Virskaya3-45', 37517363461, 4),
       (8, 'IIF', 'Naukova21-61', 375253610221, 1),
       (9, 'KROM', 'Centralnaya10-312', 375253600224, 4),
       (10, 'SMOl', 'Bseuul6-206', 375173617221, 3);

INSERT INTO student (user_id, name, lastname, patronymic, birthdate, mail, dean_id)
VALUES (1, 'Ilya', 'Ilyavich', 'Ilich', '2001-06-06', 'mail@mail.ru', NULL),
       (2, 'Matwey', 'Gricenko', 'Vladimirovich', '2002-03-29', 'matwey@mail.ru', 1),
       (3, 'Nikolay', 'Karach', 'Nikolaevich', '2002-10-08', 'karachun@mail.ru', 1),
       (4, 'Ilya', 'Molich', 'Ivanov', '2003-02-08', 'ilya2003@mail.ru', 2),
       (5, 'Ksenia', 'Minova', 'Alexandrova', '2002-07-25', 'clavino@mail.ru', 3),
       (6, 'Ekaterina', 'Silova', 'Dmitrievna', '2000-12-09', 'yakatka@gmail.com', 5),
       (7, 'Alrifai', 'Marcel', 'Klivochich', '2000-01-10', 'yaaaaaz@gmail.com', 2),
       (8, 'Andrew', 'Scala', 'Eduardovich', '2001-11-03', 'nagibator2001@gmail.com', 4),
       (9, 'Valeria', 'Komonova', 'Sergeyevna', '2001-05-04', 'talantmira@mail.ru', 3),
       (10, 'Kirill', 'Duba', 'Maksimov', '2002-07-07', 'mirsim@mail.ru', 3),
       (11, 'Evdokiy', 'Usov', 'Eduardovich', '2001-05-04', 'kirimka01@mail.ru', 5),
       (12, 'Luba', 'Lubika', 'Nikolaevna', '2001-09-06', 'katevna@gmail.com', 6),
       (13, 'Andrew', 'Somov', 'Ivanovich', '2002-12-16', 'somovandreqw@mail.ru', 7),
       (14, 'Timofey', 'Smuglov', 'Vladimirovich', '2003-01-11', 'morov@mail.ru', 8),
       (15, 'Simka', 'Vintick', 'Papusovna', '1999-01-01', 'simka@gmail.com', 9),
       (16, 'Kirill', 'Takov', 'Vladimirovich', '2001-04-12', 'kimokov@mail.ru', 10),
       (17, 'Yana', 'Clavova', 'Andreewa', '2002-09-21', ',yanaKol@mail.ru', 5),
       (18, 'Alexandr', 'Psigotskiy', 'Alexandrowich', '2002-07-25', 'pshiga@mail.ru', 4),
       (19, 'Alina', 'Frikon', 'Michailovna', '2001-05-11', ',mikulca@mail.ru', 4),
       (20, 'Solomon', 'Car', 'Solomonovich', '2001-08-19', ',solomomsolomon@gmail.com', 8);

INSERT INTO document_type(id, type)
VALUES (1, 'Students earnings'),
       (2, 'blood donation'),
       (3, 'form of education'),
       (4, 'progress certificate'),
       (5, 'on work'),
       (6, 'in bank');

INSERT INTO document(id, order_date, type_id, status, delivery_type, comment, document, student_id, receiver_name,
                     receiver_mail)
VALUES (1, '2021-09-08', 2, true, 1, null, '/document.txt', 2, null, null),
       (2, '2021-09-08', 3, false, 2, 'no comment', null, 2, 'receiver name', null),
       (3, '2021-09-01', 2, true, 1, null, '/document.txt', 3, null, null),
       (4, '2021-09-02', 2, true, 1, null, '/document.txt', 6, null, null);

INSERT INTO subject (id, name)
VALUES (1, 'Math'),
       (2, 'Physics'),
       (3, 'Sociology'),
       (4, 'Psychology'),
       (5, 'Philosophy'),
       (6, 'English'),
       (7, 'KIT'),
       (8, 'Economy'),
       (9, 'Ethics'),
       (10, 'Marketing'),
       (11, 'OAIP'),
       (12, 'OOP'),
       (13, 'MathStatistic');

INSERT INTO marks(subject_id, rate, issue_date, student_id)
VALUES (1, 8.7, '2020-01-02', 2),
       (5, 9, '2020-01-02', 2),
       (11, 9, '2020-01-03', 2),
       (2, 7, '2001-05-11', 3),
       (11, 6.5, '2001-05-11', 3);

