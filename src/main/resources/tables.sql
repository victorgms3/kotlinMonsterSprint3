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
        id INT PRIMARY KEY AUTO_INCREMENT,
        nom VARCHAR(255) NOT NULL,
        espece_id INT NOT NULL,
        entraineur_id INT NULL,
        expInit DOUBLE NOT NULL DEFAULT 0.0,
        FOREIGN KEY (espece_id) REFERENCES EspeceMonstre(id),
        FOREIGN KEY (entraineur_id) REFERENCES Entraineurs(id));

--Insertion de données :

--Entraineurs
INSERT INTO Entraineurs (nom, argents)
VALUES
    ('Bob', 1),
    ('Alice', 1),
    ('Clara', 1);

--Especes :
INSERT INTO EspecesMonstre
(id, nom, type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv,
 modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv,
 description, particularites, caracteres)
VALUES
    (1, 'springleaf', 'Graine', 9, 11, 10, 12, 14, 60,
     6.5, 9.0, 8.0, 7.0, 10.0, 14.0,
     'Un petit monstre espiègle au corps rond comme une graine. Il aime se cacher dans l’herbe haute et se dorer au soleil.',
     'Sa feuille sur la tête s’incline pour indiquer son humeur.',
     'Curieux, amical, un peu timide.'),

    (4, 'flamkip', 'Animal', 12, 8, 13, 16, 7, 50,
     10.0, 5.5, 9.5, 9.5, 6.5, 12.0,
     'Ce petit animal est toujours entouré d’une flamme dansante. Il déteste le froid et s’énerve facilement quand on tente d’éteindre son feu.',
     'Sa flamme change d’intensité selon son niveau d’énergie.',
     'Impulsif, joueur, loyal.'),
    (7, 'aquamy', 'Meteo', 10, 11, 9, 14, 14, 55,
     9.0, 10.0, 7.5, 12.0, 12.0, 13.5,
     'Une créature vaporeuse qui ressemble à un petit nuage. Les gouttes qui tombent de son corps sont pures et rafraîchissantes.',
     'Fait légèrement baisser la température autour de lui quand il s’endort.',
     'Calme, rêveur, mystérieux.'),

    (8, 'laoumi', 'Animal', 11, 10, 9, 8, 11, 58,
     11.0, 8.0, 7.0, 6.0, 11.5, 14.0,
     'Un petit ourson au pelage soyeux. Il adore se tenir debout et brandir ses petites pattes comme s’il dansait.',
     'Son grognement est plus mignon qu’effrayant, mais il devient redoutable pour défendre ses amis.',
     'Affectueux, protecteur, gourmand.'),

    (10, 'bugsyface', 'Insecte', 10, 13, 8, 7, 13, 45,
     7.0, 11.0, 6.5, 8.0, 11.5, 10.0,
     'Un insecte à la carapace luisante qui se déplace par petits bonds. Il communique en faisant vibrer ses antennes.',
     'Sa carapace devient plus dure après chaque mue, augmentant sa défense.',
     'Travailleur, sociable, infatigable.'),

    (13, 'galum', 'Mineral', 12, 15, 6, 8, 12, 55,
     9.0, 13.0, 4.0, 6.5, 10.5, 13.0,
     'Un golem ancien sculpté dans la pierre. Ses yeux s’illuminent d’une lueur mystérieuse quand il se met en garde.',
     'Peut rester immobile pendant des heures, le faisant passer pour une statue.',
     'Sérieux, stoïque, fiable.'),
    (17, 'pikachu', 'Électrique', 13, 8, 16, 14, 9, 50,
     10.5, 7.0, 12.0, 11.5, 7.5, 12.5,
     'Un petit rongeur jaune capable de générer de puissantes décharges électriques. Sa queue en forme d’éclair vibre quand il est excité.',
     'Il emmagasine de l’électricité dans ses joues. Peut libérer une attaque foudroyante en cas de danger.',
     'Énergique, loyal, espiègle.');

--Individus :
INSERT INTO IndividuMonstre (espece_id, nom, entraineur_id, expInit)
VALUES
    (1,'springleaf',2, 1.0),
    (7, 'aquamy',1,1.0),
    (10,'bugsyface',1,1.0),
    (13,'galum',3,1.0),
    (4,'flamkip',3,1.0);



