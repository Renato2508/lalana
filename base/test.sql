select coalesce(sum(qte_mvt), 0) as stock 
from mouvement where idactivite = ?;