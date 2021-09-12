CREATE TABLE public.books_authors (
  book_id INTEGER,
  author_id INTEGER,
  CONSTRAINT fk_author_id FOREIGN KEY (author_id)
    REFERENCES public.authors(id),
  CONSTRAINT fk_book_id FOREIGN KEY (book_id)
    REFERENCES public.books(id)
)
WITH (oids = false);