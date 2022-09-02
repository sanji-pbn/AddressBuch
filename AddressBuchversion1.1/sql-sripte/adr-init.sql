drop table AB_Kontakt;
drop table AB_Anschrift;
drop table AB_Bankverbindung;
drop table AB_Person;


CREATE TABLE AB_Person
(
   Pk varchar(45) PRIMARY KEY NOT NULL,
   Vorname varchar(255),
   Nachname varchar(255),
   Anrede varchar(20),
   Title varchar(20),
   Geburtsdatum varchar(10),
   Geschlecht varchar(20),
   Sterbedatum varchar(10),
   Kurzform varchar(30),
   Firma varchar(255),
   Vertraulich_enum varchar(20),
   BevorzugterKontaktweg_enum varchar(20),
   JurOderNatPerson_enum varchar(20),
   Bemerkung varchar(255),
   lockDSGVO tinyint DEFAULT ((0)) NOT NULL

)

-- drop table AB_Kontakt
CREATE TABLE AB_Kontakt
(
   Pk varchar(45) PRIMARY KEY NOT NULL,
   Person_fk varchar(45) NOT NULL,
   Messagetyp_Enum varchar(20),
   EmailOrPhone varchar(255),
   Aktiv tinyint
)


CREATE TABLE AB_Anschrift
(
   Pk varchar(45) PRIMARY KEY NOT NULL,
   Person_fk varchar(45) NOT NULL,
   Plz varchar(20),
   Ort varchar(255),
   Land varchar(255),
   Strasse varchar(255),
   Anschriftentyp_Enum varchar(20),
   Aktiv tinyint
)

CREATE TABLE AB_Bankverbindung
(
   Pk varchar(45) PRIMARY KEY NOT NULL,
   Person_fk varchar(45) NOT NULL,
   IBAN varchar(45),
   BIC varchar(45),
   kreditinstitut varchar(255),
   Kontoinhaber varchar(255),   
   Ort varchar(255),
   Plz varchar(20),
   Strasse varchar(255),
   Aktiv tinyint
)