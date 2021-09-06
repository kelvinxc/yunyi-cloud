package com.yunyi.service.impl;

import com.yunyi.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: BaseService
 * @Description: TODO
 * @author:
 * @Version: 1.0
 **/
public class BaseService {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected MyFileMapper myFileMapper;
    @Autowired
    protected FileFolderMapper fileFolderMapper;
    @Autowired
    protected FileStoreMapper fileStoreMapper;
    @Autowired
    protected TempFileMapper tempFileMapper;
}
