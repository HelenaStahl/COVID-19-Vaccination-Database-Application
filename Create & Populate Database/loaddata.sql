CONNECT TO cs421;

INSERT INTO DesignatedVaccineLocation VALUES ('Jewish General', 'Montreal', 'H3T 1E2', '3755 Chemin de la Côte-Sainte-Catherine');
INSERT INTO DesignatedVaccineLocation VALUES ('Montreal General', 'Montreal', 'H3G 1A4', '1650 Cedar Ave');
INSERT INTO DesignatedVaccineLocation VALUES ('Notre Dame', 'Montreal', 'H2L 4M1', '1560 Sherbrooke St E');
INSERT INTO DesignatedVaccineLocation VALUES ('Jeffery Hale Hospital', 'Quebec City', 'G1S 2M6', '1250 Chemin Sainte-Foy');
INSERT INTO DesignatedVaccineLocation VALUES ('Hospital Saint-Sacrement', 'Quebec City', 'G1S 4L8', '1050 Ch Ste-Foy');

INSERT INTO VaccinationDates VALUES ('2021-03-20', 'Jewish General');
INSERT INTO VaccinationDates VALUES ('2021-02-06', 'Jewish General');
INSERT INTO VaccinationDates VALUES ('2021-02-6', 'Montreal General');
INSERT INTO VaccinationDates VALUES ('2021-02-6', 'Notre Dame');
INSERT INTO VaccinationDates VALUES ('2021-02-6', 'Jeffery Hale Hospital');
INSERT INTO VaccinationDates VALUES ('2021-02-08', 'Hospital Saint-Sacrement');
INSERT INTO VaccinationDates VALUES ('2021-02-07', 'Hospital Saint-Sacrement');
INSERT INTO VaccinationDates VALUES ('2021-01-10', 'Montreal General');
INSERT INTO VaccinationDates VALUES ('2021-01-15', 'Montreal General');
INSERT INTO VaccinationDates VALUES ('2021-01-28', 'Montreal General');


INSERT INTO Vaccine VALUES ('Pfizer-BioNTech', 2, 2);
INSERT INTO Vaccine VALUES ('Moderna', 2, 1);
INSERT INTO Vaccine VALUES ('Vaccine 3', 3, 2);
INSERT INTO Vaccine VALUES ('Vaccine 4', 2, 2);
INSERT INTO Vaccine VALUES ('Vaccine 5', 1, 3);

INSERT INTO Batch VALUES (1, 'Pfizer-BioNTech', 100, '2021-10-31', '2021-01-01', 'Jewish General');
INSERT INTO Batch VALUES (2, 'Pfizer-BioNTech', 200, '2021-12-05', '2021-01-02', 'Montreal General');
INSERT INTO Batch VALUES (1, 'Moderna', 50, '2022-10-18', '2021-01-25', 'Montreal General');
INSERT INTO Batch VALUES (2, 'Moderna', 150, '2022-10-25', '2021-01-31', 'Notre Dame');
INSERT INTO Batch VALUES (3, 'Moderna', 75, '2022-12-05', '2021-01-31', 'Jeffery Hale Hospital');
INSERT INTO Batch VALUES (3, 'Pfizer-BioNTech', 300, '2021-12-28', '2021-01-03', 'Hospital Saint-Sacrement');
INSERT INTO Batch VALUES (4, 'Pfizer-BioNTech', 75, '2021-12-30', '2021-01-04', 'Montreal General');


INSERT INTO Person VALUES ('4022-166-879-QO', 'Tom Smith', '647-333-9024', '1980-04-19', 'Male', 'H2V 0P3', '111 Parc Avenue', '2021-01-05', 'Essential Service Worker', 3);
INSERT INTO Person VALUES ('2033-811-990-SE', 'Jane Doe', '416-298-1181', '1960-08-12', 'Female', 'H1U 3D8', '845 Durocher Avenue', '2021-01-20', 'Everybody else', 4);
INSERT INTO Person VALUES ('3134-562-155-SD', 'Jack Nickels', '511-387-5242', '1983-03-22', 'Male', 'H9W 1A3', '9 Hutchison Avenue', '2021-01-25', 'Teacher', 2);
INSERT INTO Person VALUES ('2221-599-158-SW', 'Jenny Jacobs', '514-935-6111', '1990-06-13', 'Female', 'G3W 9C4', '444 Parc Avenue', '2021-01-05', 'Health Care Worker', 1);
INSERT INTO Person VALUES ('3410-002-792-DE', 'Peter Finnigan', '514-003-2743', '1940-01-24', 'Male', 'H9S 4W2', '8566 Bernard Avenue', '2021-01-06', 'Elderly', 1);
INSERT INTO Person VALUES ('2224-312-056-SX', 'Connor Murphy', '514-222-8451', '1994-04-26', 'Male', 'M56 7K0', '23 Bernard Avenue', '2021-03-04', 'Everybody else', 4);

