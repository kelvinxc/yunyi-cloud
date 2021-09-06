package com.yunyi.mapper;

import com.yunyi.entity.User;
import com.yunyi.entity.UserToShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName: MyFileMapper
 * @Description: 与用户相关的数据库操作
 * @author:
 * @Version: 1.0
 **/
@Mapper
public interface UserMapper {

    /**
     * @Description 添加User
     * @author
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * @Description 删除User
     * @author
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

    /**
     * @Description 通过ID查询单条数据
     * @author
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(Integer userId);

    /**
     * @author
     * @return 实例对象
     */
    User queryByOpenId(String openId);

    /**
     * @Description 通过邮箱和密码查询单条数据
     * @author
     * @param email 邮箱
     * @param password 密码
     * @return 实例对象
     */
    User queryByEmailAndPwd(@Param("email") String email,@Param("password") String password);

    /**
     * @Description  通过邮箱查询单条数据
     * @Author
     * @Param [email]
     * @return com.yunyi.entity.User
     **/
    User getUserByEmail(String email);

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
     * @Description 修改User
     * @author
     * @return 影响行数
     */
    int update(User user);

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
