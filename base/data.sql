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


CREATE VIEW v_ActiviteBouquet AS
    SELECT  b.idBouquet, b.NomBouquet, a.idActivite, a.NomActivite
    FROM ActiviteBouquet as ab
    JOIN Activite as a ON ab.idActivite = a.idActivite
    JOIN Bouquet as b ON ab.idBouquet = b.idBouquet;
