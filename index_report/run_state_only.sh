## search by state no filters order by name
psql -f states_no_filters.sql -d cse135_small > hot_10MB/states_no_filters
psql -f states_no_filters.sql -d cse135_small > hot_10MB/states_no_filters
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_no_filters.sql -d cse135_big > cold_4.5GB/states_no_filters

## search by state filter by category order by name
psql -f states_filter_category.sql -d cse135_small > hot_10MB/states_filter_category
psql -f states_filter_category.sql -d cse135_small > hot_10MB/states_filter_category
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_filter_category.sql -d cse135_big > cold_4.5GB/states_filter_category

## search by state no filters order by topk
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/states_no_filter_topk
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/states_no_filter_topk
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_no_filter_topk.sql -d cse135_big > cold_4.5GB/states_no_filter_topk

## search by state filter by category order by topk
psql -f states_filter_category_topk.sql -d cse135_small > hot_10MB/states_filter_category_topk
psql -f states_filter_category_topk.sql -d cse135_small > hot_10MB/states_filter_category_topk
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_filter_category_topk.sql -d cse135_big > cold_4.5GB/states_filter_category_topk
