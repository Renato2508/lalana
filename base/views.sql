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


create view v_stat as
 SELECT idbouquet, nombouquet, idlocalisation, nomlocalisation, idduree, nomduree, sexe, sum(qte_reservee) as vendu
    FROM reservation as r
    natural join bouquet as b
    natural join duree as d
    natural join localisation as l
    natural join client as c
    group by idbouquet, nombouquet, idLocalisation, nomlocalisation, idduree, nomduree, sexe;
    order by idbouquet, idlocalisation, idduree, sexe asc