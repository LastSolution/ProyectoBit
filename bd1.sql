create table empresas(
	rut varchar (20) primary key not null,
	nombre varchar(30) not null,
	telefono varchar(20) not null,
	correo varchar(50) not null
)

create table locales(
	nro_local int identity (1,1) not null,
	latitud varchar(max),
	longitud varchar(max),
	direccion varchar(max) not null,
	telefono varchar(20),
	empresa varchar(20),
	primary key (nro_local),
	foreign key (empresa) references empresas (rut)
)

create table usuarios(
	id_usuario int identity (1,1) not null,
	username varchar(10) not null,
	password varchar(20) not null,
	tipo int,
	empresa varchar(20),
	primary key (id_usuario),
	foreign key (empresa) references empresas (rut)
)

create table platos(
	nombre_plato varchar(50) primary key not null,
	precio money not null,
	tamanio int not null,
	categoria int not null
)

create table cartas(
	nro_carta int identity (1,1) not null,
	fecha_carta smalldatetime not null,
	nombre_carta varchar(100) not null,
	plato varchar(50) not null,
	primary key (nro_carta),
	foreign key (plato) references platos (nombre_plato)
)

create table mesas(
	nro_mesa int identity (1,1) not null,
	x varchar(max),
	y varchar(max),
	carta int not null,
	usuario int not null,
	pedido varchar(50) not null,
	fecha_atencion smalldatetime 
		check(fecha_atencion >= getDate()) default(getDate()),
	cant_comensales int not null,
	primary key (nro_mesa),
	foreign key (carta) references cartas (nro_carta),
	foreign key (usuario) references usuarios (id_usuario),
	foreign key (pedido) references platos (nombre_plato)
)

create table clientes(
	id_cliente int primary key not null,
	nombre_cliente varchar(60) not null
)

create table reservas(
	nro_reserva int identity (1,1) not null,
	nro_tarjeta varchar(50),
	metodo_pago int not null,
	mesa int not null,
	cliente int not null,
	fecha_solicitud smalldatetime default(getDate()),
	primary key (nro_reserva),
	foreign key (mesa) references mesas (nro_mesa),
	foreign key (cliente) references clientes (id_cliente)
)

create table pedidos(
	codigo varchar(6) primary key,
	estado int not null,
	precio_total money not null,
	plato varchar(50),
	foreign key (plato) references platos (nombre_plato),
)

create table solicitan(
	nro_reserva int not null,
	nro_mesa int not null,
	primary key (nro_reserva, nro_mesa)
)

create table atienden(
	id_usuario int not null,
	nro_mesa int not null,
	primary key (id_usuario, nro_mesa)
)