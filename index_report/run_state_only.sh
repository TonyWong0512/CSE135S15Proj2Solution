## search by state no filters order by topk
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/states_no_filter_topk
psql -f states_no_filter_topk.sql -d cse135_small > hot_10MB/states_no_filter_topk
sudo service postgresql stop
sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
sudo service postgresql start
psql -f states_no_filter_topk.sql -d cse135_big > cold_4.5GB/states_no_filter_topk

