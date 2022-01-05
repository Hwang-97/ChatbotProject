drop table tblQuestion;
drop table tblAnswer;
drop sequence seqQuestion;
drop sequence seqAnswer;

drop view vwqa;

create sequence seqQuestion;
create sequence seqAnswer;

create table tblAnswer(
    seq Number PRIMARY KEY,
    answer varchar2(4000) unique not null
);

create table tblQuestion(
    seq Number PRIMARY KEY,
    question varchar2(4000),
    answer_seq number REFERENCES tblAnswer(seq) not null
);

insert into tblAnswer values(seqAnswer.nextval,'안녕하세요');
insert into tblAnswer values(seqAnswer.nextval,'궁금한게 있으면 다시 찾아주세요!');
insert into tblAnswer values(seqAnswer.nextval,'고객센터 전화번호는 010-0000-1111 입니다.');

insert into tblQuestion values(seqQuestion.nextval,'안녕',1);
insert into tblQuestion values(seqQuestion.nextval,'안녕',2);
insert into tblQuestion values(seqQuestion.nextval,'하이',1);
insert into tblQuestion values(seqQuestion.nextval,'감사',2);
insert into tblQuestion values(seqQuestion.nextval,'전화로 문의하고 싶어요',3);
--delete from tblQuestion;
select * from tblQuestion;
--delete from tblAnswer;
select * from tblAnswer;

Create or replace view vwQA
as
select a.answer,q.question 
from tblAnswer a inner join tblquestion q on a.seq = q.answer_seq;

select * from vwqa;

select count(answer) as cnt from vwqa where question = '안녕';
