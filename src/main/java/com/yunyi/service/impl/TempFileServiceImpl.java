package com.yunyi.service.impl;

import com.yunyi.entity.TempFile;
import com.yunyi.service.TempFileService;
import com.yunyi.utils.AliyunOssUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TempFileServiceImpl
 * @Description (TempFile)表服务实现类
 * @author
 * @Version 1.0
 **/
@Service("tempFileService")
public class TempFileServiceImpl extends BaseService implements TempFileService {

    /**
     * @Description 添加TempFile
     * @author
     * @param tempFile 实例对象
     * @return 是否成功
     */
    @Override
    public boolean insert(TempFile tempFile) {
        if(tempFileMapper.insert(tempFile) == 1){
            return true;
        }
        return false;
    }

    /**
     * @Description 删除TempFile
     * @author
     * @param fileId 主键
     * @return 是否成功
     */
    @Async
    @Override
    public void deleteById(Integer fileId) {
        try {
            TimeUnit.HOURS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TempFile tempFile = queryById(fileId);
        try {
            if (AliyunOssUtil.delete(tempFile.getFilePath())) {
                tempFileMapper.deleteById(fileId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 查询单条数据
     * @author
     * @param fileId 主键
     * @return 实例对象
     */
    @Override
    public TempFile queryById(Integer fileId) {
        return tempFileMapper.queryById(fileId);
    }

    /**
     * @Description 查询全部数据
     * @author
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    @Override
    public List<TempFile> queryAll() {
        return tempFileMapper.queryAll();
    }

    /**
     * @Description 实体作为筛选条件查询数据
     * @author
     * @param tempFile 实例对象
     * @return 对象列表
     */
    @Override
    public List<TempFile> queryAll(TempFile tempFile) {
        return tempFileMapper.queryAll(tempFile);
    }

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author
     * @param tempFile 实例对象
     * @return 是否成功
     */
    @Override
    public boolean update(TempFile tempFile) {
        if(tempFileMapper.update(tempFile) == 1){
            return true;
        }
        return false;
    }

}
