create database spider;
use spider;
create table t_admin_setting(
  `key` varchar(255) primary key,
  `value` varchar(255)
);

create table t_summoners(
  id varchar(255) primary key,
  account_id varchar(255),
  puuid varchar(255),
  name varchar(255),
  profile_icon_id int,
  revision_date bigint(20),
  summoner_level int,
  is_read int,
  area varchar(255)
);

create table t_match(
  game_id varchar(255) primary key,
  platform_id varchar(255),
  champion bigint(20),
  queue bigint(20),
  season bigint(20),
  timestamp bigint(20),
  role varchar(255),
  lane varchar(255),
  area varchar(255),
  is_read int
);

insert into t_admin_setting values('apikey', 'RGAPI-5cf28ce1-2b97-4141-869b-e4b3f25e186e');
insert into t_admin_setting values('summoner_task_running', '0');
insert into t_admin_setting values('match_task_running', '0');



create table t_match_0204(
  game_id varchar(255) primary key,
  platform_id varchar(255),
  champion bigint(20),
  queue bigint(20),
  season bigint(20),
  timestamp bigint(20),
  role varchar(255),
  lane varchar(255),
  area varchar(255),
  is_read int
);

create table t_summoners_0204(
  id varchar(255) primary key,
  account_id varchar(255),
  puuid varchar(255),
  name varchar(255),
  profile_icon_id int,
  revision_date bigint(20),
  summoner_level int,
  is_read int,
  area varchar(255)
);

insert into t_summoners_0204 as select * from t_summoners where is_read = 0 limit 1000;

update t_summoners set is_read = 3 limit 1000;