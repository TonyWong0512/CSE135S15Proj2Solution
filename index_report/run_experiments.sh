# search by customers no filters order by name
psql -f customer_no_filters.sql -d cse135_small > hot_10MB/customer_no_filters
psql -f customer_no_filters.sql -d cse135_small > hot_10MB/customer_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_no_filters.sql -d cse135_big > cold_4.5GB/customer_no_filters

## search by customer filter by category order by name
psql -f customer_filter_category.sql -d cse135_small > hot_10MB/customer_no_filters
psql -f customer_filter_category.sql -d cse135_small > hot_10MB/customer_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_filter_category.sql -d cse135_big > cold_4.5GB/customer_no_filters

## search by customer no filters order by price
psql -f customer_no_filter_topk.sql -d cse135_small > hot_10MB/customer_no_filters
psql -f customer_no_filter_topk.sql -d cse135_small > hot_10MB/customer_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f customer_no_filter_topk.sql -d cse135_big > cold_4.5GB/customer_no_filters

## search by state no filters order by topk
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/customer_no_filters
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/customer_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_no_filter_topk.sql -d cse135_big > cold_4.5GB/customer_no_filters
