package com.scccy.videoflowDy.service;

import com.scccy.videoModel.domain.ConfigDouyin;
import com.scccy.videobase.common.ResultData;
import com.scccy.videoModel.domain.CollectData;
import com.scccy.videoModel.domain.ConfigTiktok;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface DouYinService {
    void saveSettings(ConfigDouyin configDouyin);


    ResultData getDouYinCodeLogin() throws NoSuchAlgorithmException, IOException;

    ResultData FavoritesData(CollectData collectData);
}
