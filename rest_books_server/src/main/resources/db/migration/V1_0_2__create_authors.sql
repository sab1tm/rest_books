CREATE TABLE public.authors (
  id SERIAL NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  birth_date DATE,
  sex CHAR(1),
  PRIMARY KEY(id)
)
WITH (oids = false);