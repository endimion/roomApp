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
  "name" VARCHAR(50) NOT NULL,
  "email" VARCHAR(50) UNIQUE NOT NULL,
  "host_id" BIGINT NOT NULL,
  "type" INTEGER NOT NULL,
  "phone_number" VARCHAR(50) NOT NULL,
  "has_vehicle" BOOLEAN NOT NULL,
  "vehicle_type" INTEGER NOT NULL
);

CREATE TABLE "employee_type" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR
);

CREATE TABLE "vehicle_type" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR
);

CREATE TABLE "task_assignment" (
  "id" SERIAL PRIMARY KEY,
  "request_id" BIGINT NOT NULL,
  "employee_id" INTEGER NOT NULL,
  "status" INTEGER NOT NULL
);

CREATE TABLE "offered_service" (
  "id" SERIAL PRIMARY KEY,
  "service_type" INTEGER NOT NULL,
  "name" VARCHAR(50) NOT NULL,
  "vehicle_type" INTEGER NOT NULL
);

CREATE TABLE "service_type" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR
);

CREATE TABLE "assignment_status" (
  "id" SERIAL PRIMARY KEY,
  "status" VARCHAR
);

CREATE TABLE "request" (
  "id" SERIAL PRIMARY KEY,
  "reservation_id" BIGINT NOT NULL,
  "email" VARCHAR(150) NOT NULL,
  "service_id" INTEGER NOT NULL
);

CREATE TABLE "room_services" (
  "id" SERIAL PRIMARY KEY,
  "room_id" INTEGER NOT NULL,
  "service_id" INTEGER NOT NULL
);

CREATE TABLE "host_services" (
  "id" SERIAL PRIMARY KEY,
  "host_id" BIGINT NOT NULL,
  "type" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL
);

ALTER TABLE "hub" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");

ALTER TABLE "room" ADD FOREIGN KEY ("hub_id") REFERENCES "hub" ("id");

ALTER TABLE "reservation" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "reservation" ADD FOREIGN KEY ("status") REFERENCES "reservation_status" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("type") REFERENCES "employee_type" ("id");

ALTER TABLE "employee" ADD FOREIGN KEY ("vehicle_type") REFERENCES "vehicle_type" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("request_id") REFERENCES "request" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("employee_id") REFERENCES "employee" ("id");

ALTER TABLE "task_assignment" ADD FOREIGN KEY ("status") REFERENCES "assignment_status" ("id");

ALTER TABLE "offered_service" ADD FOREIGN KEY ("service_type") REFERENCES "service_type" ("id");

ALTER TABLE "offered_service" ADD FOREIGN KEY ("vehicle_type") REFERENCES "vehicle_type" ("id");

ALTER TABLE "request" ADD FOREIGN KEY ("reservation_id") REFERENCES "reservation" ("id");

ALTER TABLE "request" ADD FOREIGN KEY ("service_id") REFERENCES "offered_service" ("id");

ALTER TABLE "room_services" ADD FOREIGN KEY ("room_id") REFERENCES "room" ("id");

ALTER TABLE "room_services" ADD FOREIGN KEY ("service_id") REFERENCES "offered_service" ("id");

ALTER TABLE "host_services" ADD FOREIGN KEY ("host_id") REFERENCES "host" ("id");