create database spain_datos;
use spain_datos;

create table usuario(
	id INT  not null auto_increment primary key,
    uuid varchar(255) not null default(uuid()),
    nombre varchar(255) not null unique,
    email varchar(255) not null unique,
    id_sede int null,
	password varchar(255) not null,
    activo TINYINT(1) not null default(1),
    fecha_creacion datetime not null default now(),
    fecha_modificacion datetime not null default now()
);



insert into usuario(nombre, email, password)
values ('admin','admin@resenans','password');

insert into usuario(nombre, email, password)
values ('solo','solo@resenans','password');

create table comunidad_autonoma(
	id INT  not null auto_increment primary key,
    uuid varchar(120) not null default(uuid()),
    codigo_ine int not null unique,
    codigo_aemet varchar(255) not null,
    codigo_aemet_area varchar(255) not null,
    nombre varchar(255) not null unique,
    activo TINYINT(1) not null default(1) ,
    id_usuario_creacion int not null default(1),
    id_usuario_modificacion int not null default(1),
    fecha_creacion datetime not null default now(),
    fecha_modificacion datetime not null default now()
);

alter table comunidad_autonoma add foreign key fk_comunidad_autonoma_usuario_creacion (id_usuario_creacion) references usuario(id);
alter table comunidad_autonoma add foreign key fk_comunidad_autonoma_usuario_modificacion (id_usuario_modificacion) references usuario(id);

insert into comunidad_autonoma(codigo_ine,codigo_aemet, codigo_aemet_area,nombre,id_usuario_creacion,id_usuario_modificacion)values
(1,'and','61','Andalucía',1,1),
(2,'arn','62','Aragón',1,1),
(3,'ast','63','Asturias, Principado de',1,1),
(4,'bal','64','Balears, Illes',1,1),
(5,'coo','65','Canarias',1,1),
(6,'can','66','Cantabria',1,1),
(7,'cle','67','Castilla y León',1,1),
(8,'clm','68','Castilla - La Mancha',1,1),
(9,'cat','69','Cataluña',1,1),
(10,'val','77','Comunitat Valenciana',1,1),
(11,'ext','70','Extremadura',1,1),
(12,'gal','71','Galicia',1,1),
(13,'mad','72','Madrid, Comunidad de',1,1),
(14,'mur','73','Murcia, Región de',1,1),
(15,'nav','74','Navarra, Comunidad Foral de',1,1),
(16,'pav','75','País Vasco',1,1),
(17,'rio','76','Rioja, La',1,1),
(18,'','78','Ceuta',1,1),
(19,'','79','Melilla',1,1);



create table tipo_matricula(
	id INT  not null auto_increment primary key,
    uuid varchar(250) not null default(uuid()),
    nombre varchar(255) not null,
    descripcion varchar(255),
    codigo varchar(20) not null unique,
    activo TINYINT(1) not null default(1) ,
    id_usuario_creacion int not null default(1),
    id_usuario_modificacion int not null default(1),
    fecha_creacion datetime not null default now(),
    fecha_modificacion datetime not null default now()
);

alter table tipo_matricula add foreign key fk_tipo_matricula_usuario_creacion (id_usuario_creacion) references usuario(id);
alter table tipo_matricula add foreign key fk_tipo_matricula_usuario_modificacion (id_usuario_modificacion) references usuario(id);

insert into tipo_matricula(nombre,descripcion, codigo,id_usuario_creacion,id_usuario_modificacion)values
('Normal', 'Matrícula normal, 4 dígitos y 3 letras, ejemplo: 1234DFG', 'mat-nor',1,1),
('Histórica', 'Matrícula Histórica, H seguido 4 dígitos y 3 letras, ejemplo: H1234DFG', 'mat-his',1,1),
('Temporal Particulares', 'Matrícula Temporal Particular, P seguido de 4 dígitos y 3 letras, ejemplo: P1234RTF', 'mat-pro-par',1,1),
('Temporal Empresas', 'Matrícula Temporal Empresas, lleva una letra S(Vehículos no matriculados) o V(Vehículos ya matriculados) seguido de 4 números y 3 letras, a continuación mes de valided en numeros romanoas y el año, ejemplo: S4567CFG', 'mat-pro-emp',1,1),
('Guardia Civil', 'Matrícula Guardia civil, PGC seguido 5 dígitos, ejemplo: PGC012345', 'mat-pgc',1,1),
('Cuerpo Nacional de Policía', 'Matrícula Cuerpo Nacional de Policía, CNP seguido 5 dígitos, ejemplo: CNP12345', 'mat-cnp',1,1),
('Ejército de Tierra', 'Matrícula del Ejército de Tierra, ET seguido 5 dígitos, ejemplo: ET12345', 'mat-et',1,1),
('Ejército del Aire', 'Matrícula del Ejército del Aire, EA seguido 5 dígitos, ejemplo: EA12345', 'mat-ea',1,1),
('Fuerzas Navales', 'Matrícula de las Flotas Navales, FN seguido 5 dígitos, ejemplo:FNT12345', 'mat-fn',1,1),
('Policía Canaria', 'Matrícula de la Policía Canaria, CGPC seguido 5 dígitos, ejemplo:CGPC12345', 'mat-cgpc',1,1);

