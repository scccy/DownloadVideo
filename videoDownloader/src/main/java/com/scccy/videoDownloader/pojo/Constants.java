package com.scccy.videoDownloader.pojo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Constants {
    public static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir")).getParent().getParent().getParent();
    public static final int VERSION_MAJOR = 5;
    public static final int VERSION_MINOR = 3;
    public static final boolean VERSION_BETA = false;

    public static final String REPOSITORY = "https://github.com/JoeanAmier/TikTokDownloader";
    public static final String LICENCE = "GNU General Public License v3.0";
    public static final String DOCUMENTATION_URL = "https://github.com/JoeanAmier/TikTokDownloader/wiki/Documentation";
    public static final String RELEASES = "https://github.com/JoeanAmier/TikTokDownloader/releases/latest";

    public static final int RETRY = 5;

    public static final String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    public static final int[][] X_BOGUS_CODE = {
            {147, 136},
            {80, 235},
            {170, 197}
    };

    public static final String BLANK_PREVIEW = "static/images/blank.png";

    // List of constants
    public static final List<String> ALL_CONSTANTS = List.of(
            "PROJECT_ROOT",
            "VERSION_MAJOR",
            "VERSION_MINOR",
            "VERSION_BETA",
            "REPOSITORY",
            "LICENCE",
            "DOCUMENTATION_URL",
            "RELEASES",
            "RETRY",
            "USERAGENT",
            "X_BOGUS_CODE",
            "DISCLAIMER_TEXT",
            "BLANK_PREVIEW"
    );
}
