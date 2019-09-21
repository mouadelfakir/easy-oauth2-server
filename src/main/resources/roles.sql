drop table if exists `users`;
drop table if exists `authorities`;
CREATE TABLE `users` (
  `USERNAME` VARCHAR(50) BINARY NOT NULL,
  `PASSWORD` VARCHAR(500) NOT NULL,
  `ENABLED` boolean not null,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `authorities` (
  `USERNAME` VARCHAR(20) NOT NULL,
  `AUTHORITY` VARCHAR(20) NOT NULL)
  ENGINE=InnoDB;

insert into users values ('admin', '{noop}nimda', 1);

--"{noop}nimda, ROLE_DATAFLOW.VIEW, ROLE_DATAFLOW.MANAGE, ROLE_DATAFLOW.CREATE, ROLE_DATAFLOW.SCHEDULE, ROLE_DATAFLOW.MODIFY, ROLE_DATAFLOW.DEPLOY, ROLE_DATAFLOW.DESTROY"

insert into authorities values ('admin', 'ROLE_DATAFLOW.VIEW');
insert into authorities values ('admin', 'ROLE_DATAFLOW.MANAGE');
insert into authorities values ('admin', 'ROLE_DATAFLOW.CREATE');
insert into authorities values ('admin', 'ROLE_DATAFLOW.SCHEDULE');
insert into authorities values ('admin', 'ROLE_DATAFLOW.MODIFY');
insert into authorities values ('admin', 'ROLE_DATAFLOW.DEPLOY');
insert into authorities values ('admin', 'ROLE_DATAFLOW.DESTROY');