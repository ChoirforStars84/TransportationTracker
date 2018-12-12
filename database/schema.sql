-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL,
  phone_number varchar(10) NULL
);

DROP TABLE IF EXISTS bus_stops;

CREATE TABLE bus_stops (
  internalid varchar(8) PRIMARY KEY,
  name varchar(128) NOT NULL,
  externalid varchar(8) NOT NULL,
  direction varchar(16) NOT NULL,
  lat varchar(16) NOT NULL,
  long varchar(16) NOT NULL,
  time_point varchar(8) NOT NULL,
  newzone varchar(8) NOT NULL,
  no_rts_served varchar(8) NOT NULL,
  routes varchar(128) NOT NULL, 
  mode varchar(16) NOT NULL,
  public_shelter varchar(32) NOT NULL,
  public_stop varchar(32) NOT NULL
  
);

DROP TABLE IF EXISTS bus_lines;

CREATE TABLE bus_lines (
	number varchar(8) PRIMARY KEY,
	name varchar(64)

);



DROP TABLE IF EXISTS stops_lines;

CREATE TABLE stops_lines (
	bus_line varchar(16),
	stop_id varchar(8),
	
	constraint pk_stops_lines primary key (bus_line, stop_id),
    constraint fk_stops_lines_bus_line foreign key (bus_line) references bus_lines (number),
    constraint fk_stops_lines_stop_id foreign key (stop_id) references stops (internalid)

);



COMMIT;