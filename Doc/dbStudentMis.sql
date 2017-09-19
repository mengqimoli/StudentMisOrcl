-- �༶��
drop table [Class];
create table [Class]
(
	ID			nvarchar(20)	not null,		-- ID,�༶���
	Name		nvarchar(40)	not null,		-- ����
	constraint PK_Class primary key(ID)
);

-- ѧ����
drop table Student;
create table Student
(
	ID			nvarchar(20)	not null,		-- ѧ��
	ClassID		nvarchar(20)	not null,		-- �༶ID,���,�ο�Class(ID)
	Name		nvarchar(40)	not null,		-- ����
	Birthday	datetime		not null,		-- ��������
	Age			int				not null,		-- ����
	Height		decimal(10,2)	not null,		-- ���,��λ��,����2λС��
	constraint PK_Student primary key(ID),
	constraint FK_Student_Class foreign key(ClassID) references [Class](ID)
);
