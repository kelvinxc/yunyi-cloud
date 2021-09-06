package com.yunyi.service;


import com.yunyi.entity.TempFile;

import java.util.List;

/**
 * @InterfaceName TempFileService
 * @Description (TempFile)表服务接口
 * @author
 * @Version 1.0
 **/
public interface TempFileService {

    /**
     * @Description 添加TempFile
     * @author
     * @param tempFile 实例对象
     * @return 是否成功
     */
    boolean insert(TempFile tempFile);

    /**
     * @Description 删除TempFile
     * @author
     * @param fileId 主键
     */
    void deleteById(Integer fileId);

    /**
     * @Description 查询单条数据
     * @author
     * @param fileId 主键
     * @return 实例对象
     */
    TempFile queryById(Integer fileId);

    /**
     * @Description 查询全部数据
     * @author
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<TempFile> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author
     * @param tempFile 实例对象
     * @return 对象列表
     */
    List<TempFile> queryAll(TempFile tempFile);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author
     * @param tempFile 实例对象
     * @return 是否成功
     */
    boolean update(TempFile tempFile);

}
