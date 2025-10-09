-- Creation de la table Entraineurs
CREATE TABLE Entraineurs(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    argents INTEGER);

-- Creation de la table EspeceMonstre
CREATE TABLE EspeceMonstre(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    type VARCHAR(255),
    baseAttaque INTEGER,
    baseDefense INTEGER,
    baseVitesse INTEGER,
    baseAttaqueSpe INTEGER,
    baseDefenseSpe INTEGER,
    basePv INTEGER,
    modAttaque INTEGER,
    modDefense INTEGER,
    modVitesse INTEGER,
    modAttaqueSpe INTEGER,
    modDefenseSpe INTEGER,
    modPv INTEGER,
    description VARCHAR(255),
    particularite VARCHAR(255),
    caracteres VARCHAR(255));

-- Creation de la table Zone
CREATE TABLE Zone(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    expZone INTEGER);

-- Creation de la table Zone_EspeceMonstre
CREATE TABLE Zone_EspeceMonstre(
    zone_id INTEGER,
    espece_id INTEGER,
    PRIMARY KEY (zone_id, espece_id),
    FOREIGN KEY (zone_id) REFERENCES Zone(id),
    FOREIGN KEY (espece_id) REFERENCES EspeceMonstre(id));

-- Creation de la table IndividuMonstre
CREATE TABLE IndividuMonstre (
        id INT PRIMARY KEY,
        nom VARCHAR(255) NOT NULL,
        espece_id INT NOT NULL,
        entraineur_id INT NULL,
        expInit DOUBLE NOT NULL DEFAULT 0.0,
        FOREIGN KEY (espece_id) REFERENCES EspeceMonstre(id),
        FOREIGN KEY (entraineur_id) REFERENCES Entraineurs(id));

--Insertion de données :
INSERT INTO Entraineurs (nom, argents)
VALUES
    ('Bob', 1),
    ('Alice', 1),
    ('Clara', 1);
