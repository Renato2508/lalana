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

-- Création des vues

-- Vue v_activite_bouquet_all
CREATE VIEW v_activite_bouquet_all AS
    SELECT ab.idActiviteBouquet, a.idActivite, a.nomactivite, getlastprix(a.idactivite) as prixunitaire, b.idBouquet, b.nombouquet
    FROM activitebouquet ab
    Natural JOIN activite a 
    natural JOIN bouquet b;

-- Vue v_composition
CREATE VIEW v_composition AS
    SELECT vab.idbouquet, vab.nombouquet, vab.idactivite, vab.nomactivite, c.idLocalisation, l.nomlocalisation, c.idduree, d.nomduree, d.dureeMin, d.dureeMax, c.frequence, vab.prixunitaire
    FROM composition c
    natural JOIN v_activite_bouquet_all vab 
    natural JOIN localisation l 
    natural JOIN duree d ;

CREATE VIEW v_bouquet_prix AS
    SELECT idbouquet, nombouquet, idlocalisation, nomlocalisation, idduree, nomduree, sum(prixunitaire*frequence) as prix
    FROM v_composition as vc
    group by idbouquet, nombouquet, idLocalisation, nomlocalisation, idduree, nomduree;