package com.sharpframework.shardingcore.shardingmodel;

/**
 * Created by Robin on 2017-05-08.
 */
public class ShardingModelImpl {

    public static String shardingByMod(Object shardingValue, int shardingCount) {
        int shrdingValue = Integer.parseInt(String.valueOf(shardingValue));
        return String.valueOf(shrdingValue % shardingCount);
    }

}
