package com.scccy.videoBase.untils;

import org.bouncycastle.jcajce.provider.digest.SM3;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CryptoUtility {
    private final String salt;
    private final List<String> base64Alphabet;
    private final int[] bigArray = {
            121, 243,  55, 234, 103,  36,  47, 228,  30, 231, 106,   6, 115,  95,  78, 101, 250, 207, 198,  50,
            139, 227, 220, 105,  97, 143,  34,  28, 194, 215,  18, 100, 159, 160,  43,   8, 169, 217, 180, 120,
            247,  45,  90,  11,  27, 197,  46,   3,  84,  72,   5,  68,  62,  56, 221,  75, 144,  79,  73, 161,
            178,  81,  64, 187, 134, 117, 186, 118,  16, 241, 130,  71,  89, 147, 122, 129,  65,  40,  88, 150,
            110, 219, 199, 255, 181, 254,  48,   4, 195, 248, 208,  32, 116, 167,  69, 201,  17, 124, 125, 104,
            96,  83,  80, 127, 236, 108, 154, 126, 204,  15,  20, 135, 112, 158,  13,   1, 188, 164, 210, 237,
            222,  98, 212,  77, 253,  42, 170, 202,  26,  22,  29, 182, 251,  10, 173, 152,  58, 138,  54, 141,
            185,  33, 157,  31, 252, 132, 233, 235, 102, 196, 191, 223, 240, 148,  39, 123,  92,  82, 128, 109,
            57,  24,  38, 113, 209, 245,   2, 119, 153, 229, 189, 214, 230, 174, 232,  63,  52, 205,  86, 140,
            66, 175, 111, 171, 246, 133, 238, 193,  99,  60,  74,  91, 225,  51,  76,  37, 145, 211, 166, 151,
            213, 206,   0, 200, 244, 176, 218,  44, 184, 172,  49, 216,  93, 168,  53,  21, 183,  41,  67,  85,
            224, 155, 226, 242,  87, 177, 146,  70, 190,  12, 162,  19, 137, 114,  25, 165, 163, 192,  23,  59,
            9,  94, 179, 107,  35,   7, 142, 131, 239, 203, 149, 136,  61, 249,  14, 156
    };

    public CryptoUtility(String salt, List<String> customBase64Alphabet) {
        this.salt = salt;
        this.base64Alphabet = customBase64Alphabet;
    }

    /**
     * Converts the given inputData to its SM3 hash and returns the hash as an int array.
     *
     * @param inputData the input data to hash
     * @return the hash as an int array
     */
    public static int[] sm3ToArray(String inputData) {
        SM3.Digest sm3 = new SM3.Digest();
        byte[] hash = sm3.digest(inputData.getBytes(StandardCharsets.UTF_8));
        return bytesToIntArray(hash);
    }

    /**
     * Adds the salt to the given param string.
     *
     * @param param the string to add salt to
     * @return the salted string
     */
    public String addSalt(String param) {
        return param + this.salt;
    }

    /**
     * Processes the given param, optionally adding salt, and converts it to a byte array.
     *
     * @param param the param to process
     * @param addSalt whether to add salt to the param
     * @return the processed param as a byte array
     */
    public byte[] processParam(Object param, boolean addSalt) {
        byte[] paramBytes;

        if (param instanceof String) {
            paramBytes = ((String) param).getBytes(StandardCharsets.UTF_8);
        } else if (param instanceof List) {
            List<?> list = (List<?>) param;
            paramBytes = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                paramBytes[i] = ((Integer) list.get(i)).byteValue();
            }
        } else {
            throw new IllegalArgumentException("param must be either a String or a List<Integer>");
        }

        if (addSalt) {
            paramBytes = addSaltToBytes(paramBytes, this.salt);
        }

        return paramBytes;
    }

    /**
     * Converts the given param to an int array after processing it.
     *
     * @param param the param to convert
     * @param addSalt whether to add salt to the param
     * @return the processed param as an int array
     */
    public List<Integer> paramsToArray(Object param, boolean addSalt) {
        byte[] processedParam = this.processParam(param, addSalt);
        int[] intArray = sm3ToArray(new String(processedParam, StandardCharsets.UTF_8));
        List<Integer> intList = new ArrayList<>();
        for (int value : intArray) {
            intList.add(value);
        }
        return intList;
    }

    /**
     * Transforms the given int array using an internal state array.
     *
     * @param bytesList the int array to transform
     * @return the transformed string
     */
    public String transformBytes(List<Integer> bytesList) {
        StringBuilder resultStr = new StringBuilder();
        int indexB = this.bigArray[1];
        int initialValue = 0;

        for (int index = 0; index < bytesList.size(); index++) {
            int charValue = bytesList.get(index);
            int sumInitial;

            if (index == 0) {
                initialValue = this.bigArray[indexB];
                sumInitial = indexB + initialValue;
                this.bigArray[1] = initialValue;
                this.bigArray[indexB] = indexB;
            } else {
                sumInitial = initialValue + this.bigArray[(index + 2) % this.bigArray.length];
            }

            sumInitial %= this.bigArray.length;
            int valueF = this.bigArray[sumInitial];
            int encryptedChar = charValue ^ valueF;
            resultStr.append((char) encryptedChar);

            int valueE = this.bigArray[(index + 2) % this.bigArray.length];
            sumInitial = (indexB + valueE) % this.bigArray.length;
            initialValue = this.bigArray[sumInitial];
            this.bigArray[sumInitial] = this.bigArray[(index + 2) % this.bigArray.length];
            this.bigArray[(index + 2) % this.bigArray.length] = initialValue;
            indexB = sumInitial;
        }

        return resultStr.toString();
    }

    /**
     * Encodes the given inputString to base64 using a specified alphabet.
     *
     * @param inputString the string to encode
     * @param selectedAlphabet the index of the alphabet to use
     * @return the base64 encoded string
     */
    public String base64Encode(String inputString, int selectedAlphabet) {
        StringBuilder binaryString = new StringBuilder();

        for (char c : inputString.toCharArray()) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }

        int paddingLength = (6 - binaryString.length() % 6) % 6;
        binaryString.append("0".repeat(paddingLength));

        StringBuilder encodedString = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 6) {
            int index = Integer.parseInt(binaryString.substring(i, i + 6), 2);
            encodedString.append(this.base64Alphabet.get(selectedAlphabet).charAt(index));
        }

        encodedString.append("=".repeat(paddingLength / 2));

        return encodedString.toString();
    }

    /**
     * Encodes the given abogusBytesStr using a specified alphabet.
     *
     * @param abogusBytesStr the string to encode
     * @param selectedAlphabet the index of the alphabet to use
     * @return the encoded string
     */
    public String abogusEncode(String abogusBytesStr, int selectedAlphabet) {
        StringBuilder abogus = new StringBuilder();

        for (int i = 0; i < abogusBytesStr.length(); i += 3) {
            int n;
            if (i + 2 < abogusBytesStr.length()) {
                n = (abogusBytesStr.charAt(i) << 16) | (abogusBytesStr.charAt(i + 1) << 8) | abogusBytesStr.charAt(i + 2);
            } else if (i + 1 < abogusBytesStr.length()) {
                n = (abogusBytesStr.charAt(i) << 16) | (abogusBytesStr.charAt(i + 1) << 8);
            } else {
                n = abogusBytesStr.charAt(i) << 16;
            }

            for (int j = 18; j >= 0; j -= 6) {
                int k = (n >> j) & 0x3F;
                abogus.append(this.base64Alphabet.get(selectedAlphabet).charAt(k));
            }
        }

        while (abogus.length() % 4 != 0) {
            abogus.append("=");
        }

        return abogus.toString();
    }

    /**
     * Encrypts the given plaintext using RC4 algorithm with the specified key.
     *
     * @param key the encryption key
     * @param plaintext the plaintext to encrypt
     * @return the ciphertext as a byte array
     */
    public static byte[] rc4Encrypt(byte[] key, String plaintext) {
        byte[] S = new byte[256];
        byte[] T = new byte[256];

        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = key[i % key.length];
        }

        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }

        byte[] ciphertext = new byte[plaintext.length()];
        int i = 0;
        j = 0;

        for (int k = 0; k < plaintext.length(); k++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            int t = (S[i] + S[j]) & 0xFF;
            ciphertext[k] = (byte) (plaintext.charAt(k) ^ S[t]);
        }

        return ciphertext;
    }

    /**
     * Converts the given byte array to an int array.
     *
     * @param bytes the byte array to convert
     * @return the int array
     */
    private static int[] bytesToIntArray(byte[] bytes) {
        int[] intArray = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            intArray[i] = bytes[i] & 0xFF;
        }
        return intArray;
    }

    /**
     * Adds salt to the given byte array.
     *
     * @param data the byte array to add salt to
     * @param salt the salt string
     * @return the salted byte array
     */
    private static byte[] addSaltToBytes(byte[] data, String salt) {
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        byte[] saltedData = new byte[data.length + saltBytes.length];
        System.arraycopy(data, 0, saltedData, 0, data.length);
        System.arraycopy(saltBytes, 0, saltedData, data.length, saltBytes.length);
        return saltedData;
    }
}
