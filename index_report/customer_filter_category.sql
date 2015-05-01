BEGIN;
CREATE TEMP TABLE p_t (id int, name text) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE u_t (id int, name text) ON COMMIT DELETE ROWS;
insert into u_t (id, name) select id, name from users order by name asc offset 0 limit 20;
insert into p_t (id, name) select id, name from products where cid= 1 order by name asc offset 0 limit 10;
EXPLAIN ANALYZE VERBOSE select s.uid, sum(s.quantity*s.price) from  u_t u LEFT OUTER JOIN
(select s2.uid as uid, s2.pid as pid, s2.price as price, s2.quantity as quantity  from sales s2, products p where s2.pid = p.id and p.cid = 1) s ON s.uid=u.id group by s.uid;
EXPLAIN ANALYZE VERBOSE select s.pid, sum(s.quantity*s.price) from p_t p LEFT OUTER JOIN sales s ON s.pid=p.id  group by s.pid;
EXPLAIN ANALYZE VERBOSE select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;
COMMIT;
