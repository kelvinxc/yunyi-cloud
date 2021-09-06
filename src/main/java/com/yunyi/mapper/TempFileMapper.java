package com.yunyi.mapper;

import com.yunyi.entity.TempFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @InterfaceName TempFileMapper
* @Description (TempFile)表数据库访问层
* @author
* @Version 1.0
**/
@Mapper
public interface TempFileMapper {

   /**
    * @Description 添加TempFile
    * @author
    * @param tempFile 实例对象
    * @return 影响行数
    */
   int insert(TempFile tempFile);

   /**
    * @Description 删除TempFile
    * @author
    * @param fileId 主键
    * @return 影响行数
    */
   int deleteById(Integer fileId);

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
    * @Description 修改TempFile
    * @author
    * @return 影响行数
    */
   int update(TempFile tempFile);

}
