# --- Person schema

# --- !Ups

CREATE TABLE person (
    id bigserial NOT NULL,
    firstname varchar(255),
    lastname varchar(255),
    email varchar(255)
);

# --- !Downs

DROP TABLE person;
