# --- Person schema

# --- !Ups

CREATE TABLE person (
    id bigserial NOT NULL,
    email varchar(255),
    CONSTRAINT person_pkey PRIMARY KEY (id)

);

# --- !Downs

DROP TABLE person;
