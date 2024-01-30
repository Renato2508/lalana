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

create or replace view v_voyages_existants_genre as
    select *
    from bouquet as b
    cross join localisation as l
    cross join duree as d
    cross join (VALUES ('hom'), ('fem')) AS temp_table(sexe);




create or replace view v_statistiques as
 SELECT v.idbouquet, v.nombouquet, v.idlocalisation, v.nomlocalisation, v.idduree, v.nomduree, v.sexe, coalesce(sum(qte_reservee),0) as vendu
    FROM reservation as r
    natural join client as c
    right join v_voyages_existants_genre as v on
        r.idbouquet = v.idbouquet
        and r.idlocalisation = v.idlocalisation
        and r.idduree = v.idduree
        and c.sexe = v.sexe
    group by v.idbouquet, v.nombouquet, v.idLocalisation, v.nomlocalisation, v.idduree, v.nomduree, v.sexe;

    order by b.idbouquet, l.idlocalisation, d.idduree, sexe asc



create or replace view v_calcul_anciennete as
select idemploye,   DATE_PART('year', age(CURRENT_DATE, date1)) +
                    DATE_PART('month', age(CURRENT_DATE, date1)) / 12.0 +
                    DATE_PART('day', age(CURRENT_DATE, date1)) / 365.25 + p.experienceMin as anciennete
from employe as e 
natural join profil ;

create or replace view v_taux_horaire as
select tauxhoraire
from profil 
where type = "ref";

create or replace view v_taux_horaire_actuel as 
select a.idemploye, p.idprofil, (t.tauxhoraire * p.tauxaugemente) as tauxhoraire
from v_calcul_anciennete as a
join profil as p on (a.anciennete >= p.experiencemin and a.anciennete < p.experiencemax) or(a.anciennete >= p.exepriencemin and p.experiencemax is null); 
cross join v_taux_horaire as t

create or replace view v_taux_horaire_actuel_final as 
select e.*, p.*, tx.tauxhoraire
from v_taux_horaire_actuel as tx
natural join employe as e
natural join profil as p;
