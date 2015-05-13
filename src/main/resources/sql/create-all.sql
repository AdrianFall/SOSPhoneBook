DROP TABLE role;
DROP TABLE account;

CREATE TABLE account
(
  id serial NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  enabled boolean NOT NULL DEFAULT TRUE,
  CONSTRAINT account_pkey PRIMARY KEY (id)
);

CREATE TABLE role
(
  id serial NOT NULL,
  account_id bigint NOT NULL,
  role character varying(50) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id),
  CONSTRAINT role_fkey FOREIGN KEY (account_id) REFERENCES account (id)
);

INSERT INTO account(email, password)
VALUES ('jose@jo.se', '$2a$10$kMVhUDUeDSdZOeCOboXzXOwy9f4VXvIp2yE0OEHIWA.BTlaX6.vpq');
INSERT INTO account(email, password)
VALUES ('adrianq92@hotmail.com', '$2a$10$AK1rKs1jY0W0qjACmoDioO7gzCzJIxAfXDBgOi0gfyYaf.adw8m7y');

INSERT INTO role(account_id, role)
VALUES (1, 'ROLE_USER');
INSERT INTO role(account_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO role(account_id, role)
VALUES (2, 'ROLE_USER');