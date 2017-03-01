-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-01-08 19:38:14.657




-- tables
-- Table Jelo
CREATE TABLE Jelo (
    ID_jela int  NOT NULL  AUTO_INCREMENT,
    Naziv_jela varchar(32)  NOT NULL,
    Opis_jela varchar(32)  NOT NULL,
    CONSTRAINT Jelo_pk PRIMARY KEY (ID_jela)
);

-- Table Jelovnik
CREATE TABLE Jelovnik (
    ID_jelovnika int  NOT NULL  AUTO_INCREMENT,
    Naziv_jelovnika varchar(32)  NOT NULL,
    Restoran_ID_restorana int  NOT NULL,
    CONSTRAINT Jelovnik_pk PRIMARY KEY (ID_jelovnika)
);

-- Table Korisnik
CREATE TABLE Korisnik (
    ID_korisnika int  NOT NULL  AUTO_INCREMENT,
    Email_korisnika varchar(32)  NOT NULL,
    Sifra_korisnika varchar(32)  NOT NULL,
    Ime_korisnika varchar(32)  NOT NULL,
    Adresa_korisnika varchar(64)  NULL,
    Uloga_korisnika_ID_uloge int  NOT NULL,
    UNIQUE INDEX Korisnik_ak_1 (Email_korisnika),
    CONSTRAINT Korisnik_pk PRIMARY KEY (ID_korisnika)
);

-- Table OcenaPotvrdio
CREATE TABLE OcenaPotvrdio (
    ID_ocena_potvrdio int  NOT NULL  AUTO_INCREMENT,
    Potvrdio bool  NOT NULL,
    Ocena int  NOT NULL,
    Korisnik_ID_korisnika int  NOT NULL,
    Rezervacija_ID_rezervacije int  NOT NULL,
    UNIQUE INDEX OcenaPotvrdio_ak_1 (Korisnik_ID_korisnika,Rezervacija_ID_rezervacije),
    CONSTRAINT OcenaPotvrdio_pk PRIMARY KEY (ID_ocena_potvrdio)
);

-- Table Prijatelji
CREATE TABLE Prijatelji (
    ID_kor_1 int  NOT NULL,
    ID_kor_2 int  NOT NULL,
    ID_prijatelji int  NOT NULL  AUTO_INCREMENT,
    UNIQUE INDEX Prijatelji_ak_1 (ID_kor_1,ID_kor_2),
    CONSTRAINT Prijatelji_pk PRIMARY KEY (ID_prijatelji)
);

-- Table Restoran
CREATE TABLE Restoran (
    ID_restorana int  NOT NULL  AUTO_INCREMENT,
    Naziv_restorana varchar(32)  NOT NULL,
    Opis_restorana varchar(32)  NULL,
    Menadzer_restorana int  NOT NULL,
    DimX_restorana int  NOT NULL,
    DimY_restorana int  NOT NULL,
    CONSTRAINT Restoran_pk PRIMARY KEY (ID_restorana)
);

-- Table Rezervacija
CREATE TABLE Rezervacija (
    ID_rezervacije int  NOT NULL  AUTO_INCREMENT,
    DatumVreme timestamp  NOT NULL,
    Trajanje int  NOT NULL,
    Korisnik_ID_korisnika int  NOT NULL,
    Sto_ID_stola int  NOT NULL,
    CONSTRAINT Rezervacija_pk PRIMARY KEY (ID_rezervacije)
);

-- Table Stavka_jelovnika
CREATE TABLE Stavka_jelovnika (
    ID_stavke_jelovnika int  NOT NULL  AUTO_INCREMENT,
    Cena_stavke_jelovnika double(8,2)  NOT NULL,
    Jelovnik_ID_jelovnika int  NOT NULL,
    Jelo_ID_jela int  NOT NULL,
    UNIQUE INDEX Stavka_jelovnika_ak_1 (Jelovnik_ID_jelovnika,Jelo_ID_jela),
    CONSTRAINT Stavka_jelovnika_pk PRIMARY KEY (ID_stavke_jelovnika)
);

-- Table Sto
CREATE TABLE Sto (
    Naziv_stola varchar(32)  NOT NULL,
    PozX_stola int  NOT NULL,
    PozY_stola int  NOT NULL,
    Broj_mesta int  NOT NULL,
    Restoran_ID_restorana int  NOT NULL,
    ID_stola int  NOT NULL  AUTO_INCREMENT,
    CONSTRAINT Sto_pk PRIMARY KEY (ID_stola)
);

