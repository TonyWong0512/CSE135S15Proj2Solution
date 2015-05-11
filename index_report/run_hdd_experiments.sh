# search by customers no filters order by name
psql -f customer_no_filters.sql -d smalldb > smalldbHDD/customer_no_filters
psql -f customer_no_filters.sql -d smalldb > smalldbHDD/customer_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_no_filters.sql -d largedb > largedbHDD/customer_no_filters

## search by customer filter by category order by name
psql -f customer_filter_category.sql -d smalldb > smalldbHDD/customer_filter_category
psql -f customer_filter_category.sql -d smalldb > smalldbHDD/customer_filter_category
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_filter_category.sql -d largedb > largedbHDD/customer_filter_category

## search by customer no filters order by price
psql -f customer_no_filter_topk.sql -d smalldb > smalldbHDD/customer_no_filter_topk
psql -f customer_no_filter_topk.sql -d smalldb > smalldbHDD/customer_no_filter_topk
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_no_filter_topk.sql -d largedb > largedbHDD/customer_no_filter_topk

## search by state no filters order by topk
psql -f states_no_filter_topk.sql -d smalldb > smalldbHDD/states_no_filter_topk
psql -f states_no_filter_topk.sql -d smalldb > smalldbHDD/states_no_filter_topk
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_no_filter_topk.sql -d largedb > largedbHDD/states_no_filter_topk
