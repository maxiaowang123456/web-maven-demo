SET FOREIGN_KEY_CHECKS=0;
drop table if exists t_female_health_form;
drop table if exists t_male_health_form;
drop table if exists t_task;
drop table if exists  t_work_card;
drop table if exists t_employee_task;
drop  table if exists t_employee;
SET FOREIGN_KEY_CHECKS=1;

create table t_employee(
  id int(12) not null auto_increment,
  real_name varchar(60) not null,
  sex int(2) not null comment '1 - 男 0 - 女',
  birthday date not null,
  mobile varchar(20) not null,
  email varchar(60) not null,
  position varchar(20) not null,
  note varchar(256),
  primary key (id)
);

create  table  t_employee_task(
  id int(12) not null,
  emp_id int(12) not null,
  task_id int(12) not null,
  task_name varchar(60) not null,
  note varchar(256),
  primary key(id)
);

create table t_female_health_form(
  id int(12) not null auto_increment,
  emp_id int(12) not null,
  heart varchar(64) not null,
  liver varchar (64) not null,
  spleen varchar (64) not null,
  lung varchar (64) not null,
  kidney varchar (64) not null,
  uterus varchar (64) not null,
  note varchar (256) not null,
  primary key (id)
);

create table t_male_health_form(
  id int(12) not null auto_increment,
  emp_id int(12) not null,
  heart varchar(64) not null,
  liver varchar (64) not null,
  spleen varchar (64) not null,
  lung varchar (64) not null,
  kidney varchar (64) not null,
  uterus varchar (64) not null,
  note varchar (256) not null,
  primary key (id)
);

create table t_task(
  id int(12) not null,
  title varchar (60) not null,
  context varchar (256) not null,
  note varchar (256),
  primary key (id)
);

create table t_work_card(
  id int(12) not null auto_increment,
  emp_id int(12) not null,
  real_name varchar (60) not null,
  department varchar (20) not null,
  mobile varchar (20) not null,
  position varchar (30) not null,
  note varchar (256),
  primary key (id)
);

alter table t_employee_task add constraint fk_emp_task_empId foreign key(emp_id)
  references t_employee(id) on delete restrict on update restrict;
alter table t_employee_task add constraint fk_emp_task_taskId foreign key(task_id)
  references t_task(id) on delete restrict on update restrict;
alter table t_female_health_form add constraint fk_fm_heal_empId foreign key(emp_id)
  references t_employee(id) on delete restrict on update restrict;
alter table t_male_health_form add constraint fk_male_heal_empId foreign key(emp_id)
  references t_employee(id) on delete restrict on update restrict;
alter table t_work_card add constraint fk_work_card_empId foreign key(emp_id)
  references t_employee(id) on delete restrict on update restrict;

insert into t_employee(id,real_name,sex,mobile,email,birthday,position)
  values(1,'zs',1,'15210029416','123456789@qq.com','2008-08-08','beijing');
insert into t_task(id,title,context)values(1,'todolist','task is very well');
insert into t_employee_task(id,emp_id,task_id,task_name)values(1,1,1,'todolist');

delimiter $$
create procedure pro_employee(in v_real_name varchar(60),out total int,out exe_date date)
begin
select count(*) into total from t_employee where real_name like concat('%',v_real_name,'%');
select current_date into exe_date from dual;
end $$
delimiter ;
