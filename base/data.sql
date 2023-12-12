CREATE DATABASE voyage;
\c voyage;
-- Table des activités
CREATE TABLE Activite (
    idActivite SERIAL PRIMARY KEY,
    NomActivite VARCHAR(255) NOT NULL
);

-- Table des bouquets
CREATE TABLE Bouquet (
    idBouquet SERIAL PRIMARY KEY,
    NomBouquet VARCHAR(255) NOT NULL
);

-- Table de liaison entre les activités et les bouquets
CREATE TABLE ActiviteBouquet (
    idActiviteBouquet SERIAL PRIMARY KEY,
    idActivite INT,
    idBouquet INT,
    FOREIGN KEY (idActivite) REFERENCES Activite(idActivite),
    FOREIGN KEY (idBouquet) REFERENCES Bouquet(idBouquet)
);

-- CREATE VIEW VueActiviteBouquet AS
-- SELECT ab.idActivite, a.NomActivite, ab.idBouquet, b.NomBouquet
-- FROM ActiviteBouquet ab
-- JOIN Activite a ON ab.idActivite = a.idActivite
-- JOIN Bouquet b ON ab.idBouquet = b.idBouquet;

-- Insertion de données dans la table Activite
INSERT INTO Activite (idActivite, NomActivite) VALUES
    (1, 'Activite1'),
    (2, 'Activite2'),
    (3, 'Activite3');

-- Insertion de données dans la table Bouquet
INSERT INTO Bouquet (idBouquet, NomBouquet) VALUES
    (1, 'Bouquet1'),
    (2, 'Bouquet2'),
    (3, 'Bouquet3');

-- Insertion de données dans la table ActiviteBouquet
INSERT INTO ActiviteBouquet (idActiviteBouquet, idActivite, idBouquet) VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 2),
    (4, 1, 3);

CREATE VIEW VueActiviteBouquet AS
SELECT ab.idActiviteBouquet, a.idActivite, a.NomActivite, b.idBouquet, b.NomBouquet
FROM ActiviteBouquet ab
JOIN Activite a ON ab.idActivite = a.idActivite
JOIN Bouquet b ON ab.idBouquet = b.idBouquet;

SELECT * FROM VueActiviteBouquet;
