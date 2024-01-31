INSERT INTO CONFERENCE_ROOMS (ID,NAME,CAPACITY) VALUES (1,'Amaze',3);
INSERT INTO CONFERENCE_ROOMS (ID,NAME,CAPACITY) VALUES (2,'Beauty',7);
INSERT INTO CONFERENCE_ROOMS (ID,NAME,CAPACITY) VALUES (3,'Inspire',12);
INSERT INTO CONFERENCE_ROOMS (ID,NAME,CAPACITY) VALUES (4,'Strive',20);

INSERT INTO maintenance_intervals (start_time, end_time, conference_room_id) VALUES
('09:00:00', '09:15:00', 1),
('13:00:00', '13:15:00', 1),
('17:00:00', '17:15:00', 1);


INSERT INTO maintenance_intervals (start_time, end_time, conference_room_id) VALUES
('09:00:00', '09:15:00', 2),
('13:00:00', '13:15:00', 2),
('17:00:00', '17:15:00', 2);

INSERT INTO maintenance_intervals (start_time, end_time, conference_room_id) VALUES
('09:00:00', '09:15:00', 3),
('13:00:00', '13:15:00', 3),
('17:00:00', '17:15:00', 3);

INSERT INTO maintenance_intervals (start_time, end_time, conference_room_id) VALUES
('09:00:00', '09:15:00', 4),
('13:00:00', '13:15:00', 4),
('17:00:00', '17:15:00', 4);
