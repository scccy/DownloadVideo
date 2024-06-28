package com.scccy.videoDownloader.pojo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parameter {
    public static final String[] NAME_KEYS = {
            "id", "desc", "create_time", "nickname", "uid", "mark", "type"
    };
    public static final Set<String> MODE_COMPLETE_VALUES = Set.of(
            "1", "2", "3", "4", "4 1", "4 2", "4 2 1", "4 2 2", "4 2 3", "4 3", "4 3 1", "4 3 2", "4 4", "4 5", "4 5 1", "4 5 2", "4 6", "4 6 1", "4 6 2", "4 6 3", "4 7", "4 7 1", "4 7 2", "4 7 3", "4 8", "4 9", "4 10", "5", "6", "7", "8"
    );
    public static final Set<String> MODE_REDUCED_VALUES = Set.of(
            "1", "2", "3", "4", "4 1", "4 2", "4 2 1", "4 2 2", "4 2 3", "4 3", "4 3 1", "4 3 2", "4 4", "4 5", "4 5 1", "4 5 2", "4 5 3", "4 6", "4 7", "5", "6", "7", "8"
    );
    private Cleaner cleaner = new Cleaner();

    private CookiesSettings cookiesSettings;
    private Cookie cookieObject;
    private Path mainPath;
    private Object xb;
    private ColorfulConsole console;
    private String cookieCache;
    private Map<String, String> cookie;
    private Path root;
//    todo:直接赋值NUll
    private Object accountsUrls=null;
    private Object mixUrls;
    private String folderName;
    private String[] nameFormat;
    private String dateFormat;
    private String split;
    private boolean music;
    private boolean folderMode;
    private String storageFormat;
    private boolean dynamicCover;
    private boolean originalCover;
    private Map<String, String> proxies;
    private boolean download;
    private int maxSize;
    private int chunk;
    private int maxRetry;
    private int maxPages;
    private String[] defaultMode;
    private Object ownerUrl;
    private FFMPEG ffmpeg;
    private DownloadRecorder blacklist;
    private boolean reduced;
    private double timeout = 10;
    private Map<String, Object> checkRules;

    public Parameter(
            CookiesSettings cookiesSettings,
            Cookie cookieObject,
            Path mainPath,
            Logger logger,
            Object xb,
            ColorfulConsole console,
            Object cookie,
            String root,
            Map<String, String> accountsUrls,
            Map<String, String> mixUrls,
            String folderName,
            String nameFormat,
            String dateFormat,
            String split,
            boolean music,
            boolean folderMode,
            String storageFormat,
            boolean dynamicCover,
            boolean originalCover,
            String proxies,
            boolean download,
            int maxSize,
            int chunk,
            int maxRetry,
            int maxPages,
            String defaultMode,
            Map<String, String> ownerUrl,
            String ffmpeg,
            DownloadRecorder blacklist,
            boolean reduced,
            double timeout,
            Map<String, Object> kwargs
    ) {
        this.cookiesSettings = cookiesSettings;
        this.cookieObject = cookieObject;
        this.mainPath = mainPath;
        this.logger = logger;
        this.xb = xb;
        this.console = console;
        this.cookieCache = null;
        this.cookie = checkCookie(cookie);
        this.root = checkRoot(root);
        this.folderName = checkFolderName(folderName);
        this.nameFormat = checkNameFormat(nameFormat);
        this.dateFormat = checkDateFormat(dateFormat);
        this.split = checkSplit(split);
        this.music = checkBool(music);
        this.folderMode = checkBool(folderMode);
        this.storageFormat = checkStorageFormat(storageFormat);
        this.dynamicCover = checkBool(dynamicCover);
        this.originalCover = checkBool(originalCover);
        this.proxies = checkProxies(proxies);
        this.download = checkBool(download);
        this.maxSize = checkMaxSize(maxSize);
        this.chunk = checkChunk(chunk);
        this.maxRetry = checkMaxRetry(maxRetry);
        this.maxPages = checkMaxPages(maxPages);
        this.blacklist = blacklist;
        this.timeout = checkTimeout(timeout);
        this.accountsUrls = Extractor.generateDataObject(accountsUrls);
        this.mixUrls = Extractor.generateDataObject(mixUrls);
        this.ownerUrl = Extractor.generateDataObject(ownerUrl);
        this.reduced = reduced;
        this.defaultMode = checkDefaultMode(defaultMode);
        this.preview = BLANK_PREVIEW;
        this.ffmpeg = generateFfmpegObject(ffmpeg);
        this.checkRules = new HashMap<>();
        checkRules.put("accounts_urls", null);
        checkRules.put("mix_urls", null);
        checkRules.put("owner_url", null);
        checkRules.put("root", this::checkRoot);
        checkRules.put("folder_name", this::checkFolderName);
        checkRules.put("name_format", this::checkNameFormat);
        checkRules.put("date_format", this::checkDateFormat);
        checkRules.put("split", this::checkSplit);
        checkRules.put("folder_mode", this::checkBool);
        checkRules.put("music", this::checkBool);
        checkRules.put("storage_format", this::checkStorageFormat);
        checkRules.put("dynamic_cover", this::checkBool);
        checkRules.put("original_cover", this::checkBool);
        checkRules.put("proxies", this::checkProxies);
        checkRules.put("download", this::checkBool);
        checkRules.put("max_size", this::checkMaxSize);
        checkRules.put("chunk", this::checkChunk);
        checkRules.put("max_retry", this::checkMaxRetry);
        checkRules.put("max_pages", this::checkMaxPages);
        checkRules.put("default_mode", this::checkDefaultMode);
        checkRules.put("ffmpeg", this::generateFfmpegObject);
    }

    private boolean checkBool(boolean value) {
        return value;
    }

    private Map<String, String> checkCookie(Object cookie) {
        if (cookie instanceof Map) {
            return (Map<String, String>) cookie;
        } else if (cookie instanceof String) {
            this.cookieCache = (String) cookie;
        } else {
            logger.warning("Cookie 参数格式错误");
        }
        return new HashMap<>();
    }

    private void addCookie(Map<String, String> cookie) {
        Map<String, String> parameters = new HashMap<>();
        parameters.putAll(MsToken.getRealMsToken());
        parameters.putAll(TtWid.getTtWid());
        if (cookie != null) {
            cookie.putAll(parameters);
        }
    }

    private Path checkRoot(String root) {
        if (root == null || root.isEmpty()) {
            return mainPath;
        }
        Path r = Paths.get(root);
        if (r.toFile().isDirectory()) {
            logger.info("root 参数已设置为 " + root, false);
            return r;
        }
        if (checkRootAgain(r)) {
            logger.info("root 参数已设置为 " + r, false);
            return r;
        }
        logger.warning("root 参数 " + root + " 不是有效的文件夹路径，程序将使用项目根路径作为储存路径");
        return mainPath;
    }

    private boolean checkRootAgain(Path root) {
        if (root.toAbsolutePath().getParent().toFile().isDirectory()) {
            try {
                Files.createDirectory(root);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String checkFolderName(String folderName) {
        if (folderName != null && !folderName.isEmpty()) {
            folderName = cleaner.filterName(folderName, false);
            if (folderName != null) {
                logger.info("folder_name 参数已设置为 " + folderName, false);
                return folderName;
            }
        }
        logger.warning("folder_name 参数 " + folderName + " 不是有效的文件夹名称，程序将使用默认值：Download");
        return "Download";
    }

    private String[] checkNameFormat(String nameFormat) {
        if (nameFormat != null && !nameFormat.isEmpty()) {
            String[] nameKeys = nameFormat.trim().split(" ");
            if (Arrays.stream(nameKeys).allMatch(key -> Arrays.asList(NAME_KEYS).contains(key))) {
                logger.info("name_format 参数已设置为 " + nameFormat, false);
                return nameKeys;
            }
        }
        logger.warning("name_format 参数 " + nameFormat + " 设置错误，程序将使用默认值：创建时间 作品类型 账号昵称 作品描述");
        return new String[]{"create_time", "type", "nickname", "desc"};
    }

    private String checkDateFormat(String dateFormat) {
        if (dateFormat != null && !dateFormat.isEmpty()) {
            try {
                new SimpleDateFormat(dateFormat);
                logger.info("date_format 参数已设置为 " + dateFormat, false);
                return dateFormat;
            } catch (IllegalArgumentException e) {
                logger.warning("date_format 参数 " + dateFormat + " 设置错误，程序将使用默认值：年-月-日 时:分:秒");
            }
        }
        return "%Y-%m-%d %H:%M:%S";
    }

    private String checkSplit(String split) {
        if (split != null && !split.isEmpty()) {
            for (char c : split.toCharArray()) {
                if (cleaner.rule.containsKey(String.valueOf(c))) {
                    logger.warning("split 参数 " + split + " 包含非法字符，程序将使用默认值：-");
                    return "-";
                }
            }
            logger.info("split 参数已设置为 " + split, false);
            return split;
        }
        return "-";
    }

    private Map<String, String> checkProxies(String proxies) {
        if (proxies != null && !proxies.isEmpty()) {
            Map<String, String> proxiesDict = new HashMap<>();
            proxiesDict.put("http", proxies);
            proxiesDict.put("https", proxies);
            proxiesDict.put("ftp", proxies);
            try {
                URL url = new URL("https://www.baidu.com/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", USERAGENT);
                connection.setRequestProperty("Cookie", Register.generateCookie(proxiesDict));
                connection.setConnectTimeout(10000);
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    logger.info("代理 " + proxies + " 测试成功");
                    return proxiesDict;
                }
            } catch (IOException e) {
                logger.warning("代理 " + proxies + " 测试失败");
            }
        }
        return Map.of("http", null, "https", null, "ftp", null);
    }

    private int checkMaxSize(int maxSize) {
        maxSize = Math.max(maxSize, 0);
        logger.info("max_size 参数已设置为 " + maxSize, false);
        return maxSize;
    }

    private int checkChunk(int chunk) {
        if (chunk > 1024) {
            logger.info("chunk 参数已设置为 " + chunk, false);
            return chunk;
        }
        logger.warning("chunk 参数 " + chunk + " 设置错误，程序将使用默认值：" + (1024 * 1024), false);
        return 1024 * 1024;
    }

    private int checkMaxRetry(int maxRetry) {
        if (maxRetry >= 0) {
            logger.info("max_retry 参数已设置为 " + maxRetry, false);
            return maxRetry;
        }
        logger.warning("max_retry 参数 " + maxRetry + " 设置错误，程序将使用默认值：5", false);
        return 5;
    }

    private int checkMaxPages(int maxPages) {
        if (maxPages > 0) {
            logger.info("max_pages 参数已设置为 " + maxPages, false);
            return maxPages;
        } else if (maxPages != 0) {
            logger.warning("max_pages 参数 " + maxPages + " 设置错误，程序将使用默认值：99999", false);
        }
        return 99999;
    }

    private double checkTimeout(double timeout) {
        if (timeout > 0) {
            logger.info("timeout 参数已设置为 " + timeout, false);
            return timeout;
        }
        logger.warning("timeout 参数 " + timeout + " 设置错误，程序将使用默认值：10");
        return 10;
    }

    private String checkStorageFormat(String storageFormat) {
        if (RecordManager.DataLogger.containsKey(storageFormat)) {
            logger.info("storage_format 参数已设置为 " + storageFormat, false);
            return storageFormat;
        }
        if (storageFormat == null || storageFormat.isEmpty()) {
            logger.info("storage_format 参数未设置，程序不会储存任何数据至文件", false);
        } else {
            logger.warning("storage_format 参数 " + storageFormat + " 设置错误，程序默认不会储存任何数据至文件");
        }
        return "";
    }

    private String[] checkDefaultMode(String defaultMode) {
        Set<String> modeValues = reduced ? MODE_REDUCED_VALUES : MODE_COMPLETE_VALUES;
        if (defaultMode != null && modeValues.contains(defaultMode)) {
            return defaultMode.split(" ");
        }
        if (defaultMode != null && !defaultMode.isEmpty()) {
            logger.warning("default_mode 参数 " + defaultMode + " 设置错误");
        }
        return new String[0];
    }

    public void updateCookie() {
        if (cookie != null) {
            addCookie(cookie);
            headers.put("Cookie", Register.generateCookie(cookie));
        } else if (cookieCache != null) {
            headers.put("Cookie", addCookie(cookieCache));
        }
    }

    private FFMPEG generateFfmpegObject(String ffmpegPath) {
        return new FFMPEG(ffmpegPath);
    }

    public Map<String, Object> getSettingsData() {
        Map<String, Object> settingsData = new HashMap<>();
        settingsData.put("accounts_urls", accountsUrls.toMap());
        settingsData.put("mix_urls", mixUrls.toMap());
        settingsData.put("owner_url", ownerUrl.toMap());
        settingsData.put("root", root.toAbsolutePath().toString());
        settingsData.put("folder_name", folderName);
        settingsData.put("name_format", String.join(" ", nameFormat));
        settingsData.put("date_format", dateFormat);
        settingsData.put("split", split);
        settingsData.put("folder_mode", folderMode);
        settingsData.put("music", music);
        settingsData.put("storage_format", storageFormat);
        settingsData.put("cookie", cookieCache != null ? cookieCache : cookie);
        settingsData.put("dynamic_cover", dynamicCover);
        settingsData.put("original_cover", originalCover);
        settingsData.put("proxies", proxies.get("https") != null ? proxies.get("https") : "");
        settingsData.put("download", download);
        settingsData.put("max_size", maxSize);
        settingsData.put("chunk", chunk);
        settingsData.put("max_retry", maxRetry);
        settingsData.put("max_pages", maxPages);
        settingsData.put("default_mode", String.join(" ", defaultMode));
        settingsData.put("ffmpeg", ffmpeg.getPath() != null ? ffmpeg.getPath() : "");
        return settingsData;
    }
    public Map<String, Object> updateSettingsData(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (checkRules.containsKey(key)) {
                Object rule = checkRules.get(key);
                if (rule instanceof Function) {
                    setField(this, key, ((Function<Object, Object>) rule).apply(value));
                }
            }
        }
        if (data.containsKey("cookie")) {
            setField(this, "cookie", cookieObject.extract(data.get("cookie"), true));
            updateCookie();
        }
        cookiesSettings.update(getSettingsData());
        return getSettingsData();
    }

    private void setField(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
