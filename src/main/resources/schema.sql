CREATE TABLE "public.Host" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR(50) NOT NULL,
  "Email" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "public.Hub" (
  "Id" BIGINT PRIMARY KEY,
  "HostId" BIGINT NOT NULL,
  "Name" VARCHAR(50) NOT NULL,
  "Location" VARCHAR(200) NOT NULL
);

CREATE TABLE "public.Room" (
  "Id" BIGINT PRIMARY KEY,
  "HubId" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL,
  "NumberOfBeds" INTEGER NOT NULL
);

CREATE TABLE "public.Reservation" (
  "Id" BIGINT PRIMARY KEY,
  "RoomId" INTEGER NOT NULL,
  "Email" VARCHAR(50) NOT NULL,
  "CheckIn" DATE NOT NULL,
  "CheckOut" DATE NOT NULL,
  "Persons" INTEGER NOT NULL,
  "Children" INTEGER NOT NULL,
  "Babies" INTEGER NOT NULL,
  "Status" INTEGER NOT NULL,
  "TrueCheckIn" DATE,
  "TrueCheckOut" DATE
);

CREATE TABLE "public.ReservationStatus" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "public.Employee" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR(50) NOT NULL,
  "Email" VARCHAR(50) UNIQUE NOT NULL,
  "HostId" BIGINT NOT NULL,
  "Type" INTEGER NOT NULL,
  "PhoneNumber" VARCHAR(50) NOT NULL,
  "HasVehicle" BOOLEAN NOT NULL,
  "VehicleType" INTEGER NOT NULL
);

CREATE TABLE "public.EmployeeType" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "public.VehicleType" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "public.TaskAssignment" (
  "Id" BIGINT PRIMARY KEY,
  "RequestId" BIGINT NOT NULL,
  "EmployeeId" INTEGER NOT NULL,
  "Status" INTEGER NOT NULL
);

CREATE TABLE "public.Service" (
  "Id" BIGINT PRIMARY KEY,
  "ServiceType" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL,
  "VehicleType" INTEGER NOT NULL
);

CREATE TABLE "public.ServiceType" (
  "Id" BIGINT PRIMARY KEY,
  "Name" VARCHAR
);

CREATE TABLE "public.AssignmentStatus" (
  "Id" BIGINT PRIMARY KEY,
  "Status" VARCHAR
);

CREATE TABLE "public.Request" (
  "Id" BIGINT PRIMARY KEY,
  "ReservationId" BIGINT NOT NULL,
  "Email" VARCHAR(150) NOT NULL,
  "ServiceId" INTEGER NOT NULL
);

CREATE TABLE "public.RoomServices" (
  "Id" BIGINT PRIMARY KEY,
  "RoomId" INTEGER NOT NULL,
  "ServiceId" INTEGER NOT NULL
);

CREATE TABLE "public.HostServices" (
  "Id" BIGINT PRIMARY KEY,
  "HostId" BIGINT NOT NULL,
  "Type" INTEGER NOT NULL,
  "Name" VARCHAR(50) NOT NULL
);

ALTER TABLE "public.Hub" ADD FOREIGN KEY ("HostId") REFERENCES "public.Host" ("Id");

ALTER TABLE "public.Room" ADD FOREIGN KEY ("HubId") REFERENCES "public.Hub" ("Id");

ALTER TABLE "public.Reservation" ADD FOREIGN KEY ("RoomId") REFERENCES "public.Room" ("Id");

ALTER TABLE "public.Reservation" ADD FOREIGN KEY ("Status") REFERENCES "public.ReservationStatus" ("Id");

ALTER TABLE "public.Employee" ADD FOREIGN KEY ("HostId") REFERENCES "public.Host" ("Id");

ALTER TABLE "public.Employee" ADD FOREIGN KEY ("Type") REFERENCES "public.EmployeeType" ("Id");

ALTER TABLE "public.Employee" ADD FOREIGN KEY ("VehicleType") REFERENCES "public.VehicleType" ("Id");

ALTER TABLE "public.TaskAssignment" ADD FOREIGN KEY ("RequestId") REFERENCES "public.Request" ("ReservationId");

ALTER TABLE "public.TaskAssignment" ADD FOREIGN KEY ("EmployeeId") REFERENCES "public.Employee" ("Id");

ALTER TABLE "public.TaskAssignment" ADD FOREIGN KEY ("Status") REFERENCES "public.AssignmentStatus" ("Id");

ALTER TABLE "public.Service" ADD FOREIGN KEY ("ServiceType") REFERENCES "public.ServiceType" ("Id");

ALTER TABLE "public.Service" ADD FOREIGN KEY ("VehicleType") REFERENCES "public.VehicleType" ("Id");

ALTER TABLE "public.Request" ADD FOREIGN KEY ("ReservationId") REFERENCES "public.Reservation" ("Id");

ALTER TABLE "public.Request" ADD FOREIGN KEY ("ServiceId") REFERENCES "public.Service" ("Id");

ALTER TABLE "public.RoomServices" ADD FOREIGN KEY ("RoomId") REFERENCES "public.Room" ("Id");

ALTER TABLE "public.RoomServices" ADD FOREIGN KEY ("ServiceId") REFERENCES "public.Service" ("Id");

ALTER TABLE "public.HostServices" ADD FOREIGN KEY ("HostId") REFERENCES "public.Host" ("Id");