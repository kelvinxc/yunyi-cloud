package com.yunyi.service.impl;

import com.yunyi.entity.FileFolder;
import com.yunyi.entity.MyFile;
import com.yunyi.service.FileFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @ClassName: FileFolderServiceImpl
* @Description: TODO
* @author:
* @Version: 1.0
**/

@Service
public class FileFolderServiceImpl extends BaseService implements FileFolderService {

    /**
     * @Description 根据文件夹的id删除文件夹
     * @Author
     * @Param [fileFolderId] 文件夹的id
     * @Return java.lang.Integer
     */
    @Override
    public Integer deleteFileFolderById(Integer fileFolderId) {
        return fileFolderMapper.deleteFileFolderById(fileFolderId);
    }

    /**
     * @Description 增加文件夹
     * @Author
     * @Param [fileFolder] 文件夹对象
     * @Return java.lang.Integer
     */
    @Override
    public Integer addFileFolder(FileFolder fileFolder) {
        return fileFolderMapper.addFileFolder(fileFolder);
    }

    /**
     * @Description 根据文件夹的id获取文件下的文件
     * @Author
     * @Param [fileFolderId] 文件夹id
     * @Return com.molihub.entity.FileFolder
     */
    @Override
    public List<MyFile> getFileFolderById(Integer fileFolderId) {
        return fileFolderMapper.getFileByFileFolder(fileFolderId);
    }

    /**
     * @Description 根据父文件夹获得所有的文件夹
     * @Author
     * @Param [parentFolderId]
     * @Return java.util.List<com.molihub.entity.FileFolder>
     */
    @Override
    public Integer updateFileFolderById(FileFolder fileFolder) {
        return fileFolderMapper.updateFileFolderById(fileFolder);
    }

    /**
     * @Description 根据文件夹的id获取文件夹
     * @Author
     * @Param [fileFolderId]
     * @Return com.molihub.entity.FileFolder
     */
    @Override
    public List<FileFolder> getFileFolderByParentFolderId(Integer parentFolderId) {
        return fileFolderMapper.getFileFolderByParentFolderId(parentFolderId);
    }

    /**
     * @Description 根据仓库Id获得仓库根目录下的所有文件夹
     * @Author
     * @Param [fileStoreId]
     * @return java.util.List<com.molihub.entity.FileFolder>
     **/
    @Override
    public FileFolder getFileFolderByFileFolderId(Integer fileFolderId) {
        return fileFolderMapper.getFileFolderById(fileFolderId);
    }

    /**
     * @Description 根据文件夹的id修改文件夹信息
     * @Author
     * @Param [fileFolder] 文件夹对象
     * @Return java.lang.Integer
     */
    @Override
    public List<FileFolder> getRootFoldersByFileStoreId(Integer fileStoreId) {
        return fileFolderMapper.getRootFoldersByFileStoreId(fileStoreId);
    }
}
