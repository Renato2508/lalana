CREATE OR REPLACE FUNCTION getlastprix(id_activite_arg int)
RETURNS numeric AS $$
DECLARE
    dernier_prix numeric;
BEGIN
    SELECT prixunitaire INTO dernier_prix
    FROM activite_prix
    WHERE idactivite = id_activite_arg
    ORDER BY datemaj DESC
    LIMIT 1;

    RETURN dernier_prix;
END;
$$ LANGUAGE plpgsql;