-- Table Uloga_korisnika
CREATE TABLE Uloga_korisnika (
    ID_uloge int  NOT NULL  AUTO_INCREMENT,
    Naziv_uloge varchar(16)  NOT NULL,
    CONSTRAINT Uloga_korisnika_pk PRIMARY KEY (ID_uloge)
);





-- foreign keys
-- Reference:  Jelovnik_Restoran (table: Jelovnik)

ALTER TABLE Jelovnik ADD CONSTRAINT Jelovnik_Restoran FOREIGN KEY Jelovnik_Restoran (Restoran_ID_restorana)
    REFERENCES Restoran (ID_restorana);
-- Reference:  Korisnik_Uloga_korisnika (table: Korisnik)

ALTER TABLE Korisnik ADD CONSTRAINT Korisnik_Uloga_korisnika FOREIGN KEY Korisnik_Uloga_korisnika (Uloga_korisnika_ID_uloge)
    REFERENCES Uloga_korisnika (ID_uloge);
-- Reference:  Menadzer_restorana_FK (table: Restoran)

ALTER TABLE Restoran ADD CONSTRAINT Menadzer_restorana_FK FOREIGN KEY Menadzer_restorana_FK (Menadzer_restorana)
    REFERENCES Korisnik (ID_korisnika);
-- Reference:  OcenaPotvrdio_Korisnik (table: OcenaPotvrdio)

ALTER TABLE OcenaPotvrdio ADD CONSTRAINT OcenaPotvrdio_Korisnik FOREIGN KEY OcenaPotvrdio_Korisnik (Korisnik_ID_korisnika)
    REFERENCES Korisnik (ID_korisnika);
-- Reference:  OcenaPotvrdio_Rezervacija (table: OcenaPotvrdio)

ALTER TABLE OcenaPotvrdio ADD CONSTRAINT OcenaPotvrdio_Rezervacija FOREIGN KEY OcenaPotvrdio_Rezervacija (Rezervacija_ID_rezervacije)
    REFERENCES Rezervacija (ID_rezervacije);
-- Reference:  Prijatelji_Korisnik_1 (table: Prijatelji)

ALTER TABLE Prijatelji ADD CONSTRAINT Prijatelji_Korisnik_1 FOREIGN KEY Prijatelji_Korisnik_1 (ID_kor_1)
    REFERENCES Korisnik (ID_korisnika);
-- Reference:  Prijatelji_Korisnik_2 (table: Prijatelji)

ALTER TABLE Prijatelji ADD CONSTRAINT Prijatelji_Korisnik_2 FOREIGN KEY Prijatelji_Korisnik_2 (ID_kor_2)
    REFERENCES Korisnik (ID_korisnika);
-- Reference:  Rezervacija_Korisnik (table: Rezervacija)

ALTER TABLE Rezervacija ADD CONSTRAINT Rezervacija_Korisnik FOREIGN KEY Rezervacija_Korisnik (Korisnik_ID_korisnika)
    REFERENCES Korisnik (ID_korisnika);
-- Reference:  Rezervacija_Sto (table: Rezervacija)

ALTER TABLE Rezervacija ADD CONSTRAINT Rezervacija_Sto FOREIGN KEY Rezervacija_Sto (Sto_ID_stola)
    REFERENCES Sto (ID_stola);
-- Reference:  Stavka_jelovnika_Jelo (table: Stavka_jelovnika)

ALTER TABLE Stavka_jelovnika ADD CONSTRAINT Stavka_jelovnika_Jelo FOREIGN KEY Stavka_jelovnika_Jelo (Jelo_ID_jela)
    REFERENCES Jelo (ID_jela);
-- Reference:  Stavka_jelovnika_Jelovnik (table: Stavka_jelovnika)

ALTER TABLE Stavka_jelovnika ADD CONSTRAINT Stavka_jelovnika_Jelovnik FOREIGN KEY Stavka_jelovnika_Jelovnik (Jelovnik_ID_jelovnika)
    REFERENCES Jelovnik (ID_jelovnika);
-- Reference:  Sto_Restoran (table: Sto)

ALTER TABLE Sto ADD CONSTRAINT Sto_Restoran FOREIGN KEY Sto_Restoran (Restoran_ID_restorana)
    REFERENCES Restoran (ID_restorana);



-- End of file.

