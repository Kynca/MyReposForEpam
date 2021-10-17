CREATE DATABASE document_handler;

CREATE USER 'app'@'%' IDENTIFIED BY 'pass';

GRANT SELECT, UPDATE, DELETE, INSERT
    ON document_handler.* TO 'app'@'%';




