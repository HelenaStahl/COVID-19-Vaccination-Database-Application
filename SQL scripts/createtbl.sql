CONNECT TO cs421;

CREATE TABLE Person (
    hinsurnum VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    dateOfBirth DATE NOT NULL,
    gender VARCHAR(30) NOT NULL,
    postalCode VARCHAR(30) NOT NULL,
    streetAddress VARCHAR(30) NOT NULL,
    registrationDate DATE NOT NULL,
    category VARCHAR(30) NOT NULL,
    priority INT NOT NULL,
    PRIMARY KEY (hinsurnum)
);

CREATE TABLE DesignatedVaccineLocation (
    locationName VARCHAR(30) NOT NULL,
    city VARCHAR(30) NOT NULL,
    postalCode VARCHAR(30) NOT NULL,
    streetAddress VARCHAR(40) NOT NULL,
    PRIMARY KEY (locationName)
);

CREATE TABLE Hospital (
    locationName VARCHAR(30) NOT NULL,
    PRIMARY KEY (locationName),
    FOREIGN KEY (locationName) REFERENCES DesignatedVaccineLocation
);

CREATE TABLE Nurse (
    licenseNumber INT NOT NULL,
    nurseName VARCHAR(30) NOT NULL,
    locationName VARCHAR(30) NOT NULL,
    PRIMARY KEY (licenseNumber),
    FOREIGN KEY (locationName) REFERENCES Hospital(locationName)
);

CREATE TABLE VaccinationDates
(
    date DATE NOT NULL,
    locationName VARCHAR(30) NOT NULL,
    PRIMARY KEY (date, locationName),
    FOREIGN KEY (locationName) REFERENCES DesignatedVaccineLocation(locationName)
);

CREATE TABLE Vaccine (
    vaccineName VARCHAR(30) NOT NULL,
    numberOfDoses INT NOT NULL,
    waitingPeriod INT NOT NULL,
    PRIMARY KEY (vaccineName)
);

CREATE TABLE Batch (
    batchNumber INT NOT NULL,
    vaccineName VARCHAR(30) NOT NULL,
    numberOfVials INT NOT NULL,
    expiryDate DATE NOT NULL,
    manufacturingDate DATE NOT NULL,
    locationName VARCHAR(30) NOT NULL,
    CHECK (manufacturingDate < expiryDate),
    PRIMARY KEY (batchNumber, vaccineName),
    FOREIGN KEY (vaccineName) REFERENCES Vaccine(vaccineName)
);

CREATE TABLE Vial (
    vialNumber INT NOT NULL,
    batchNumber INT NOT NULL,
    vaccineName VARCHAR(30) NOT NULL,
    PRIMARY KEY (vialNumber, batchNumber, vaccineName),
    FOREIGN KEY (batchNumber, vaccineName) REFERENCES Batch(batchNumber, vaccineName)
);

CREATE TABLE Slot (
    slotNumber INT NOT NULL,
    time TIME NOT NULL,
    date DATE NOT NULL,
    locationName VARCHAR(30) NOT NULL,
    hinsurnum VARCHAR(30),
    allocateDate DATE,
    licenseNumber INT,
    vialNumber INT,
    batchNumber INT,
    vaccineName VARCHAR(30),
    PRIMARY KEY (slotNumber, time, date, locationName),
    FOREIGN KEY (date, locationName) REFERENCES VaccinationDates(date, locationName),
    FOREIGN KEY (licenseNumber) REFERENCES Nurse(licenseNumber),
    FOREIGN KEY (hinsurnum) REFERENCES Person(hinsurnum),
    FOREIGN KEY (vialNumber, batchNumber, vaccineName) REFERENCES Vial(vialNumber, batchNumber, vaccineName)
);

CREATE TABLE NurseWorkAssignments (
    licenseNumber INT NOT NULL,
    date DATE NOT NULL,
    locationName VARCHAR(30) NOT NULL,
    PRIMARY KEY (licenseNumber, date, locationName),
    FOREIGN KEY (licenseNumber) REFERENCES Nurse(licenseNumber),
    FOREIGN KEY (date, locationName) REFERENCES VaccinationDates(date, locationName)
);
