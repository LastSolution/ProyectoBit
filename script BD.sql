create database TastIT
go
use TastIT
go

create table empresas(
	rut varchar (12) primary key not null,
	nombre varchar(30) not null,
	telefono varchar(20) not null,
	correo varchar(50) not null,
	logo varchar(max),
	deleted bit not null default(0)
)

create table locales(
	nro_local int identity (1,1) not null,
	latitud varchar(max),
	longitud varchar(max),
	direccion varchar(max) not null,
	telefono varchar(20),
	empresa varchar(12),
	deleted bit not null default(0),
	primary key (nro_local),
	foreign key (empresa) references empresas (rut)
)

create table usuarios(
	id_usuario int identity (1,1) not null,
	username varchar(10) not null,
	password varchar(20) not null,
	tipo varchar(30),
	empresa varchar(12),
	deleted bit not null default(0),
	primary key (id_usuario),
	foreign key (empresa) references empresas (rut)
)

create table categorias(
	id_categoria int identity (1,1) primary key,
	nombre_categoria varchar(50)
)

create table cartas(
	nro_carta int identity (1,1) not null,
	fecha_carta smalldatetime not null,
	nombre_carta varchar(100) not null,
	imgCarta varchar(max),
	local int not null,
	deleted bit not null default(0),
	primary key (nro_carta),
	foreign key (local) references locales (nro_local)
)

create table pedidos(
	id_pedido int identity (1,1) primary key  not null,
	estado int not null,
	precio_total money not null,
	deleted bit not null default(0)
)

create table platos(
	id_plato int identity (1,1) primary key  not null,
	nombre_plato varchar(50) not null,
	precio money not null,
	tamanio int not null,
	imagen varchar(max),
	carta int not null,
	categoria int not null,
	deleted bit not null default(0),
	foreign key (carta) references cartas (nro_carta),
	foreign key (categoria) references categorias (id_categoria)
)

create table pedidos_platos(
	pedido int,
	plato int,
	cantidad int not null,
	primary key (pedido, plato),
	foreign key (pedido) references pedidos (id_pedido),
	foreign key (plato) references platos (id_plato)
)


create table mesas(
	nro_mesa int identity (1,1) not null,
	x varchar(max),
	y varchar(max),
	img varchar(max),
	carta int not null,
	usuario int not null,
	pedido int not null,
	local int not null,
	fecha_atencion smalldatetime 
		check(fecha_atencion >= getDate()) default(getDate()),
	cant_comensales int not null,
	deleted bit not null default(0),
	primary key (nro_mesa),
	foreign key (carta) references cartas (nro_carta),
	foreign key (usuario) references usuarios (id_usuario),
	foreign key (pedido) references pedidos (id_pedido),
	foreign key (local) references locales (nro_local)
)

create table clientes(
	id_cliente int identity (1,1) primary key not null,
	rut_cliente varchar(20),
	nombre_cliente varchar(60) not null,
	deleted bit not null default(0)
)

create table reservas(
	nro_reserva int identity (1,1) not null,
	nro_tarjeta varchar(50),
	metodo_pago int not null,
	mesa int not null,
	cliente int not null,
	fecha_solicitud smalldatetime default(getDate()),
	deleted bit not null default(0),
	primary key (nro_reserva),
	foreign key (mesa) references mesas (nro_mesa),
	foreign key (cliente) references clientes (id_cliente)
)

create table solicitan(
	nro_reserva int not null,
	nro_mesa int not null,
	deleted bit not null default(0),
	primary key (nro_reserva, nro_mesa),
	foreign key (nro_mesa) references mesas (nro_mesa),
	foreign key (nro_reserva) references reservas (nro_reserva)
)

create table atienden(
	id_usuario int not null,
	nro_mesa int not null,
	deleted bit not null default(0),
	primary key (id_usuario, nro_mesa),
	foreign key (nro_mesa) references mesas (nro_mesa),
	foreign key (id_usuario) references usuarios (id_usuario)
)


insert into empresas (rut,nombre,telefono,correo,logo,deleted)
	values ('100000000666', 'LST', '098790944', 'lastsolutionteam@gmail.com', '', 0)

insert into usuarios (username,password,tipo,empresa,deleted)
	values ('Admin', 'admin', 'Administrador', '100000000666', 0)