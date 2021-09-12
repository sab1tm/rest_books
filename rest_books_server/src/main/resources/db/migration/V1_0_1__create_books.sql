CREATE TABLE public.books (
  id SERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  published VARCHAR(4),
  PRIMARY KEY(id)
)
WITH (oids = false);