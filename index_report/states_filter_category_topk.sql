BEGIN;
SET random_page_cost to 2.0;
CREATE TEMP TABLE p_t (name text, id int, price int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE s_t (name text, id int, price int) ON COMMIT DELETE ROWS;
EXPLAIN ANALYZE VERBOSE insert into s_t (name, id, price) select st.name, st.id, sum(s.quantity*s.price) as price from states st LEFT OUTER JOIN ( select u.state as state, s2.pid as pid, s2.price as price, s2.quantity as quantity  from users u, sales s2, products p where u.id = s2.uid and s2.pid = p.id and p.cid = 1) s on s.state = st.id group by st.id, st.name order by price offset 0 limit 20;
EXPLAIN ANALYZE VERBOSE insert into p_t (name, id, price) select p.name, s.pid, sum(s.quantity*s.price) as price from products p, sales s where s.pid=p.id and p.cid = 1 group by s.pid, p.name order by price desc offset 0 limit 10;
EXPLAIN ANALYZE VERBOSE select u.state, s.pid, sum(s.quantity*s.price) from s_t st, users u, p_t p, sales s where st.id = u.state and s.uid=u.id and s.pid=p.id group by u.state, s.pid;
COMMIT;
