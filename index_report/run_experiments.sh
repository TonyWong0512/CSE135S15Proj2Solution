echo "This will override all previous experiments results."
read -r -p "Are you sure? [y/N] " response
if [[ $response =~ ^([yY][eE][sS]|[yY])$ ]]; then
    cp sql_files/*.sql .

    #### PART 1
    
    # search by customers no filters order by name
    psql -f customer_no_filters.sql -d cse135_small > hot/ssd/customer_no_filters
    psql -f customer_no_filters.sql -d cse135_small > hot/ssd/customer_no_filters
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_no_filters.sql -d cse135_big > cold/ssd/customer_no_filters
    
    ## search by customer filter by category order by name
    psql -f customer_filter_category.sql -d cse135_small > hot/ssd/customer_filter_category
    psql -f customer_filter_category.sql -d cse135_small > hot/ssd/customer_filter_category
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_filter_category.sql -d cse135_big > cold/ssd/customer_filter_category
    
    ## search by customer no filters order by price
    psql -f customer_no_filter_topk.sql -d cse135_small > hot/ssd/customer_no_filter_topk
    psql -f customer_no_filter_topk.sql -d cse135_small > hot/ssd/customer_no_filter_topk
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_no_filter_topk.sql -d cse135_big > cold/ssd/customer_no_filter_topk
    
    ## search by state no filters order by topk
    psql -f states_no_filter_topk.sql -d cse135_small > hot/ssd/states_no_filter_topk
    psql -f states_no_filter_topk.sql -d cse135_small > hot/ssd/states_no_filter_topk
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f states_no_filter_topk.sql -d cse135_big > cold/ssd/states_no_filter_topk

    #### PART 2

    # search by customers no filters order by name
    psql -f customer_no_filters.sql -d cse135_small > hot/hdd/customer_no_filters
    psql -f customer_no_filters.sql -d cse135_small > hot/hdd/customer_no_filters
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_no_filters.sql -d cse135_big > cold/hdd/customer_no_filters
    
    ## search by customer filter by category order by name
    psql -f customer_filter_category.sql -d cse135_small > hot/hdd/customer_filter_category
    psql -f customer_filter_category.sql -d cse135_small > hot/hdd/customer_filter_category
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_filter_category.sql -d cse135_big > cold/hdd/customer_filter_category
    
    ## search by customer no filters order by price
    psql -f customer_no_filter_topk.sql -d cse135_small > hot/hdd/customer_no_filter_topk
    psql -f customer_no_filter_topk.sql -d cse135_small > hot/hdd/customer_no_filter_topk
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f customer_no_filter_topk.sql -d cse135_big > cold/hdd/customer_no_filter_topk
    
    ## search by state no filters order by topk
    psql -f states_no_filter_topk.sql -d cse135_small > hot/hdd/states_no_filter_topk
    psql -f states_no_filter_topk.sql -d cse135_small > hot/hdd/states_no_filter_topk
    sudo service postgresql stop
    sudo sh -c "echo 3 > /proc/sys/vm/drop_caches"
    sudo service postgresql start
    psql -f states_no_filter_topk.sql -d cse135_big > cold/hdd/states_no_filter_topk

fi
