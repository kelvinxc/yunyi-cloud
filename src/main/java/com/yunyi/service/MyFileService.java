package com.yunyi.service;



import com.yunyi.entity.FileStoreStatistics;
import com.yunyi.entity.MyFile;

import java.util.List;

/**
 * @InterfaceName: MyFileService
 * @Description: 文件业务层接口
 * @author:
 * @Version: 1.0
 **/
public interface MyFileService{

    /**
     * @Description 根据文件的id删除文件
     * @Author
     * @Param [myFileId]
     * @Return Integer
     */
    Integer deleteByFileId(Integer myFileId);

    /**
     * @Description 根据父文件夹的id删除文件
     * @Author
     * @Param [id]
     * @Return Integer
     */
    Integer deleteByParentFolderId(Integer id);

    /**
     * @Description 添加文件
     * @Author
     * @Param [myFile]
     * @Return Integer
     */
    Integer addFileByFileStoreId(MyFile myFile);

    /**
     * @Description 根据文件id获得文件
     * @Author
     * @Param [myFileId]
     * @Return com.molihub.entity.MyFile
     */
    MyFile getFileByFileId(Integer myFileId);

    /**
     * @Description 根据文件id修改文件
     * @Author
     * @Param [record]
     * @Return Integer
     */
    Integer updateFile(MyFile record);

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
    List<MyFile> getFilesByType(Integer storeId, Integer type);

    /**
     * @Description 获取仓库的统计信息
     * @Author
     * @Param [id]
     * @return com.molihub.entity.FileStoreStatistics
     **/
    FileStoreStatistics getCountStatistics(Integer id);

}
