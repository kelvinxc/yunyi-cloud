package com.yunyi.mapper;

import com.yunyi.entity.FileFolder;
import com.yunyi.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: FileFolderMapper
 * @Description: 与文件夹相关的数据库操作
 * @author:
 **/
@Mapper
public interface FileFolderMapper {

    /**
     * @Description 根据文件夹的id删除文件夹
     * @Author
     * @Param [fileFolderId]
     * @return java.lang.Integer
     **/
    Integer deleteFileFolderById(Integer fileFolderId);

    /**
     * @Description 根据父文件夹的id删除文件夹
     * @Author
     * @Param [parentFolderId]
     * @return java.lang.Integer
     **/
    Integer deleteFileFolderByParentFolderId(Integer parentFolderId);

    /**
     * @Description 根据仓库的id删除文件夹
     * @Author
     * @Param [fileStoreId]
     * @return java.lang.Integer
     **/
    Integer deleteFileFolderByFileStoreId(Integer fileStoreId);

    /**
     * @Description 增加文件夹
     * @Author
     * @Param [fileFolder]
     * @return java.lang.Integer
     **/
    Integer addFileFolder(FileFolder fileFolder);

    /**
     * @Description 根据文件夹的id获取文件夹
     * @Author
     * @Param [fileFolderId]
     * @return com.yunyi.entity.FileFolder
     **/
    FileFolder getFileFolderById(Integer fileFolderId);

    /**
     * @Description 根据父文件夹的id获取文件夹
     * @Author
     * @Param  * @param null
     * @return
     **/
    List<FileFolder> getFileFolderByParentFolderId(Integer parentFolderId);

    /**
     * @Description 根据仓库的id获取文件夹
     * @Author
     * @Param [fileStoreId]
     * @return java.util.List<com.yunyi.entity.FileFolder>
     **/
    List<FileFolder> getFileFolderByFileStoreId(Integer fileStoreId);

    /**
     * @Description 获得仓库的文件夹数量
     * @Author
     * @Param [fileStoreId]
     * @return java.lang.Integer
     **/
    Integer getFileFolderCountByFileStoreId(Integer fileStoreId);

    /**
     * @Description 根据仓库Id获得仓库根目录下的所有文件夹
     * @Author
     * @Param [fileStoreId]
     * @return java.util.List<com.molihub.entity.FileFolder>
     **/
    List<FileFolder> getRootFoldersByFileStoreId(Integer fileStoreId);

    /**
     * @Description 根据文件夹的id修改文件夹信息
     * @Author
     * @Param [fileFolder]
     * @return java.lang.Integer
     **/
    Integer updateFileFolderById(FileFolder fileFolder);

    /**
     * @Description 根据文件夹的id获取文件夹下面的文件
     * @Author
     * @Param [fileStoreId]
     * @return java.util.List<com.yunyi.entity.MyFile>
     **/
    List<MyFile> getFileByFileFolder(Integer fileStoreId);

}
