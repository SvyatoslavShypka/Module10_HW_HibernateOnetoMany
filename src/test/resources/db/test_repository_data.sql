-- Create the client table
CREATE TABLE IF NOT EXISTS client (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) CHECK(length(NAME) >= 3 and length(NAME) <= 200)
);



-- Create the planet table
CREATE TABLE IF NOT EXISTS planet (
  id VARCHAR(50) NOT NULL CHECK(id = UPPER(id)) PRIMARY KEY,
  name VARCHAR(500) NOT NULL UNIQUE CHECK(length(name) >= 1 and length(name) <= 500)
);



-- Create the ticket table
CREATE TABLE IF NOT EXISTS ticket (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      created_at TIMESTAMP,
                      client_id BIGINT,
                      from_planet_id VARCHAR(50),
                      to_planet_id VARCHAR(50),
                      FOREIGN KEY (client_id) REFERENCES client(id),
                      FOREIGN KEY (from_planet_id) REFERENCES planet(id),
                      FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);

DELETE FROM ticket;
DELETE FROM client;
DELETE FROM planet;

-- insert statements for Table 1: client
INSERT INTO client (name) VALUES
  ('Client1'),
  ('Client2'),
  ('Client3'),
  ('Client4'),
  ('Client5'),
  ('Client6'),
  ('Client7'),
  ('Client8'),
  ('Client9'),
  ('Client10')
;


-- insert statements for Table 2: planet
INSERT INTO planet (id, name) VALUES
  ('SOLAR001', 'Mars'),
  ('SOLAR002', 'Earth'),
  ('SOLAR003', 'Jupiter'),
  ('SOLAR004', 'Mercury'),
  ('SOLAR005', 'Neptune')
;


-- insert statements for Table 3: tickets
INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
       ('2029-09-25 16:28:45', 4, 'SOLAR001', 'SOLAR004'),
       ('2029-09-26 16:28:45', 2, 'SOLAR004', 'SOLAR001'),
       ('2029-09-27 16:28:45', 3, 'SOLAR002', 'SOLAR005')
;

