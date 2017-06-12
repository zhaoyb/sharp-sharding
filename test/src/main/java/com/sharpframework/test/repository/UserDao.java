package com.sharpframework.test.repository;


import com.sharpframework.test.mapping.UserMapper;
import com.sharpframework.test.model.User;
import com.sharpframework.shardingcore.multippledb.DataSourceName;
import com.sharpframework.shardingcore.shardingannotation.Sharding;
import com.sharpframework.shardingcore.shardingannotation.ShardingKey;
import com.sharpframework.shardingcore.shardingmodel.ShardingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Robin on 2017-05-08.
 */
@Component
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    @Sharding(dataSource = DataSourceName.UserDb, shardingModel = ShardingModel.MOD, shardingCount = 3)
    public boolean addUser(@ShardingKey int userId, User user) {
        int rowCount = userMapper.addUser(user);
        if (rowCount > 0)
            return true;
        else
            return false;
    }

    @Sharding(dataSource = DataSourceName.UserDb, shardingModel = ShardingModel.MOD, shardingCount = 3)
    public User getUserById(@ShardingKey Integer id) {
        return userMapper.getUserById(id);
    }

}
