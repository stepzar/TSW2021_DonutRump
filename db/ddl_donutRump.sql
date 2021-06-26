drop schema if exists donutrump;
create schema donutrump; 
use donutrump;

create table utente (
	id bigint auto_increment primary key,
	nome varchar (45) not null,
    cognome varchar (45) not null,
    email varchar (45) not null unique,
	isAdmin boolean not null,
    telefono varchar (45) unique, 
    pswd varchar (45) not null
	); 

create table indirizzo (
	id bigint auto_increment primary key,
    idUtente bigint, 
    cap char (5) not null,
    nCivico int  not null,
    via varchar (45) not null,
	provincia char (2) not null,
    citta varchar (45) not null,
	foreign key (idUtente) references utente(id) on update cascade on delete cascade
	); 
    
create table categoria(
    id int auto_increment primary key, 
    nome varchar(45) not null unique
    );
    
create table prodottoGenerico(
    id int auto_increment primary key,
    nome varchar(45) not null,
    quantita_disponibile int not null,
    prezzo double precision not null,
    iva double precision not null,
    disponibilita boolean not null,
    descrizione text(10000) not null,
    immagine varchar(255) default "default.jpg",
    categoria int,
    foreign key (categoria) references categoria(id) on update cascade on delete set null
    );
    
create table metodoPagamento 
(
 numeroCarta varchar(150) primary key,
 idUtente bigint, 
 scadenza date not null,
 cvv char(3) not null,
 foreign key (idUtente) references utente(id)  on update cascade on delete cascade
); 

 create table ordine(
 id bigint auto_increment primary key,
 idUtente bigint,
 idIndirizzo bigint,
 stato enum('consegnato', 'spedito', 'ricevuto', 'attesa'),
 dataOrdine date not null,
 importoTotale double precision default 0,
 speseSpedizione double precision default 0,
 quantitaAcquisto int not null, 
 dataConsegna date,
 metodoPagamento varchar(150),

 foreign key (idUtente) references utente(id) on update cascade on delete set null,
 foreign key (idIndirizzo) references Indirizzo(id) on update no action on delete no action, #lo gestiamo noi lato client,ma preferiamo mantenere tutti i dati degli indirizzi
 foreign key (metodoPagamento) references metodoPagamento(numeroCarta) on update no action on delete no action
 );

create table istanzaProdotto(
    id bigint auto_increment primary key,
	ivaAcquisto double precision not null,
    prezzoAcquisto double precision not null,
    prodottoGenerico int,
    idOrdine bigint,
    
    foreign key (prodottoGenerico) references prodottoGenerico(id) on update cascade on delete cascade,
    foreign key (idOrdine) references ordine(id) on update cascade on delete set null
    );
    
    
insert into categoria values (1, "generico"); #questa è la categoria che "salva" i prodotti senza categorie esplicite (come per esempio la MisteryBox)
insert into categoria values (2, "dolce"); 
insert into categoria values (3, "salato"); 
insert into categoria values (4, "bevande"); 
insert into utente values (1, "?", "?","admin@donut.rump.com", true, "0824494949", "root"); #creazione dell'admin 

