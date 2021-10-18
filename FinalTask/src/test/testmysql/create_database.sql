CREATE DATABASE document_handle;

CREATE USER 'test'@'%' IDENTIFIED BY 'pass';

GRANT SELECT, UPDATE, DELETE, INSERT
    ON document_handle.* TO 'test'@'%';