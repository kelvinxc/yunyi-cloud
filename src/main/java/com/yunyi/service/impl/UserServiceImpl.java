package com.yunyi.service.impl;

import com.yunyi.entity.User;
import com.yunyi.entity.UserToShow;
import com.yunyi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @ClassName UserServiceImpl
* @Description (User)表服务实现类
* @author
* @Version 1.0
**/
@Service
public class UserServiceImpl extends BaseService implements UserService {

   /**
    * @Description 添加User
    * @author
    * @param user 实例对象
    * @return 是否成功
    */
   @Override
   public boolean insert(User user) {
       if(userMapper.insert(user) == 1){
           return true;
       }
       return false;
   }

   /**
    * @Description 删除User
    * @author
    * @param userId 主键
    * @return 是否成功
    */
   @Override
   public boolean deleteById(Integer userId) {
       if(userMapper.deleteById(userId) == 1){
           return true;
       }
       return false;
   }

   /**
    * @Description 查询单条数据
    * @author
    * @param userId 主键
    * @return 实例对象
    */
   @Override
   public User queryById(Integer userId) {
       return userMapper.queryById(userId);
   }

   /**
    * 通过OpenId获取用户信息
    */
    @Override
    public User getUserByOpenId(String openId) {
        return userMapper.queryByOpenId(openId);
    }

    /**
     * @Description 通过邮箱查询单条数据
     * @author
     * @param email 邮箱
     * @return 实例对象
     */
    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    /**
     * @Description 通过邮箱和密码查询单条数据
     * @author
     * @param email 邮箱
     * @param password 密码
     * @return 实例对象
     */
    @Override
    public User queryByEmailAndPwd(String email, String password) {
        return userMapper.queryByEmailAndPwd(email,password);
    }

    /**
    * @Description 查询全部数据
    * @author
    * 分页使用MyBatis的插件实现
    * @return 对象列表
    */
   @Override
   public List<User> queryAll() {
       return userMapper.queryAll();
   }

   /**
    * @Description 实体作为筛选条件查询数据
    * @author
    * @param user 实例对象
    * @return 对象列表
    */
   @Override
   public List<User> queryAll(User user) {
       return userMapper.queryAll(user);
   }

   /**
    * @Description 修改数据，哪个属性不为空就修改哪个属性
    * @author
    * @param user 实例对象
    * @return 是否成功
    */
   @Override
   public boolean update(User user) {
       if(userMapper.update(user) == 1){
           return true;
       }
       return false;
   }

    /**
     * @Description 获取全部的用户
     * @Author xw
     * @Param []
     * @return java.util.List<com.yunyi.entity.UserToShow>
     **/
    @Override
    public List<UserToShow> getUsers() {
        return userMapper.getUsers();
    }

    /**
     * @Description 获得已注册用户的数量
     * @Author xw
     * @Param []
     * @return java.lang.Integer
     **/
    @Override
    public Integer getUsersCount() {
        return userMapper.getUsersCount();
    }

}
