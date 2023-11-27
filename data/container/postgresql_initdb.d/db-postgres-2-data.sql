insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Kilian', 'Jornet', '1987-10-27', 'kilian.jornet@mail.com', 'Spain', null, TRUE, 'Kilian Jornet', now(), null, '@Kilianjornet', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Xavier', 'Thevenard', '1988-03-06', 'xavier.thevenard@mail.com', 'France', null, TRUE, 'Xavier Thevenard', now(), null, '@xavierthevenard', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Pau', 'Capell', '1991-09-10', 'pau.capell@mail.com', 'Spain', null, TRUE, 'Pau Capell', now(), null, '@PauCapell', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Ryan', 'Sandes', '1982-03-10', 'ryan.sandes@mail.com', 'South Africa', null, TRUE, 'Ryan Sandes', now(), null, '@Ryansandes', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Jim', 'Walmsley', '1990-01-17', 'jim.walmsley@mail.com', 'United States', null, TRUE, 'Jim Walmsley', now(), null, '@walmsleyruns', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'François', 'D''haene', '1985-12-24', 'francois.dhaene@mail.com', 'France', null, TRUE, 'Francois D''Haene', now(), null, '@francois_dhaene', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Mathieu', 'Blanchard', '1987-12-03', 'mathieu.blanchard@mail.com', 'France', null, TRUE, 'Mathieu Blanchard', now(), null, '@mathieu__blanchard', 'trailerplan');
insert into P_USER values (nextval('P_USER_ID_SEQ'), 'Buon', 'Sui', '1974-01-16', 'buon.sui@tcs.com', 'France', null, TRUE, 'Buon Sui', now(), null, '@buennodelavega', 'trailerplan');

insert into P_ROLE values (nextval('P_ROLE_ID_SEQ'), 'ROLE_USER');
insert into P_ROLE values (nextval('P_ROLE_ID_SEQ'), 'ROLE_RUNNER');
insert into P_ROLE values (nextval('P_ROLE_ID_SEQ'), 'ROLE_ADMIN');
insert into P_ROLE values (nextval('P_ROLE_ID_SEQ'), 'ROLE_MODERATOR');

insert into A_USER_ROLE values (11, 102);
insert into A_USER_ROLE values (12, 102);
insert into A_USER_ROLE values (13, 102);
insert into A_USER_ROLE values (14, 102);
insert into A_USER_ROLE values (15, 102);
insert into A_USER_ROLE values (16, 102);
insert into A_USER_ROLE values (17, 102);
insert into A_USER_ROLE values (18, 101);
insert into A_USER_ROLE values (18, 102);
insert into A_USER_ROLE values (18, 103);
insert into A_USER_ROLE values (18, 104);
