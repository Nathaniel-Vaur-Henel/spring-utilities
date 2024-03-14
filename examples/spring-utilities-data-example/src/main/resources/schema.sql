CREATE TABLE person (
  id bigint auto_increment,
  first_name VARCHAR NOT NULL,
  last_name VARCHAR,
  email VARCHAR,
  age bigint
);

CREATE TABLE item (
  id bigint auto_increment,
  name VARCHAR NOT NULL,
  owner_id bigint --,
--        FOREIGN KEY(person_id)        REFERENCES person(id)
);