INSERT INTO Vial VALUES (1, 1, 'Pfizer-BioNTech');
INSERT INTO Vial VALUES (2, 1, 'Pfizer-BioNTech');
INSERT INTO Vial VALUES (3, 1, 'Pfizer-BioNTech');
INSERT INTO Vial VALUES (1, 1, 'Moderna');
INSERT INTO Vial VALUES (1, 2, 'Moderna');
INSERT INTO Vial VALUES (1, 3, 'Moderna');
INSERT INTO Vial VALUES (1, 4, 'Pfizer-BioNTech');
INSERT INTO Vial VALUES (2, 4, 'Pfizer-BioNTech');
INSERT INTO Vial VALUES (3, 4, 'Pfizer-BioNTech');

INSERT INTO Hospital VALUES ('Jewish General');
INSERT INTO Hospital VALUES ('Montreal General');
INSERT INTO Hospital VALUES ('Notre Dame');
INSERT INTO Hospital VALUES ('Jeffery Hale Hospital');
INSERT INTO Hospital VALUES ('Hospital Saint-Sacrement');

INSERT INTO Nurse VALUES (12345, 'John Mitchell', 'Jewish General');
INSERT INTO Nurse VALUES (13579, 'Nancy Walker', 'Montreal General');
INSERT INTO Nurse VALUES (15565, 'Michael Spencer', 'Jeffery Hale Hospital');
INSERT INTO Nurse VALUES (19903, 'Marsha Birk', 'Jeffery Hale Hospital');
INSERT INTO Nurse VALUES (44424, 'Marie Mickelson', 'Hospital Saint-Sacrement');

INSERT INTO NurseWorkAssignments VALUES (12345, '2021-03-20', 'Jewish General');
INSERT INTO NurseWorkAssignments VALUES (13579, '2021-03-20', 'Jewish General');
INSERT INTO NurseWorkAssignments VALUES (13579, '2021-02-06', 'Jewish General');
INSERT INTO NurseWorkAssignments VALUES (15565, '2021-02-06', 'Montreal General');
INSERT INTO NurseWorkAssignments VALUES (15565, '2021-03-20', 'Jewish General');
INSERT INTO NurseWorkAssignments VALUES (19903, '2021-02-06', 'Jeffery Hale Hospital');
INSERT INTO NurseWorkAssignments VALUES (44424, '2021-02-07', 'Hospital Saint-Sacrement');
INSERT INTO NurseWorkAssignments VALUES (44424, '2021-02-06', 'Notre Dame');
INSERT INTO NurseWorkAssignments VALUES (19903, '2021-01-10', 'Montreal General');
INSERT INTO NurseWorkAssignments VALUES (19903, '2021-01-15', 'Montreal General');
INSERT INTO NurseWorkAssignments VALUES (12345, '2021-01-28', 'Montreal General');

INSERT INTO Slot VALUES (1, '13:30:00', '2021-03-20', 'Jewish General', '4022-166-879-QO', '2021-03-19', 12345, 1, 1, 'Pfizer-BioNTech');
INSERT INTO Slot VALUES (2, '13:45:00', '2021-03-20', 'Jewish General', NULL, NULL, 13579, NULL, NULL, NULL);
INSERT INTO Slot VALUES (3, '14:00:00', '2021-03-20', 'Jewish General', NULL, NULL, 15565, NULL, NULL, NULL);
INSERT INTO Slot VALUES (3, '14:00:00', '2021-02-06', 'Jewish General', NULL, NULL, 15565, NULL, NULL, NULL);
INSERT INTO Slot VALUES (1, '11:30:00', '2021-02-06', 'Montreal General', '2033-811-990-SE', '2021-02-03', '15565', 1, 1, 'Moderna');
INSERT INTO Slot VALUES (1, '9:00:00', '2021-02-06', 'Notre Dame', '4022-166-879-QO','2021-02-02', 44424, 1, 2, 'Moderna');
INSERT INTO Slot VALUES (1, '8:00:00', '2021-02-06', 'Jeffery Hale Hospital', '3134-562-155-SD', '2021-02-01', 19903, 1, 3, 'Moderna');
INSERT INTO Slot VALUES (1, '13:30:00', '2021-01-10', 'Montreal General', '2221-599-158-SW', '2021-01-08', 19903, 1, 4, 'Pfizer-BioNTech');
INSERT INTO Slot VALUES (1, '13:30:00', '2021-01-28', 'Montreal General', '2221-599-158-SW', '2021-01-20', 12345, 2, 4, 'Pfizer-BioNTech');
INSERT INTO Slot VALUES (1, '13:30:00', '2021-01-15', 'Montreal General', '3410-002-792-DE', '2021-01-12', 19903, 3, 4, 'Pfizer-BioNTech');
