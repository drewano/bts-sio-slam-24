drop database if exists orange_event_266 ; 
create database orange_event_266; 
use orange_event_266; 

create table technicien (
	idtechnicien int(3) not null auto_increment, 
	nom varchar (50), 
	prenom varchar (50), 
	qualification varchar (50), 
	email varchar (50), 
	mdp varchar (50),
	primary key (idtechnicien)
);
create table materiel (
	idmateriel int(3) not null auto_increment, 
	designation varchar (50), 
	dateAchat date , 
	prixAchat float, 
	categorie varchar (50),
	primary key (idmateriel)
);
create table intervention (
	idinter int(3) not null auto_increment, 
	description text, 
	dateinter date , 
	prix float, 
	idtechnicien int(3) not null, 
	idmateriel int(3) not null, 
	primary key (idinter), 
	foreign key (idtechnicien) references technicien (idtechnicien),
	foreign key (idmateriel) references materiel (idmateriel)
);

insert into technicien values 
	(null, "Semin", "Cynthia", "techn Box", "b@gmail.com", "123");


insert into intervention values
	(null, "changement", "30/10/2004", 32, 1, 1);


create view nbIntersTechniciens as (
	select t.nom, t.prenom, count(i.idinter) as nbInterventions
	from technicien t, intervention i 
	where t.idtechnicien = i.idtechnicien
	group by t.nom, t.prenom 
	order by t.nom, t.prenom
);

	 











