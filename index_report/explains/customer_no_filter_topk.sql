CREATE TEMP TABLE p_t (name text, id int, price int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE u_t (name text, id int, price int) ON COMMIT DELETE ROWS;
select count(*) from users;
select count(*) from products;
select count(*) from sales;
EXPLAIN ANALYZE VERBOSE insert into u_t (name, id, price) select u.name, s.uid, sum(s.quantity*s.price) as price
from  users u, sales s  where s.uid=u.id group by s.uid, u.name order by price;
EXPLAIN ANALYZE VERBOSE insert into p_t (name, id, price) select p.name, s.pid, sum(s.quantity*s.price) as price 
from products p, sales s where s.pid=p.id  group by s.pid, p.name order by price;
EXPLAIN ANALYZE VERBOSE select s.uid, s.pid, sum(s.quantity*s.price) from u_t u,p_t p, sales s where s.uid=u.id and s.pid=p.id group by s.uid, s.pid;
