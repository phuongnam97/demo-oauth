CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- insert into users (username, password, uuid, created_time, last_updated_time, voided_time) values
-- ('sysadmin', '$2a$08$4tvf.oUCSPHd5W8/pLaMVe5TAXgD3gXVxXkBuy5A0FVfjQLah.MPC', uuid_generate_v4(), current_timestamp, current_timestamp, current_timestamp),
-- ('huong', '$2a$08$4tvf.oUCSPHd5W8/pLaMVe5TAXgD3gXVxXkBuy5A0FVfjQLah.MPC', uuid_generate_v4(), current_timestamp, current_timestamp, current_timestamp);
-- -- create default user (sysadmin / iTech1234) (huong / iTech1234)

insert into roles (name, uuid, created_time, last_updated_time ) values ('USER', uuid_generate_v4(), current_timestamp , current_timestamp ),
('ADMIN', uuid_generate_v4(), current_timestamp , current_timestamp );