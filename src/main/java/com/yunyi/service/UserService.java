package com.yunyi.service;

import com.yunyi.entity.User;
import com.yunyi.entity.UserToShow;

import java.util.List;

/**
 * @InterfaceName: MyFileService
 * @Description: 用户业务层接口
 * @author:
 * @Version: 1.0
 **/
public interface UserService {

    /**
     * @Description 添加User
     * @author
     * @param user 实例对象
     * @return 是否成功
     */
    boolean insert(User user);

    /**
     * @Description 删除User
     * @author
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer userId);

    /**
     * @Description 查询单条数据
     * @author
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    User getUserByOpenId (String openId);

    /**
     * @Description  通过邮箱查询单条数据
     * @Author
     * @Param [email]
     * @return com.yunyi.entity.User
     **/
    User getUserByEmail(String email);

    /**
     * @Description 通过邮箱和密码查询单条数据
     * @author
     * @param email 邮箱
     * @param password 密码
     * @return 实例对象
     */
    User queryByEmailAndPwd(String email, String password);

    /**
     * @Description 查询全部数据
     * @author
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<User> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author
     * @param user 实例对象
     * @return 是否成功
     */
    boolean update(User user);

    /**
     * @Description 获取全部的用户
     * @Author
     * @Param []
     * @return java.util.List<com.yunyi.entity.UserToShow>
     **/
    List<UserToShow> getUsers();

    /**
     * @Description 获得已注册用户的数量
     * @Author
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getUsersCount();
}
