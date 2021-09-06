package com.yunyi.controller;

import com.yunyi.entity.TempFile;
import com.yunyi.utils.AliyunOssUtil;
import com.yunyi.utils.LogUtils;
import com.yunyi.utils.QRCodeUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: TempFileController
 * @Description: 临时文件控制器
 * @author:
 * @Version: 1.0
 */
@Controller
public class TempFileController extends BaseController {

    private Logger logger = LogUtils.getInstance(TempFileController.class);

    /**
     * @Description 上传临时文件
     * @Author
     * @Param [files]
     * @return void
     **/
    @PostMapping("/uploadTempFile")
    public String uploadTempFile(@RequestParam("file") MultipartFile file, String url) {
        session.setAttribute("imgPath","https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2654852821,3851565636&fm=26&gp=0.jpg");
        String name = file.getOriginalFilename().replaceAll(" ","");
        if (!checkTarget(name)){
            logger.error("临时文件上传失败!文件名不符合规范...");
            session.setAttribute("msg", "上传失败!文件名不符合规范");
            session.setAttribute("url","error");
            return "redirect:/temp-file";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        String path = "/temp/"+dateStr +"/"+UUID.randomUUID();

        String filePath = null;
        try {
            // 上传到阿里云
            filePath = AliyunOssUtil.upLoad(file, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(filePath)){
            //上传成功
            logger.info("临时文件上传成功!"+filePath);
            String size = String.valueOf(file.getSize());
            TempFile tempFile = TempFile.builder().fileName(name).filePath(filePath).size(size).uploadTime(new Date()).build();
            if (tempFileService.insert(tempFile)) {
                try {
                    String id = UUID.randomUUID().toString();
                    String p = request.getSession().getServletContext().getRealPath("/user_img/");
                    url = url+"/file/share?t="+ UUID.randomUUID().toString().substring(0,10) +"&f="+tempFile.getFileId()+"&p="+size+"&flag=2";
                    File targetFile = new File(p, "");
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    File f = new File(p, id + ".jpg");
                    if (!f.exists()){
                        //文件不存在,开始生成二维码并保存文件
                        OutputStream os = new FileOutputStream(f);
                        QRCodeUtil.encode(url, "/static/img/logo.png", os, true);
                        os.close();
                    }
                    session.setAttribute("imgPath","user_img/"+id+".jpg");
                    session.setAttribute("url",url);
                    session.setAttribute("msg","上传成功，扫码/访问链接 即可下载！");
                    return "redirect:/temp-file";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                logger.info("临时文件数据库写入失败!"+name);
                session.setAttribute("url","error");
                session.setAttribute("msg", "服务器出错了，临时文件上传失败!");
            }
        }
        return "redirect:/temp-file";
    }

    /**
     * @Description 正则验证文件名是否合法 [汉字,字符,数字,下划线,英文句号,横线]
     * @Author
     * @Param [target]
     * @return boolean
     **/
    public boolean checkTarget(String target) {
        final String format = "[^\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w-_.]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(target);
        return !matcher.find();
    }
}
