create table tblTest(
    seq NUMBER NULL,
    NAME VARCHAR2(50) NULL,
    num NUMBER NULL
);

insert into tblTest values(1,'황',8);
insert into tblTest values(2,'현',9);
insert into tblTest values(3,'우',10);

commit;
select * from tblTest;