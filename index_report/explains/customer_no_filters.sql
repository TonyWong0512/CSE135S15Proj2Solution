BEGIN;
CREATE TEMP TABLE p_t (id int, name text)ON COMMIT DELETE ROWS;
CREATE TEMP TABLE u_t (id int, name text)ON COMMIT DELETE ROWS;
insert into u_t (id, name) select id,name from users order by name asc offset 0 limit 20;
insert into p_t (id, name) select id,name from products order by name asc offset 0 limit 10;
select s.uid, sum(s.quantity*s.price) from  u_t u LEFT OUTER JOIN sales s ON s.uid=u.id group by s.uid;
select s.pid, sum(s.quantity*s.price) from p_t p LEFT OUTER JOIN sales s ON s.pid=p.id  group by s.pid;
select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;
COMMIT;
