BEGIN;
SET random_page_cost to 2.0;
CREATE TEMP TABLE p_t (name text, id int) ON COMMIT DELETE ROWS;
CREATE TEMP TABLE s_t (name text, id int) ON COMMIT DELETE ROWS;
insert into s_t (name, id) select st.name, st.id from  states st order by name desc offset 0 limit 20;
insert into p_t (id, name) select id,name from products order by name asc offset 0 limit 10;
EXPLAIN ANALYZE VERBOSE select s_t.id, coalesce(sum(s.quantity*s.price),0) from s_t LEFT OUTER JOIN (select u.state as state, s.quantity as quantity, s.price as price FROM users u JOIN sales s ON s.uid=u.id) s ON s.state=s_t.id group by s_t.id;
EXPLAIN ANALYZE VERBOSE select p.id, sum(s.quantity*s.price) from p_t p LEFT OUTER JOIN sales s ON s.pid=p.id  group by p.id;
EXPLAIN ANALYZE VERBOSE select u.state, s.pid, sum(s.quantity*s.price) from s_t st, users u, p_t p, sales s where st.id = u.state and s.uid=u.id and s.pid=p.id group by u.state, s.pid;
COMMIT;
