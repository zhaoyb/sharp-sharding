package com.sharpframework.shardingcore.multippledb;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by zhaoyabin on 16/10/11.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

    public static void clearDataSourceKey() {
        dataSourceKey.remove();
    }

}
