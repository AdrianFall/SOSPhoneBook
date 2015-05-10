CREATE TABLE app_account
(
  id serial NOT NULL,
  username text,
  password text,
  email text,
  CONSTRAINT pk_account PRIMARY KEY (id)
)