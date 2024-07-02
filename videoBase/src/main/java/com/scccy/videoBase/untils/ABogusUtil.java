package com.scccy.videoBase.untils;

import java.util.*;

public class ABogusUtil {
    private static final int AID = 6383;
    private static final int PAGE_ID = 0;
    private static final String SALT = "cus";
    private static final List<Integer> OPTIONS = Arrays.asList(0, 1, 14);
    private static final String CHARACTER = "Dkdpgh2ZmsQB80/MfvV36XI1R45-WUAlEixNLwoqYTOPuzKFjJnry79HbGcaStCe";
    private static final String CHARACTER2 = "ckdp1h4ZKsUB80/Mfvw36XIgR25+WQAlEi7NLboqYTOPuzmFjJnryx9HVGDaStCe";
    private static final List<String> CHARACTER_LIST = Arrays.asList(CHARACTER, CHARACTER2);
    private static final List<Integer> SORT_INDEX = Arrays.asList(18, 20, 52, 26, 30, 34, 58, 38, 40, 53, 42, 21, 27, 54, 55, 31, 35, 57, 39, 41, 43, 22, 28, 32, 60, 36, 23, 29, 33, 37, 44, 45, 59, 46, 47, 48, 49, 50, 24, 25, 65, 66, 70, 71);
    private static final List<Integer> SORT_INDEX_2 = Arrays.asList(18, 20, 26, 30, 34, 38, 40, 42, 21, 27, 31, 35, 39, 41, 43, 22, 28, 32, 36, 23, 29, 33, 37, 44, 45, 46, 47, 48, 49, 50, 24, 25, 52, 53, 54, 55, 57, 58, 59, 60, 65, 66, 70, 71);

    private final String userAgent;
    private final String browserFp;
    private final CryptoUtility cryptoUtility;

    public ABogusUtil(String fp, String userAgent) {
        this.userAgent = userAgent != null && !userAgent.isEmpty()
                ? userAgent
                : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0";

        this.browserFp = fp != null && !fp.isEmpty()
                ? fp
                : BrowserFingerprintGenerator.init("Edge");

        this.cryptoUtility = new CryptoUtility(SALT, CHARACTER_LIST);
    }

    public String encodeData(String data, int alphabetIndex) {
        return cryptoUtility.abogusEncode(data, alphabetIndex);
    }

    public Map<Integer, String> generateAbogus(String params, String request) {
        Map<Integer, Object> abDir = new HashMap<>();
        abDir.put(8, 3);
        abDir.put(15, generateConfig());
        abDir.put(18, 44);
        abDir.put(19, Arrays.asList(1, 0, 1, 5));
        abDir.put(66, 0);
        abDir.put(69, 0);
        abDir.put(70, 0);
        abDir.put(71, 0);

        long startEncryption = System.currentTimeMillis();

        List<Integer> array1 = cryptoUtility.paramsToArray(cryptoUtility.paramsToArray(params, true), true);
        List<Integer> array2 = cryptoUtility.paramsToArray(cryptoUtility.paramsToArray(request, true), true);
        List<Integer> array3 = Arrays.asList(212, 61, 87, 195, 104, 163, 124, 28, 92, 126, 187, 53, 218, 38, 254, 253, 252, 73, 83, 197, 194, 142, 113, 37, 9, 67, 166, 36, 56, 72, 56, 64);

        long endEncryption = System.currentTimeMillis();

        insertEncryptionTimes(abDir, startEncryption, endEncryption);
        insertRequestHeaders(abDir);
        insertEncryptedData(abDir, array1, array2, array3);

        abDir.put(64, browserFp.length());
        abDir.put(65, browserFp.length());

        List<Integer> sortedValues = getSortedValues(abDir);
        List<Integer> edgeFpArray = StringProcessor.toCharArray(browserFp);

        int abXor = browserFp.length() & 255;
        abXor = applyXor(abDir, abXor);

        sortedValues.addAll(edgeFpArray);
        sortedValues.add(abXor);

        String abogusBytesStr = StringProcessor.generateRandomBytes(3) + cryptoUtility.transformBytes(sortedValues);
        String abogus = cryptoUtility.abogusEncode(abogusBytesStr, 0);
        params = String.format("%s&a_bogus=%s", params, abogus);
        Map<Integer, String> returnData = new HashMap<>();
        returnData.put(1, params);
        returnData.put(2, abogus);
        returnData.put(3, userAgent);
        return returnData;

    }

