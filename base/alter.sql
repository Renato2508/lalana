-- For the table bouquet
ALTER TABLE bouquet
ADD COLUMN tipe varchar(3),
ADD COLUMN volHorRef double precision,
ADD COLUMN tauxAugmente double precision;

-- For the table dureee
ALTER TABLE duree
ADD COLUMN tipe varchar(3),
ADD COLUMN volHorRef double precision,
ADD COLUMN tauxAugmente double precision;





alter table profil
alter column  expmin 
type double precision;

alter table profil
alter column  expmax 
type double precision;

alter table profil
alter column  tauxaug
type double precision;

alter table profil
alter column  tauxhor
type double precision;

