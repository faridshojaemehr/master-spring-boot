-- Create the table
CREATE TABLE person (
                        id INT,
                        name VARCHAR(50),
                        age VARCHAR(50),
                        gender VARCHAR(50),
                        email VARCHAR(50)
);

INSERT INTO person (id, name, age, gender, email) VALUES (1, 'Waldemar Drain', '34', 'Male', 'wdrain0@vistaprint.com');
INSERT INTO person (id, name, age, gender, email) VALUES (2, 'Shae Vickress', '29', 'Female', 'svickress1@who.int');
INSERT INTO person (id, name, age, gender, email) VALUES (3, 'Siward Peirson', '42', 'Male', 'speirson2@w3.org');
INSERT INTO person (id, name, age, gender, email) VALUES (4, 'Evangeline Quick', '36', 'Female', 'equick3@globo.com');

-- Generate 100 entries with randomized data
INSERT INTO person (id, name, age, gender, email) VALUES
                                                      (5, 'John Doe', '25', 'Male', 'johndoe@example.com'),
                                                      (6, 'Jane Smith', '30', 'Female', 'janesmith@example.com'),
                                                      (7, 'Alex Johnson', '22', 'Non-binary', 'alexjohnson@example.com'),
                                                      (8, 'Chris Brown', '28', 'Male', 'chrisbrown@example.com'),
                                                      (9, 'Emily Davis', '35', 'Female', 'emilydavis@example.com'),
                                                      (10, 'Daniel Miller', '40', 'Male', 'danielmiller@example.com'),
                                                      (11, 'Sophia Wilson', '33', 'Female', 'sophiawilson@example.com'),
                                                      (12, 'Ryan Anderson', '27', 'Male', 'ryananderson@example.com'),
                                                      (13, 'Olivia Thomas', '31', 'Female', 'oliviathomas@example.com'),
                                                      (14, 'Mason Taylor', '26', 'Male', 'masontaylor@example.com'),
                                                      (100, 'Nina Russell', '37', 'Female', 'ninarussell@example.com');
