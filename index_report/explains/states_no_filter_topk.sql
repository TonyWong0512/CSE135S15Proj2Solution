CREATE TEMP TABLE p_t (name text, id int, price int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE s_t (name text, id int, price int) ON COMMIT DELETE ROWS;
select count(*) from users;
select count(*) from products;
select count(*) from sales;
EXPLAIN ANALYZE VERBOSE insert into s_t (name, id, price) select st.name, st.id, sum(s.quantity*s.price) as price
from  states st, users u, sales s  where st.id = u.state and s.uid=u.id group by st.id, st.name order by price;
EXPLAIN ANALYZE VERBOSE insert into p_t (name, id, price) select p.name, s.pid, sum(s.quantity*s.price) as price 
from products p, sales s where s.pid=p.id  group by s.pid, p.name order by price;
EXPLAIN ANALYZE VERBOSE select u.state, s.pid, sum(s.quantity*s.price) from s_t st, users u, p_t p, sales s where st.id = u.state and s.uid=u.id and s.pid=p.id group by u.state, s.pid;
select u.state, s.pid, sum(s.quantity*s.price) from s_t st, users u, p_t p, sales s where st.id = u.state and s.uid=u.id and s.pid=p.id group by u.state, s.pid;
