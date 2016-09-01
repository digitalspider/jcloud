-- INSERT into page (firstName,lastName,course,year) VALUES ("test","user","PhD", 2012);
-- INSERT into page (firstName,lastName,course,year) VALUES ("david","vittor","MBT", 2016);
-- INSERT into page (firstName,lastName,course,year) VALUES ("daniela","vittor","PhD", 2014);

CREATE TABLE page (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
    mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    muser integer NOT NULL,
	type    text NOT NULL DEFAULT 'blog',
	title   text NOT NULL,
	description text NOT NULL,
	content text NOT NULL,
	url     text NOT NULL,
	status  text NOT NULL,
	user integer NOT NULL,
	parent integer,
	tags    text
);

CREATE TABLE comment (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	name   text NOT NULL,
	content text NOT NULL,
	user integer NOT NULL,
	parent integer NULL,
	status text NOT NULL
);

CREATE TABLE rating (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	rating  int NOT NULL,
	content text,
	user integer NOT NULL,
	status text NOT NULL
);


CREATE TABLE pageExtra (
	pageId integer NOT NULL,
	likesCount  integer,
	commentCount integer,
	ratingAvg integer,
	ratingCount integer,
	viewCount integer,
	readCount integer
);

CREATE TABLE user (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
    mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    muser integer NOT NULL,
	email text NOT NULL,
	username text NOT NULL,
	password text NOT NULL,
	firstname text NOT NULL,
	lastname text NOT NULL,
	url text,
	mobile text,
	description text,
	tags text,
	type text NOT NULL,
	status text NOT NULL,
	token text,
	image blob
);

CREATE TABLE userExtra (
	userId integer NOT NULL,
	loginCount  integer,
	lastLogin date,
	moneyPaid double,
	moneyOwed double,
	monthlyCharge double,
	pageCount integer
);


CREATE TABLE role (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	name text NOT NULL,
    status text NOT NULL
);

CREATE TABLE request (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	name text NOT NULL,
	title text NOT NULL,
	content text NOT NULL,
	type text NOT NULL,
	user int NOT NULL,
    status text NOT NULL
);

CREATE TABLE os (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
    distribution text NOT NULL,
    version text NOT NULL,
    architecture text NOT NULL
);

CREATE TABLE server (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
    mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    muser integer NOT NULL,
    name text NOT NULL,
	user integer NOT NULL,
	lxdId text,
	alias text,
	type text,
	description text,
	tags text,
	status text NOT NULL,
	os integer NOT NULL,
	pdate 	date,
	price double,
	cpulimit double,
	cpuusage double,
	cpupeak double,
	memlimit double,
	memusage double,
	mempeak double,
	hddlimit double,
	hddusage double,
	hddpeak double
);

CREATE TABLE service (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
	mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	muser integer NOT NULL,
	name text NOT NULL,
	version text NOT NULL,
    status text NOT NULL,
	server integer NOT NULL,
	alias text,
	description text,
	tags text
);

CREATE TABLE cart (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	user integer NOT NULL,
    status text NOT NULL
);

CREATE TABLE product (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
	mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	muser integer NOT NULL,
	name text NOT NULL,
    status text NOT NULL,
    category int NULL,
	costPrice double NOT NULL,
	listPrice double NOT NULL,
	salePrice double NOT NULL,
    parent int NULL
);

CREATE TABLE category (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
	mdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	muser integer NOT NULL,
	name text NOT NULL,
    status text NOT NULL
);

CREATE TABLE purchase (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	name text NOT NULL,
	user integer NOT NULL,
	date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status text NOT NULL
);

CREATE TABLE invoice (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
	mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	muser integer NOT NULL,
	name text NOT NULL,
	purchase integer NOT NULL,
	user integer NOT NULL,
    month   integer NOT NULL,
    year   integer NOT NULL,
    description text NOT NULL,
    price   double NOT NULL,
    currency text NOT NULL DEFAULT 'AUD',
    status text NOT NULL
);

CREATE TABLE invoiceline (
	id	INTEGER PRIMARY KEY AUTOINCREMENT,
	cdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	cuser integer NOT NULL,
	mdate 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	muser integer NOT NULL,
	name text NOT NULL,
	invoice integer NOT NULL,
    description text NOT NULL,
    price   double NOT NULL,
    currency text NOT NULL DEFAULT 'AUD',
    status text NOT NULL
);

CREATE TABLE userRole (
	user	INTEGER NOT NULL,
	role	INTEGER NOT NULL
);

CREATE TABLE cartProduct (
	cart	INTEGER NOT NULL,
	product	INTEGER NOT NULL
);

CREATE TABLE purchaseProduct (
	purchase	INTEGER NOT NULL,
	product	INTEGER NOT NULL
);

