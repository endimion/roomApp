CREATE TABLE "host" (
  "id"  SERIAL PRIMARY KEY NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "email" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "hub" (
  "id" SERIAL PRIMARY KEY,
  "host_id" BIGINT NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "location" VARCHAR(200) NOT NULL
);

CREATE TABLE "room" (
  "id" SERIAL PRIMARY KEY,
  "hub_id" INTEGER NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "number_of_beds" INTEGER NOT NULL
);

CREATE TABLE "reservation" (
  "id" SERIAL PRIMARY KEY,
  "room_id" INTEGER NOT NULL,
  "email" VARCHAR(50) NOT NULL,
  "check_in" DATE NOT NULL,
  "check_out" DATE NOT NULL,
  "persons" INTEGER NOT NULL,
  "children" INTEGER NOT NULL,
  "babies" INTEGER NOT NULL,
  "status" INTEGER NOT NULL,
  "true_check_in" DATE,
  "true_check_out" DATE
);

CREATE TABLE "reservation_status" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR
);

CREATE TABLE "employee" (
  "id" SERIAL PRIMARY KEY,
  "Name" VARCHAR(50) NOT NULL,
  "Email" VARCHAR(50) UNIQUE NOT NULL,
  "host_id" BIGINT NOT NULL,
  "Type" INTEGER NOT NULL,
  "PhoneNumber" VARCHAR(50) NOT NULL,
  "HasVehicle" BOOLEAN NOT NULL,
  "VehicleType" INTEGER NOT NULL
);

CREATE TABLE "employee_type" (
  "id" SERIAL PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "vehicle_type" (
  "id" SERIAL PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "task_assignment" (
  "id" SERIAL PRIMARY KEY,
  "Requestid" BIGINT NOT NULL,
  "Employeeid" INTEGER NOT NULL,
  "status" INTEGER NOT NULL
);

CREATE TABLE "service" (
  "id" SERIAL PRIMARY KEY,
  "ServiceType" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL,
  "VehicleType" INTEGER NOT NULL
);

CREATE TABLE "service_type" (
  "id" SERIAL PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "assignment_status" (
  "id" SERIAL PRIMARY KEY,
  "status" VARCHAR
);

CREATE TABLE "request" (
  "id" SERIAL PRIMARY KEY,
  "Reservationid" BIGINT NOT NULL,
  "Email" VARCHAR(150) NOT NULL,
  "Serviceid" INTEGER NOT NULL
);

CREATE TABLE "room_services" (
  "id" SERIAL PRIMARY KEY,
  "Roomid" INTEGER NOT NULL,
  "Serviceid" INTEGER NOT NULL
);

CREATE TABLE "host_services" (
  "id" SERIAL PRIMARY KEY,
  "host_id" BIGINT NOT NULL,
  "Type" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL
);

ALTER TABLE "hub" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");

ALTER TABLE "room" ADD FOREIGN KEY ("hub_id") REFERENCES "hub" ("id");

ALTER TABLE "reservation" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "reservation" ADD FOREIGN KEY ("status") REFERENCES "reservation_status" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("Type") REFERENCES "employee_type" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("VehicleType") REFERENCES "vehicle_type" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("Requestid") REFERENCES "request" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("Employeeid") REFERENCES "employee" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("status") REFERENCES "assignment_status" ("id");

ALTER TABLE "service" ADD FOREIGN KEY ("ServiceType") REFERENCES "service_type" ("id");

ALTER TABLE "service" ADD FOREIGN KEY ("VehicleType") REFERENCES "vehicle_type" ("id");

ALTER TABLE "request" ADD FOREIGN KEY ("Reservationid") REFERENCES "reservation" ("id");

ALTER TABLE "request" ADD FOREIGN KEY ("Serviceid") REFERENCES "service" ("id");

ALTER TABLE "room_services" ADD FOREIGN KEY ("Roomid") REFERENCES "room" ("id");

ALTER TABLE "room_services" ADD FOREIGN KEY ("Serviceid") REFERENCES "service" ("id");

ALTER TABLE "host_services" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");