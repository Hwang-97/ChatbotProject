SET serveroutput ON;
SET verify OFF;

drop table tblQuestion;
drop table tblKeyword;
drop table tblAnswer;
drop sequence seqQuestion;
drop sequence seqAnswer;
drop sequence seqKeyword;

drop view vwqa;

create sequence seqQuestion;
create sequence seqAnswer;
create sequence seqKeyword;

create table tblAnswer(
    seq Number PRIMARY KEY,
    answer varchar2(4000) unique not null
);

create table tblKeyword(
    seq Number PRIMARY KEY,
    keyword varchar2(4000),
    answer_seq number REFERENCES tblAnswer(seq) not null
);

create table tblQuestion(
    seq Number PRIMARY KEY,
    question varchar2(4000),
    answer_seq number REFERENCES tblAnswer(seq) not null,
    cnt number default 0
);

Create or replace view vwQA
as
select a.answer,q.question,q.cnt
from tblAnswer a inner join tblquestion q on a.seq = q.answer_seq;

insert into tblAnswer values(seqAnswer.nextval,'안녕하세요');
insert into tblAnswer values(seqAnswer.nextval,'궁금한게 있으면 다시 찾아주세요!');
insert into tblAnswer values(seqAnswer.nextval,'고객센터 전화번호는 010-0000-1111 입니다.');

insert into tblKeyword values(seqKeyword.nextval,'안녕',1);
insert into tblKeyword values(seqKeyword.nextval,'안녕히',2);
insert into tblKeyword values(seqKeyword.nextval,'하이',1);
insert into tblKeyword values(seqKeyword.nextval,'고마워',2);
insert into tblKeyword values(seqKeyword.nextval,'감사',2);
insert into tblKeyword values(seqKeyword.nextval,'전화',3);
insert into tblKeyword values(seqKeyword.nextval,'고객센터',3);
insert into tblKeyword values(seqKeyword.nextval,'연락처',3);

insert into tblQuestion values(seqQuestion.nextval,'안녕',1,0);
insert into tblQuestion values(seqQuestion.nextval,'하이',1,0);
insert into tblQuestion values(seqQuestion.nextval,'감사',2,0);
insert into tblQuestion values(seqQuestion.nextval,'전화로문의하고싶어요',3,0);

select * from tblQuestion;
select * from tblAnswer;
select * from vwqa;
select * from tblKeyword;

select answer_seq,keyword from tblKeyword order by answer_seq;
-- 저장된 질문을 확인하고 가져오는 프로시저
Create or replace PROCEDURE getAnswer(
    Pquestion in varchar2,
    Panswer out varchar2
)
is
begin
    select answer into Panswer from vwqa where question=Pquestion;
Exception 
    When NO_DATA_FOUND then
        Panswer:='No';
end;

-- 키워드가 가장 많이 등장한 answer 가져오는 프로시저 질문을 받고 해당 스트링에 tblKeyword 가 들어있는지 확인 들어있다면 answer seq 반환
create or replace Procedure getAnswer_keyword(
    Pquestion in varchar2,
    Panswer out varchar2
)
is
    vcount number:=0;
    vprecount number:=0;
    vindex number:=1;
    vkeyword varchar2(4000);
    vanswer_seq number;
    keyword1 varchar2(4000);
    keyword2 varchar2(4000);
    keyword3 varchar2(4000);
    cursor vcursor is select keyword , answer_seq from tblkeyword order by answer_seq;
begin
    open vcursor;
        loop
            fetch vcursor into vkeyword,vanswer_seq;
            exit when vcursor%notfound;
            if vanswer_seq=vindex then -- 각 seq에 맞게 확인함.
                if REGEXP_LIKE(Pquestion, vkeyword)then
                    vcount:=vcount+1;
                end if;
                if vprecount < vcount then
                    select answer into Panswer from tblAnswer where vanswer_seq = seq;
                end if;
            else
                vindex:=vindex+1;
                vprecount:=vcount;
                vcount:=0;
                if REGEXP_LIKE(Pquestion, vkeyword)then-- else 에서 인덱스를 올리고 실행하면 한번의 행이 건너 뛰어지기에 else에서 한번만 검사
                    vcount:=vcount+1;
                end if;
                if vprecount < vcount then
                    select answer into Panswer from tblAnswer where vanswer_seq = seq;
                end if;
            end if;
        end loop;
    close vcursor;
Exception
    when No_Data_found
        then Panswer:='No';
end;
-- 원하는 답이 안떳을때 사용자가 answer 출력
select * from tblAnswer;
-- 원하는 답이 안떳을때 사용자가 answer을 선택하면 사용자가 남겼던 질문과 선택된 answer 번호를 insert 하는 쿼리
insert into tblQuestion values(seqQuestion.nextval,'이건 왜 없죠?? 저는 인사한 거예요!',1,0);

declare
    vm varchar2(4000);
begin
    getAnswer('이건 왜 없죠?? 저는 인사한 거예요!',vm);
--    getAnswer_keyword('안녕하세요 고객센터 연락처좀(전화) 알려주시겠어요?',vm);
    dbms_output.put_line(vm);
end;



