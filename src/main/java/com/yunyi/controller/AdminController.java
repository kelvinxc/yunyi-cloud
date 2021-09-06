package com.yunyi.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunyi.entity.*;
import com.yunyi.utils.AliyunOssUtil;
import com.yunyi.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 管理员控制器
 **/
@Controller
public class AdminController extends BaseController {
    private Logger logger = LogUtils.getInstance(AdminController.class);

    /**
     * @Description 前往用户管理页面
     * @Author
     * @Param [map]
     * @return java.lang.String
     **/
    @GetMapping("/manages-users")
    public String manageUsers(Map<String,Object> map,Integer cur){
        if (loginUser.getRole() == 1){
            //用于无访问权限
            logger.error("当前登录用户："+loginUser.getUserName()+"无管理员权限！");
            return "redirect:/error401Page";
        }
        //获取全部的用户
        Integer usersCount = userService.getUsersCount();
        //获取当前查询的页数，如果为空，默认为0
        cur = (cur == null || cur<0)?0:cur;
        //获得统计信息
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        //分页获得20个用户信息
        Page<Object> page = PageHelper.startPage(cur, 20);
        List<UserToShow> users = userService.getUsers();
        map.put("statistics", statistics);
        map.put("users", users);
        map.put("page", page);
        map.put("usersCount", usersCount);
        logger.info("用户管理域的内容："+map);
        return "admin/manage-users";
    }

    /**
     * @Description 修改用户的权限和最大容量
     * @Author
     * @Param [uId, pre, size]
     * @return java.lang.String
     **/
    @GetMapping("/updateStoreInfo")
    @ResponseBody
    public String updateStoreInfo(Integer uId,Integer pre,Integer size){
        Integer integer = fileStoreService.updatePermission(uId, pre, size*1024);
        if (integer == 1) {
            //更新成功，返回200状态码
            logger.info("修改用户"+userService.queryById(uId).getUserName()+"：的权限和仓库大小成功！");
            return "200";
        }else {
            //更新失败，返回500状态码
            logger.error("修改用户"+userService.queryById(uId).getUserName()+"：的权限和仓库大小失败！");
            return "500";
        }
    }

    /**
     * @Description 删除用户
     * @Author
     * @Param [uId, cur]
     * @return java.lang.String
     **/
    @GetMapping("/deleteUser")
    public String deleteUser(Integer uId,Integer cur){
        cur = (cur == null || cur < 0)?1:cur;
        User user = userService.queryById(uId);
        FileStore fileStore = fileStoreService.getFileStoreByUserId(uId);
        List<FileFolder> folders = fileFolderService.getRootFoldersByFileStoreId(fileStore.getFileStoreId());
        //迭代删除文件夹
        for (FileFolder f:folders) {
            try {
                deleteFolderF(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<MyFile> files = myFileService.getRootFilesByFileStoreId(fileStore.getFileStoreId());
        //删除该用户仓库根目录下的所有文件
        for (MyFile f:files) {
            String remotePath = f.getMyFilePath();
            //从oss上删除文件
            boolean b = false;
            try {
                b = AliyunOssUtil.delete(remotePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (b){
                //删除文件表对应的数据
                myFileService.deleteByFileId(f.getMyFileId());
            }
            logger.info("删除文件成功!"+f);
        }
        userService.deleteById(uId);
        fileStoreService.deleteById(fileStore.getFileStoreId());
        return "redirect:/manages-users?cur="+cur;
    }

    /**
     * @Description 迭代删除文件夹里面的所有文件和子文件夹
     * @Author
     * @Param [folder]
     * @return void
     **/
    public void deleteFolderF(FileFolder folder) throws IOException {
        //获得当前文件夹下的所有子文件夹
        List<FileFolder> folders = fileFolderService.getFileFolderByParentFolderId(folder.getFileFolderId());
        //删除当前文件夹的所有的文件
        List<MyFile> files = myFileService.getFilesByParentFolderId(folder.getFileFolderId());
        if (files.size()!=0){
            for (int i = 0; i < files.size(); i++) {
                Integer fileId = files.get(i).getMyFileId();
                boolean b = AliyunOssUtil.delete(files.get(i).getMyFilePath());
                if (b){
                    myFileService.deleteByFileId(fileId);
                    fileStoreService.subSize(folder.getFileStoreId(),Integer.valueOf(files.get(i).getSize()));
                }
            }
        }
        if (folders.size()!=0){
            for (int i = 0; i < folders.size(); i++) {
                deleteFolderF(folders.get(i));
            }
        }
        fileFolderService.deleteFileFolderById(folder.getFileFolderId());
    }
}