    private Map<String, Object> generateConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("aid", AID);
        config.put("pageId", PAGE_ID);
        config.put("boe", false);
        config.put("ddrt", 7);
        config.put("paths", generatePaths());
        config.put("track", generateTrack());
        config.put("dump", true);
        config.put("rpU", "");
        return config;
    }

    private Map<String, Object> generatePaths() {
        Map<String, Object> paths = new HashMap<>();
        paths.put("include", new ArrayList<>(Collections.nCopies(7, new HashMap<>())));
        paths.put("exclude", new ArrayList<>());
        return paths;
    }

    private Map<String, Object> generateTrack() {
        Map<String, Object> track = new HashMap<>();
        track.put("mode", 0);
        track.put("delay", 300);
        track.put("paths", new ArrayList<>());
        return track;
    }

    private void insertEncryptionTimes(Map<Integer, Object> abDir, long startEncryption, long endEncryption) {
        abDir.put(20, (int) ((startEncryption >> 24) & 255));
        abDir.put(21, (int) ((startEncryption >> 16) & 255));
        abDir.put(22, (int) ((startEncryption >> 8) & 255));
        abDir.put(23, (int) (startEncryption & 255));
        abDir.put(24, (int) (startEncryption / 256 / 256 / 256 / 256));
        abDir.put(25, (int) (startEncryption / 256 / 256 / 256 / 256 / 256));

        abDir.put(44, (int) ((endEncryption >> 24) & 255));
        abDir.put(45, (int) ((endEncryption >> 16) & 255));
        abDir.put(46, (int) ((endEncryption >> 8) & 255));
        abDir.put(47, (int) (endEncryption & 255));
        abDir.put(48, abDir.get(8));
        abDir.put(49, (int) (endEncryption / 256 / 256 / 256 / 256));
        abDir.put(50, (int) (endEncryption / 256 / 256 / 256 / 256 / 256));
    }

    private void insertRequestHeaders(Map<Integer, Object> abDir) {
        abDir.put(26, (OPTIONS.get(0) >> 24) & 255);
        abDir.put(27, (OPTIONS.get(0) >> 16) & 255);
        abDir.put(28, (OPTIONS.get(0) >> 8) & 255);
        abDir.put(29, OPTIONS.get(0) & 255);

        abDir.put(30, OPTIONS.get(1) / 256 & 255);
        abDir.put(31, OPTIONS.get(1) % 256 & 255);
        abDir.put(32, (OPTIONS.get(1) >> 24) & 255);
        abDir.put(33, (OPTIONS.get(1) >> 16) & 255);

        abDir.put(34, (OPTIONS.get(2) >> 24) & 255);
        abDir.put(35, (OPTIONS.get(2) >> 16) & 255);
        abDir.put(36, (OPTIONS.get(2) >> 8) & 255);
        abDir.put(37, OPTIONS.get(2) & 255);
    }

    private void insertEncryptedData(Map<Integer, Object> abDir, List<Integer> array1, List<Integer> array2, List<Integer> array3) {
        abDir.put(38, array1.get(21));
        abDir.put(39, array1.get(22));
        abDir.put(40, array2.get(21));
        abDir.put(41, array2.get(22));
        abDir.put(42, array3.get(23));
        abDir.put(43, array3.get(24));

        abDir.put(51, (PAGE_ID >> 24) & 255);
        abDir.put(52, (PAGE_ID >> 16) & 255);
        abDir.put(53, (PAGE_ID >> 8) & 255);
        abDir.put(54, PAGE_ID & 255);
        abDir.put(55, PAGE_ID);
        abDir.put(56, AID);
        abDir.put(57, AID & 255);
        abDir.put(58, (AID >> 8) & 255);
        abDir.put(59, (AID >> 16) & 255);
        abDir.put(60, (AID >> 24) & 255);
    }

    private List<Integer> getSortedValues(Map<Integer, Object> abDir) {
        List<Integer> sortedValues = new ArrayList<>();
        for (int index : SORT_INDEX) {
            sortedValues.add((Integer) abDir.getOrDefault(index, 0));
        }
        return sortedValues;
    }

    private int applyXor(Map<Integer, Object> abDir, int abXor) {
        for (int index = 0; index < SORT_INDEX_2.size() - 1; index++) {
            if (index == 0) {
                abXor = (Integer) abDir.getOrDefault(SORT_INDEX_2.get(index), 0);
            }
            abXor ^= (Integer) abDir.getOrDefault(SORT_INDEX_2.get(index + 1), 0);
        }
        return abXor;
    }

}
