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


create or replace view v_calcul_anciennete as
select e.idemploye,   (DATE_PART('year', age(CURRENT_DATE, e.dateembauche)) +
                    DATE_PART('month', age(CURRENT_DATE, e.dateembauche)) / 12.0 +
                    DATE_PART('day', age(CURRENT_DATE, e.dateembauche)) / 365.25 + p.expmin) as anciennete
from employe as e 
join profil as p on e.profilembauche = p.idprofil;

create or replace view v_taux_horaire as
select tauxhor as tauxhoraire
from profil 
where tipe = 'ref';

create or replace view v_taux_horaire_actuel as 
select a.idemploye, p.idprofil, coalesce((t.tauxhoraire * p.tauxaug), t.tauxhoraire) as tauxhoraire
from v_calcul_anciennete as a
cross join v_taux_horaire as t
join profil as p on (a.anciennete >= p.expmin and a.anciennete < p.expmax) 
                    or(a.anciennete >= p.expmin and p.expmax is null)
;

create or replace view v_taux_horaire_actuel_final as 
select e.*, p.*, tx.tauxhoraire
from v_taux_horaire_actuel as tx
natural join employe as e
natural join profil as p;


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

-- volHor  de base
create view volHorBase as
    select volhorref as volHorref
    from duree
    where tipe= 'ref';

-- nombre d'ouvriers de base
create view ouvriersBase as
    select nbrEmpRef as ouvrierBase
    from bouquet 
    where tipe = 'ref';

create view v_tot_per_hor as
    select  b.idbouquet, d.idduree, l.idlocalisation, coalesce(b.tauxAugmente,1) as taux_Augmente_Ouvier, o.ouvrierBase, (coalesce(b.tauxAugmente,1) * o.ouvrierBase) as ouvriers, v.volHorref, coalesce(d.tauxAugmente, 1 ) as taux_Augmente_Heure, (v.volHorref * coalesce(d.tauxAugmente, 1 )) as volHorPers
    from bouquet as b
    cross join duree as d
    cross join localisation as l
    
    cross join ouvriersBase as o
    cross join volHorBase as  v;


-- calcul du travail fourni pour chaque voyage
create view v_vol_hor_tot as
    select  idbouquet, idduree, idlocalisation, ouvriers, volHorPers, (ouvriers*volHorPers) as volHorTot
    from v_tot_per_hor 
    where ouvriers is not null
    

-- coout humain
create view v_cout_humain as
    select tot.*, tx.tauxbase,(tot.volHorTot * tx.tauxbase) as cout_humain
    from v_vol_hor_tot as tot
    cross join tauxhoraire as tx;

-- prix de revient de chaque voyage
create view prix_revient as
    select hum.*,  prx.prix as prix_achat ,(hum.cout_humain + prx.prix) as revient
    from v_bouquet_prix as prx
    join v_cout_humain as hum
        on prx.idbouquet = hum.idbouquet
        and prx.idlocalisation = hum.idlocalisation
        and prx.idduree = hum.idduree;
        

create or replace  view v_revient_all as
select ben.*, bou.nombouquet, dur.nomduree, loc.nomlocalisation
from prix_revient as ben
    join bouquet as bou on ben.idbouquet = bou.idbouquet
    join duree as dur on ben.idduree = dur.idduree
    join localisation as loc on ben.idlocalisation = loc.idlocalisation;

-- bénnéfice

create view v_benefice as 
    select rev.*, vnt.prix_vente, (vnt.prix_vente - rev.revient) as benefice
    from prix_revient as rev
    join voyageprix as vnt
        on rev.idbouquet = vnt.idbouquet
        and rev.idlocalisation = vnt.idlocalisation
        and rev.idduree = vnt.idduree;

create or replace view v_benefice_all as
    select ben.*, bou.nombouquet, dur.nomduree, loc.nomlocalisation
    from v_benefice as ben
    join bouquet as bou on ben.idbouquet = bou.idbouquet
    join duree as dur on ben.idduree = dur.idduree
    join localisation as loc on ben.idlocalisation = loc.idlocalisation;
    


