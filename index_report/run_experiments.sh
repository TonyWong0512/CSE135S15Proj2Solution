# search by customers no filters order by name
psql -f customer_no_filters.sql -d cse135_small >> small_10MB/customer_no_filters
psql -f customer_no_filters.sql -d cse135 >> medium_720MB/customer_no_filters

# search by customer filter by category order by name
psql -f customer_filter_category.sql -d cse135_small >> small_10MB/customer_filter_category
psql -f customer_filter_category.sql -d cse135 >> medium_720MB/customer_filter_category

# search by customer no filters order by price
psql -f customer_no_filter_topk.sql -d cse135_small >> small_10MB/customer_no_filter_topk
psql -f customer_no_filter_topk.sql -d cse135 >> medium_720MB/customer_no_filter_topk

# search by state no filters order by topk
psql -f states_no_filter_topk.sql -d cse135_small >> small_10MB/states_no_filter_topk
psql -f states_no_filter_topk.sql -d cse135 >> medium_720MB/states_no_filter_topk
