package com.yunyi.controller;

import com.yunyi.utils.AliyunOssUtil;
import com.yunyi.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author:
 * @Version: 1.0
 **/
@Controller
public class UserController extends BaseController {

    Logger logger = LogUtils.getInstance(UserController.class);

    /**
     * @Description 上传用户头像
     * @Author
     * @Param [file]
     * @return java.lang.String
     **/
    @PostMapping("/image-upload")
    public String imageUpload(@RequestParam("file") MultipartFile file) {
        // 图像文件为空直接返回
        if (file == null) {
            return "redirect:/user-info";
        }
        // 判断用户上传文件的类型
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".png") || filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            try {
                String url = AliyunOssUtil.upLoad(file, "/image/"+loginUser.getUserId());
                if (url != null) {
                    // 更新用户原头像地址
                    String oldPath = loginUser.getImagePath();
                    AliyunOssUtil.delete(oldPath);
                    loginUser.setImagePath(url);
                    userService.update(loginUser);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/user-info";
    }

    /**
     * @Description 修改用户密码
     * @Author
     * @Param [newPwd]
     * @return java.lang.String
     **/
    @PostMapping("/pwd-update")
    public String updatePassword(String newPwd){
        loginUser.setPassword(newPwd);
        userService.update(loginUser);
        return "redirect:/user-info";
    }

    /**
     * @Description 修改用户名
     * @Author
     * @Param [newPwd]
     * @return java.lang.String
     **/
    @PostMapping("/name-update")
    public String updateUsername(String newName){
        loginUser.setUserName(newName);
        userService.update(loginUser);
        return "redirect:/user-info";
    }

}
