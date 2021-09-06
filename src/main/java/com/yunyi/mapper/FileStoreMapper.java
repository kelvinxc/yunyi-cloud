package com.yunyi.mapper;

import com.yunyi.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName: FileStoreMapper
 * @Description: 与文件仓库相关的数据库操作
 * @author:
 * @Version: 1.0
 **/
@Mapper
public interface FileStoreMapper {

    /**
     * @Description 添加文件仓库（用户注册时调用）
     * @Author
     * @Param [fileStore]
     * @return java.lang.Integer 返回影响数据库的行数，新增文件仓库id封装在实体类的id属性
     **/
    public Integer addFileStore(FileStore fileStore);

    /**
     * @Description 根据用户id获得文件仓库
     * @Author
     * @Param [fileStoreId]
     * @return com.molihub.entity.FileStore
     **/
    public FileStore getFileStoreByUserId(Integer userId);

    /**
     * @Description 根据文件仓库id获得文件仓库
     * @Author
     * @Param [fileStoreId]
     * @return com.molihub.entity.FileStore
     **/
    public FileStore getFileStoreById(Integer fileStoreId);

    /**
     * @Description 修改仓库当前已使用的容量
     * @Author
     * @Param [id,size]
     * @return java.lang.Integer
     **/
    public Integer addSize(@Param("id") Integer id,@Param("size") Integer size);

    /**
     * @Description 修改仓库当前已使用的容量
     * @Author
     * @Param [id,size]
     * @return java.lang.Integer
     **/
    public Integer subSize(@Param("id")Integer id,@Param("size") Integer size);

    /**
     * @Description 更新仓库权限
     * @Author
     * @Param [id, permission]
     * @return java.lang.Integer
     **/
    public Integer updatePermission(@Param("id")Integer id,@Param("permission") Integer permission,@Param("size") Integer size);

    /**
     * @Description 通过主键删除仓库
     * @Author
     * @Param [id]
     * @return java.lang.Integer
     **/
    public Integer deleteById(Integer id);
}
