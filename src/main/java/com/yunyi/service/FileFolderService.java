package com.yunyi.service;


import com.yunyi.entity.FileFolder;
import com.yunyi.entity.MyFile;

import java.util.List;

/**
 * @Description 文件夹业务层接口
 * @Author
 * @Param  * @param null
 * @return
 **/
public interface FileFolderService{


    /**
     * @Description 根据文件夹的id删除文件夹
     * @Author
     * @Param [fileFolderId] 文件夹的id
     * @Return java.lang.Integer
     */
    Integer deleteFileFolderById(Integer fileFolderId);

    /**
     * @Description 增加文件夹
     * @Author
     * @Param [fileFolder] 文件夹对象
     * @Return java.lang.Integer
     */
    Integer addFileFolder(FileFolder fileFolder);

    /**
     * @Description 根据文件夹的id获取文件下的文件
     * @Author
     * @Param [fileFolderId] 文件夹id
     * @Return com.molihub.entity.FileFolder
     */
    List<MyFile> getFileFolderById(Integer fileFolderId);

    /**
     * @Description 根据父文件夹获得所有的文件夹
     * @Author
     * @Param [parentFolderId]
     * @Return java.util.List<com.molihub.entity.FileFolder>
     */
    List<FileFolder> getFileFolderByParentFolderId(Integer parentFolderId);

    /**
     * @Description 根据文件夹的id获取文件夹
     * @Author
     * @Param [fileFolderId]
     * @Return com.molihub.entity.FileFolder
     */
    FileFolder getFileFolderByFileFolderId(Integer fileFolderId);

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
     * @Param [fileFolder] 文件夹对象
     * @Return java.lang.Integer
     */
    Integer updateFileFolderById(FileFolder fileFolder);

}
