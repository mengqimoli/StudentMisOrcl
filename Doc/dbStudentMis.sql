-- 班级表
drop table [Class];
create table [Class]
(
	ID			nvarchar(20)	not null,		-- ID,班级编号
	Name		nvarchar(40)	not null,		-- 名称
	constraint PK_Class primary key(ID)
);

-- 学生表
drop table Student;
create table Student
(
	ID			nvarchar(20)	not null,		-- 学号
	ClassID		nvarchar(20)	not null,		-- 班级ID,外键,参考Class(ID)
	Name		nvarchar(40)	not null,		-- 姓名
	Birthday	datetime		not null,		-- 出生日期
	Age			int				not null,		-- 年龄
	Height		decimal(10,2)	not null,		-- 身高,单位米,保留2位小数
	constraint PK_Student primary key(ID),
	constraint FK_Student_Class foreign key(ClassID) references [Class](ID)
);
