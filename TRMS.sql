create table employee_type (
	"employee_type_id" serial,
	"employee_type_name" varchar(255),
	primary key ("employee_type_id")
); 

create table department (
	"department_id" serial,
	"name" varchar(255),
	primary key ("department_id")
);

CREATE TABLE employee (
  "employee_id" serial,
  "type" int,
  "reports_to" int,
  "first_name" varchar(255),
  "last_name" varchar(255), 
  "email" varchar(255),
  "award_amount" int,
  "pending_amount" int,
  "department" int,
  "ben_co" int,
  PRIMARY KEY ("employee_id"),
  foreign key ("type")
  references employee_type(employee_type_id)
  on delete cascade,
  foreign key ("reports_to")
  references employee(employee_id)
  on delete cascade,
  foreign key ("department")
  references department(department_id)
  on delete cascade,
  foreign key ("ben_co")
  references employee(employee_id)
  on delete cascade, 
  unique (email)
);

insert into employee_type (employee_type_name) values('vanilla');
insert into employee (first_name) values('m');
insert into department (name) values('testd');

CREATE TABLE event (
  "event_id" serial,
  "date" varchar(255),
  "time" varchar(255), 
  "location" varchar(255), 
  "description" varchar(255),
  "cost" int,
  "grading_format" int,
  "type" int,
  PRIMARY KEY ("event_id"), 
  foreign key ("type")
  references event_type(event_type_id)
  on delete cascade,
  foreign key ("grading_format")
  references grading_format(grading_format_id)
  on delete cascade
);

create table event_type (
	"event_type_id" serial,
	"event_type_name" varchar(255),
	"compensation_percentage" int,
	primary key ("event_type_id")
);


create table grading_format ( 
	"grading_format_id" serial,
	"name" varchar(255),
	"passing_grade" varchar(255),
	primary key ("grading_format_id")
	
);

create table message (
	"message_id" serial,
	"sender" varchar(255),
	"recipient_id" varchar(255),
	"contents" varchar(255),
	"subject" varchar(255),
	primary key ("message_id"),
	foreign key ("recipient_id")
	references employee(email)
);

insert into event_type(event_type_name, compensation_percentage) values ('University Course', 80);
insert into event_type(event_type_name, compensation_percentage) values ('Seminar', 60);
insert into event_type(event_type_name, compensation_percentage) values ('Certification Preparation Class', 75);
insert into event_type(event_type_name, compensation_percentage) values ('Certification', 100);
insert into event_type(event_type_name, compensation_percentage) values ('Technical Training', 90);
insert into event_type(event_type_name, compensation_percentage) values ('Other', 30);

insert into grading_format(name, passing_grade) values ('test', 'A');
insert into grading_format(name, passing_grade) values ('test_again', 'C');

create table request (
	"request_id" serial,
	"employee_id" int, 
	"event_id" int,
	"justification" varchar(255),
	"date" varchar(255),
	"ds_approved" boolean, 
	"dh_approved" boolean,
	"ben_co_approved" boolean,
	"current_worker" int,
	"complete" boolean,
	"urgent" boolean,
	"attachment" varchar(255),
	"hours_missed" int,
	"ds_approval_proof" varchar(255),
	"dh_approval_proof" varchar(255),
	primary key ("request_id"),
	foreign key ("employee_id")
	references employee(employee_id)
	on delete cascade,
	foreign key ("event_id")
	references event(event_id)
	on delete cascade
);

create table award (
	"award_id" serial,
	"request_id" int,
	"value" int,
	"justification" varchar(255),
	"awarded" boolean,
	"exceeding" boolean,
	"accepted" boolean,
	primary key ("award_id"),
	foreign key ("request_id")
	references request(request_id)
);

create table grade (
	"grade_id" serial,
	"grade" varchar(255),
	"request_id" int,
	primary key ("grade_id"),
	foreign key ("request_id")
	references request(request_id) on delete cascade
);