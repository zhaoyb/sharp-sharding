package com.sharpframework.shardingcore;


import com.sharpframework.shardingcore.exception.ShardingKeyMissingException;
import com.sharpframework.shardingcore.multippledb.MultipleDataSource;
import com.sharpframework.shardingcore.shardingannotation.Sharding;
import com.sharpframework.shardingcore.shardingannotation.ShardingKey;
import com.sharpframework.shardingcore.shardingmodel.ShardingModelImpl;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by zhaoyabin on 2017-02-07.
 */
public class ShardingDBAspect {


    public void shardingDB(JoinPoint jp) throws ShardingKeyMissingException {

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();

        Sharding sharding = method.getAnnotation(Sharding.class);
        Parameter[] parameters = method.getParameters();

        String offset = "";
        if (parameters.length > 0 && parameters[0].isAnnotationPresent(ShardingKey.class)) {

            switch (sharding.shardingModel()) {
                case MOD:
                    offset = ShardingModelImpl.shardingByMOd(jp.getArgs()[0], sharding.shardingCount());
                    break;
                case Year:
                    break;
            }

        } else {
            throw new ShardingKeyMissingException("not found sharding key");
        }
        String dataSource = sharding.dataSource().concat("-").concat(offset);
        MultipleDataSource.setDataSourceKey(dataSource);
    }


    public void cleanshardingDB() {
        MultipleDataSource.clearDataSourceKey();
    }

}
