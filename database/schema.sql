-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user cascade;
DROP TABLE IF EXISTS bus_stops cascade;
DROP TABLE IF EXISTS bus_lines cascade;
DROP TABLE IF EXISTS stops_lines cascade;
DROP TABLE IF EXISTS saved_routes cascade;
DROP TABLE IF EXISTS routes_users cascade;


CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL,
  phone_number varchar(10) NULL
);


CREATE TABLE bus_stops (
  internalid varchar(16) PRIMARY KEY,
  name varchar(128) NOT NULL,
  externalid varchar(16) NOT NULL,
  direction varchar(16) NOT NULL,
  lat varchar(16) NOT NULL,
  long varchar(16) NOT NULL,
  time_point varchar(16) NOT NULL,
  newzone varchar(16) NOT NULL,
  no_rts_served varchar(16) NOT NULL,
  routes varchar(128) NOT NULL, 
  mode varchar(16) NOT NULL,
  public_shelter varchar(32) NOT NULL,
  public_stop varchar(32) NOT NULL
  
);



CREATE TABLE bus_lines (
	number varchar(8) PRIMARY KEY,
	name varchar(64) NOT NULL,
	rtclr varchar(16) NULL,
	rtdd varchar(8) NULL,
	rtpidatafeed varchar(32) NULL

);





CREATE TABLE stops_lines (
	stop_id varchar(8) NOT NULL,
	bus_line varchar(16) NOT NULL,
	
	
	constraint pk_stops_lines primary key (bus_line, stop_id),
    constraint fk_stops_lines_bus_line foreign key (bus_line) references bus_lines (number),
    constraint fk_stops_lines_stop_id foreign key (stop_id) references bus_stops (internalid)

);




CREATE TABLE saved_routes(
         id  SERIAL PRIMARY KEY,
         user_id int NOT NULL REFERENCES app_user (id),
         start_pt varchar(256) NOT NULL,
         end_pt varchar(256) NOT NULL
         
);




CREATE TABLE routes_users(
        route_id int NOT NULL,
        user_id int NOT NULL,
        permissions varchar (32) NULL, --view or edit
        
        constraint pk_routes_users primary key (route_id, user_id),
        constraint fk_routes_users_route_id foreign key (route_id) references saved_routes (id),
        constraint fk_routes_users_user_id foreign key (user_id) references app_user (id)
);




COMMIT;