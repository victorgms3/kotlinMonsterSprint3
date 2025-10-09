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
    caracteres VARCHAR(255);

-- Creation de la table Zone
CREATE TABLE Zone(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    expZone INTEGER);

