BEGIN;
SET seq_page_cost TO 1;
SET random_page_cost TO 1;
CREATE TEMP TABLE p_t (name text, id int, price int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE s_t (name text, id int, price int) ON COMMIT DELETE ROWS;
EXPLAIN ANALYZE VERBOSE insert into s_t (name, id, price) select st.name, st.id, sum(us.quantity*us.price) as price
from  states st left outer join (select u.state as state, s.quantity as quantity, s.price as price from users u join sales s on s.uid=u.id) as us
on st.id = us.state group by st.id, st.name order by price desc offset 0 limit 20;
EXPLAIN ANALYZE VERBOSE insert into p_t (name, id, price) select p.name, s.pid, sum(s.quantity*s.price) as price 
from products p left outer join sales s on s.pid=p.id  group by s.pid, p.name order by price desc offset 0 limit 10;
EXPLAIN ANALYZE VERBOSE select u.state, s.pid, sum(s.quantity*s.price) from s_t st, users u, p_t p, sales s where st.id = u.state and s.uid=u.id and s.pid=p.id group by u.state, s.pid;
COMMIT;
