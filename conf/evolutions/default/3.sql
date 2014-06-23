# --- Contact schema

# --- !Ups

CREATE TABLE contact (
    id bigserial NOT NULL,
    firstname varchar(255),
    lastname varchar(255),
    phone varchar(255),
    email varchar(255),
    comments varchar(500),
    CONSTRAINT contact_pkey PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE contact;