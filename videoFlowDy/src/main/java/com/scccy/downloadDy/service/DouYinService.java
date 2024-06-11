package com.scccy.downloadDy.service;

import com.scccy.common.ResultData;
import com.scccy.downloadDy.domain.TiktokConfig;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface DouYinService {
    void saveSettings(TiktokConfig tiktokConfig);

    void search(String url);

    ResultData getDouYinCodeLogin() throws NoSuchAlgorithmException, IOException;
}
