create database voyage;
\c voyage

-- Création des tables

-- Table activite
CREATE TABLE activite (
    idactivite SERIAL PRIMARY KEY,
    nomactivite VARCHAR
);

-- Table bouquet
CREATE TABLE bouquet (
    idbouquet SERIAL PRIMARY KEY,
    nombouquet VARCHAR
);

-- Table activitebouquet
CREATE TABLE activitebouquet (
    idActiviteBouquet SERIAL PRIMARY KEY,
    idActivite INT REFERENCES activite(idactivite),
    idBouquet INT REFERENCES bouquet(idbouquet)
);


-- Table duree
CREATE TABLE duree (
    idduree SERIAL PRIMARY KEY,
    nomduree VARCHAR,
    dureeMin INT,
    dureeMax INT
);

-- Table localisation
CREATE TABLE localisation (
    idLocalisation SERIAL PRIMARY KEY,
    nomlocalisation VARCHAR

);


-- Table Composition
CREATE TABLE composition (
    idbouquet int references bouquet(idbouquet),
    idactivite int references activite(idactivite),
    idLocalisation INT REFERENCES localisation(idLocalisation),
    idduree INT REFERENCES duree(idduree),
    frequence INT
);

create table activite_prix(
    idactivite int references activite(idactivite),
    datemaj date not null,
    prixunitaire numeric(11,2) not null
);




create table mouvement(
    id_mvt serial primary key,
    date_mvt date,
    qte_mvt numeric,
    idActivite INT REFERENCES activite(idactivite)

);

create table reservation(
    idbouquet int references bouquet(idbouquet),
    idLocalisation INT REFERENCES localisation(idLocalisation),
    idduree INT REFERENCES duree(idduree),
    qte_reservee INT,
    date_reservation date
);
