CREATE TABLE person (
  id            INTEGER PRIMARY KEY,
  email         VARCHAR(50),
  password      VARCHAR(20),
  is_confirmed  BOOLEAN DEFAULT 'false'

);
