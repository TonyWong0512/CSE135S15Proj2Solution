BEGIN;
SET seq_page_cost TO 1;
SET random_page_cost TO 1;
CREATE TEMP TABLE p_t (id int, name text, price int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE u_t (id int, name text, price int) ON COMMIT DELETE ROWS;
EXPLAIN ANALYZE VERBOSE insert into u_t (id, name, price) select s.uid, u.name, sum(s.quantity*s.price) as price from  users u LEFT OUTER JOIN
(select s2.uid as uid, s2.pid as pid, s2.price as price, s2.quantity as quantity from sales s2, products p where s2.pid = p.id and p.cid = 1) s ON s.uid=u.id group by s.uid, u.name order by price offset 0 limit 20;
EXPLAIN ANALYZE VERBOSE insert into p_t (id, name, price) select s.pid, p.name, sum(s.quantity*s.price) as price from products p LEFT OUTER JOIN sales s ON s.pid=p.id WHERE p.id = s.pid group by s.pid, p.name order by price offset 0 limit 20;
EXPLAIN ANALYZE VERBOSE select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;
COMMIT;

