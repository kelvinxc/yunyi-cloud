package com.yunyi.mapper;

import com.yunyi.entity.FileStoreStatistics;
import com.yunyi.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName: MyFileMapper
 * @Description: 与文件相关的数据库操作
 * @author:
 * @Version: 1.0
 **/
@Mapper
public interface MyFileMapper {

    /**
     * @Description 添加文件
     * @Author
     * @Param [myFile]
     * @return java.lang.Integer
     **/
    Integer addFileByFileStoreId(MyFile myFile);

    /**
     * @Description 根据文件id修改文件
     * @Author
     * @Param [myFile]
     * @return java.lang.Integer
     **/
    Integer updateFileByFileId(MyFile myFile);

    /**
     * @Description 根据文件的id删除文件
     * @Author
     * @Param [myFileId]
     * @return java.lang.Integer
     **/
    Integer deleteByFileId(Integer myFileId);

    /**
     * @Description 根据父文件夹的id删除文件
     * @Author
     * @Param [id]
     * @return java.lang.Integer
     **/
    Integer deleteByParentFolderId(Integer id);

    /**
     * @Description 根据文件的id获取文件
     * @Author
     * @Param [myFileId]
     * @return com.yunyi.entity.MyFile
     **/
    MyFile getFileByFileId(Integer myFileId);

    /**
     * @Description 获得仓库根目录下的所有文件
     * @Author
     * @Param [fileStoreId]
     * @return java.util.List<com.molihub.entity.MyFile>
     **/
    List<MyFile> getRootFilesByFileStoreId(Integer fileStoreId);

    /**
     * @Description 根据父文件夹id获得文件
     * @Author
     * @Param [parentFolderId]
     * @return java.util.List<com.molihub.entity.MyFile>
     **/
    List<MyFile> getFilesByParentFolderId(Integer parentFolderId);

    /**
     * @Description 根据类别获取文件
     * @Author
     * @Param [storeId, type]
     * @return java.util.List<com.yunyi.entity.MyFile>
     **/
    List<MyFile> getFilesByType(@Param("storeId") Integer storeId,@Param("type") Integer type);

    /**
     * @Description 获取仓库的统计信息
     * @Author
     * @Param [id]
     * @return com.molihub.entity.FileStoreStatistics
     **/
    FileStoreStatistics getCountStatistics(Integer id);
}
