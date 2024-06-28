package com.scccy.videobase.config;

import com.scccy.videoModel.mapper.ConfigDouyinMapper;
import com.scccy.videobase.pojo.Global;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitValueConfig implements CommandLineRunner {
    @Resource
    ConfigDouyinMapper configDouyinMapper;

    @Value("${file.save.path}")
    public  String saveFile;
    @Value("${file.save.path}")
    public  String upload_path;
    @Override
    public void run(String... args) throws Exception {
//        获取抖音ck
        Global.Douyin_Cookie = configDouyinMapper.selectById(1).getCookies();
        Global.DOWN_TYPE="http";
        Global.SAVE_FILE=this.saveFile;
        Global.UPLOAD_PATH=this.upload_path;
    }
}
