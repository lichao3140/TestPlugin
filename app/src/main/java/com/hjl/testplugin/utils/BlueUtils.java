package com.hjl.testplugin.utils;

import android.text.TextUtils;
import com.orhanobut.logger.Logger;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import timber.log.Timber;

/**
 * 蓝牙字符转换辅助类
 *
 * @author lbgong
 */
public class BlueUtils {

    /*** 代表一个字节标志位 */
    public static final int TYPE_SIZE_1 = 1;
    /*** 代表两个字节标志位 */
    public static final int TYPE_SIZE_2 = 2;
    /*** 代表3个字节标志位 */
    public static final int TYPE_SIZE_3 = 3;
    /*** 代表4个字节标志位 */
    public static final int TYPE_SIZE_4 = 4;

    public static final int TYPE_SIZE_6 = 6;

    /*** 代表8个字节标志位 */
    public static final int TYPE_SIZE_8 = 8;

    /*** 代表16个字节标志位 */
    public static final int TYPE_SIZE_16 = 16;

    /*** 代表18个字节标志位 */
    public static final int TYPE_SIZE_17 = 17;

    /*** 代表18个字节标志位 */
    public static final int TYPE_SIZE_18 = 18;


    /*** 代表32个字节标志位 */
    public static final int TYPE_SIZE_32 = 32;

    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length == 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     *
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param src
     * @return
     */
    public static byte[] hexString2Bytes(String src) {
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            ret[i] = (byte) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return ret;
    }

    /**
     * 字符串转16进制字符串
     *
     * @param strPart
     * @return
     */
    public static String string2HexString(String strPart) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * 十六进制字符串转字符串
     *
     * @param src
     * @return
     */
    public static String hexString2String(String src) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < src.length() / 2; i++) {
            temp.append((char) Integer.valueOf(src.substring(i * 2, i * 2 + 2), 16).byteValue());
        }
        return temp.toString();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * @param mac
     * @return
     */
    public static String getMacAdress(String mac) {
        if (mac == null || "".equals(mac)) {
            return null;
        }

        StringBuilder macBuffer = new StringBuilder();
        for (int i = 0; i < mac.length(); i = i + 2) {

            if (i == mac.length() - 2) {
                macBuffer.append(mac.substring(i));
            } else {
                macBuffer.append(mac.substring(i, i + 2)).append(":");
            }

        }
        return macBuffer.toString();
    }

    public static String fixMacAddress(String mac) {
        if (TextUtils.isEmpty(mac)) {
            return null;
        }
        if (!isValidMac(mac)) {
            StringBuilder stringBuilder1 = new StringBuilder(mac);
            stringBuilder1.insert(2, ":");
            stringBuilder1.insert(5, ":");
            stringBuilder1.insert(8, ":");
            stringBuilder1.insert(11, ":");
            stringBuilder1.insert(14, ":");
            return stringBuilder1.toString();
        }
        return mac;

    }

    public static boolean isValidMac(String macStr) {
        if (TextUtils.isEmpty(macStr)) {
            return false;
        }
        String macAddressRule = "([A-Fa-f0-9]{2}[-,:]){5}[A-Fa-f0-9]{2}";
        return macStr.matches(macAddressRule);
    }


    public static String getSubString(String source, int start, int end) {
        if (TextUtils.isEmpty(source) || start >= end
                || source.length() < start || source.length() < end) {
            return "";
        }
        return source.substring(start, end);
    }


    /***
     * 16 Hex to integer
     * @param hex
     * @return
     */
    public static int convertHexToInteger(String hex) {
        try {
            BigInteger value = new BigInteger(hex, 16);
            return value.intValue();
        } catch (Exception e) {
            Logger.i(e.toString());
        }
        return 0;
    }

    /***
     * 16 Hex to long
     * @param hex
     * @return
     */
    public static long convertHexToLong(String hex) {
        try {
            return Long.valueOf(hex, 16);
        } catch (Exception e) {
            Logger.i(e.toString());
        }
        return 0;
    }

    /**
     * 16进制转换为ASCII
     * 解析设备返回值时候用
     */
    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {
            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = 0;
            try {
                decimal = Integer.parseInt(output, 16);
            } catch (Exception ignored) {
            }
            // convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    /**
     * parse String to int
     *
     * @param statusStr
     * @return
     */
    public static int parseInt(String statusStr) {
        int state = -1;
        try {
            state = Integer.parseInt(statusStr, 16);
        } catch (NumberFormatException ex) {
            Logger.i(ex.toString());
        }
        return state;
    }

    /**
     * 大小端转换 目前支持两种：1：两个字节，2：四个字节
     */
    public static String getBigSmallChange(int sizeType, String source) {
        if (TextUtils.isEmpty(source)) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();
        int startIndex = 0;
        int endIndex = 2; // 每次取2个字符

        // 处理每个段
        for (int i = sizeType - 1; i >= 0; i--) {
            startIndex = i * 2; // 每个段的起始索引
            endIndex = startIndex + 2; // 每个段的结束索引
            buffer.append(getSubString(source, startIndex, endIndex));
        }

        return buffer.toString();
    }

    /**
     * 获取16进制随机数
     *
     * @param len
     * @return
     */
    public static String randomHexString(int len) {
        try {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < len; i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            Logger.i(e.toString());
        }
        return null;
    }

    /**
     * 在前方补0 source 原字符 sizeType 字节类型 十六进制字符串，一个字节2位，两个字节4位，例如两个字节：81G9,四位
     */
    public static String padPreFix(String source, int sizeType) {
        if (source == null) {
            source = "";
        }

        int size = sizeType * 2;
        int stSize = source.length();
        int padLen = size - stSize;
        StringBuilder retValue = new StringBuilder();
        //强制限制大小
        if (padLen<0){
            return source.substring(size);
        }
        for (int i = 0; i < padLen; i++) {
            retValue.append("0");
        }
        return retValue.append(source).toString();
    }

    /**
     *
     */
    public static String formatMsg(String source, int sizeType) {
        return getBigSmallChange(sizeType, padPreFix(source, sizeType));
    }


    /**
     * 在前方补0 source 原字符 sizeType 字节类型 十六进制字符串，一个字节2位，两个字节4位，例如两个字节：81G9,四位
     */
    public static String padEndFix(String source, int sizeType) {
        if (source == null) {
            source = "";
        }

        int size = sizeType * 2;
        int stSize = source.length();
        int padLen = size - stSize;
        StringBuilder retValue = new StringBuilder();
        //强制限制大小
        if (padLen<0){
            return source.substring(size);
        }
        retValue.append(source);
        for (int i = 0; i < padLen; i++) {
            retValue.append("0");
        }
        return retValue.toString();
    }

    /**
     * 数据内容和
     */
    public static int getSumOfData(String content) {
        return Integer.parseInt(makeChecksum(content), 16);
    }

    /**
     * 十六进制求和-多字节输出
     *
     * @param hexdata
     * @return
     */
    public static String makeChecksum(String hexdata) {
        if (hexdata == null || hexdata.equals("")) {
            return "00";
        }
        hexdata = hexdata.replaceAll(" ", "");
        int total = 0;
        int len = hexdata.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexdata.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        return Integer.toHexString(total);
    }

    /**
     * 报文校验
     *
     * @param buff
     * @return
     */
    public static int addChecksum(byte cmd, byte[] buff) {
        int sum = cmd & 0xff;
        if (buff == null) {
            return 0;
        }
        for (byte b : buff) {
            sum += b & 0xff; // 确保将 byte 转换为无符号整数
        }
        return sum & 0xff;
    }

    /**
     * 十六进制求和-单字节输出
     *
     * @param data
     * @return
     */
    public static String makeChecksumSingle(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            //System.out.println(s);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex.toLowerCase();
    }

    /**
     * ASCII转换为16进制
     * 往设备设值时用
     */
    public static String convertStringToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char aChar : chars) {
            hex.append(Integer.toHexString((int) aChar));
        }
        return hex.toString();
    }

    /**
     * 获取字符串的内容字节数
     */
    public static String getByteOfData(String source) {
        if (source != null && source.length() > 0) {
            int byteChar = source.length() / 2;
            return padPreFix(Integer.toHexString(byteChar),
                    TYPE_SIZE_1);
        }
        return "00";
    }

    /**
     * 将一个整形化为十六进制，并以字符串的形式返回
     */
    private final static String[] hexArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 异或加密
     *
     * @param paramStr
     * @return
     */
    public static String encrypt(String paramStr, int key) {

        int[] datas = string2HexIntArray(paramStr);

        int[] odd_keymap = {0x18, 0x49, 0x8E, 0xF1, 0x6D, 0x3C, 0x2A, 0x5B,
                0x9D, 0x05, 0xE7, 0x86, 0x32, 0xD6, 0xF2, 0xAC};

        int[] even_keymap = {0x97, 0x5A, 0x7F, 0xE0, 0x3B, 0x24, 0x19, 0xF6,
                0x8A, 0x41, 0x63, 0xB5, 0x72, 0xA8, 0xC4, 0x0D};

        int key_even = even_keymap[(key >> 4) & 0x0F];
        int key_odd = odd_keymap[key & 0x0F];

        StringBuilder paramsBuffer = new StringBuilder();

        int len = datas.length;
        for (int i = 0; i < len; i++) {
            datas[i] ^= ((i % 2) == 0 ? key_even : key_odd);
            paramsBuffer.append(byteToHex(datas[i]));
        }
        return paramsBuffer.toString();
    }

    /**
     * int值转16进制值
     *
     * @param n
     * @return
     */
    public static String byteToHex(int n) {
        if (n < 0) {
            n = n + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexArray[d1] + hexArray[d2];
    }

    /***
     * long 转 16进制
     * @param n
     * @param sizeType
     * @return
     */
    public static String longToHex(long n, int sizeType) {
        return padPreFix(Long.toHexString(n), sizeType);
    }

    /**
     * 字符串分解成两个一组的十六进制值
     *
     * @param params
     * @return
     */
    public static int[] string2HexIntArray(String params) {
        int[] intArray = new int[params.length() / 2];
        StringBuffer s1 = new StringBuffer(params);
        int index;
        for (index = 2; index < s1.length(); index += 3) {
            s1.insert(index, ',');
        }
        String[] array = s1.toString().split(",");

        int i = 0;
        for (String string : array) {
            intArray[i++] = Integer.parseInt(string, 16);
        }

        return intArray;
    }

    /***
     *  补充足够字节的内容，不够的用0补齐
     * @param content
     * @param byteLen
     * @return
     */
    public static String getPaddingAppend(String content, int byteLen) {
        if (TextUtils.isEmpty(content)) {
            return getAppend(byteLen);
        }
        return content + getAppend(byteLen - content.length() / 2);
    }

    public static String getAppend(int byteLen) {
        if (byteLen <= 0) {
            return "";
        }
        StringBuilder append = new StringBuilder();
        for (int i = 0; i < byteLen; i++) {
            append.append("00");//一个字节补充两个0
        }
        return append.toString();
    }

    /**
     * 判断校验是否合法
     *
     * @param msg
     * @param or
     * @return
     */
    public static boolean checkORIsRight(String msg, String or) {
        boolean isRight = false;
        String check = Integer.toHexString(getSumOfData(msg));
        //Timber.d("checkORIsRight结果:%s", check);
        if (check.length() == 1) {
            check = "0" + check;
        }
        if (or.equals(check.substring(check.length() - 2).toUpperCase())) {
            isRight = true;
        }
        return isRight;
    }

    /**
     * 封装给充充有电服务器发送数据
     *
     * @param msg
     * @return
     */
    public static String sendCCData(String msg) {
        String send = "AAF5";
        //完整数据长度
        int allLength = msg.length() + 4 + 4 + 2;
        String hex_length = Integer.toHexString(allLength / 2);
        if (hex_length.length() == 1) {
            send = send + "0" + hex_length + "00";

        } else if (hex_length.length() == 2) {
            send = send + hex_length + "00";

        } else if (hex_length.length() == 3) {
            send = send + hex_length.substring(1, 3) + "0" + hex_length.charAt(0);

        } else if (hex_length.length() == 4) {
            send = send + hex_length.substring(2, 4) + hex_length.substring(0, 2);

        }
        //计算范围包含从命令代码和数据域-信息域1-序列号域1
        String check = Integer.toHexString(getSumOfData(msg.substring(4)));
        if (check.length() == 1) {
            check = "0" + check;
        }
        send = send + msg + check.substring(check.length() - 2);
        //Timber.d("sendCCData结果:%s", send.toUpperCase(Locale.ROOT));
        return send.toUpperCase(Locale.ROOT);
    }

    /**
     * 封装给管理平台发送数据
     *
     * @param msg
     * @return
     */
    public static String sendGLData(String msg) {
        String send = "F5AC";
        //完整数据长度
        int allLength = msg.length() + 4 + 4 + 2;
        String hex_length = Integer.toHexString(allLength / 2);
        if (hex_length.length() == 1) {
            send = send + "0" + hex_length + "00";

        } else if (hex_length.length() == 2) {
            send = send + hex_length + "00";

        } else if (hex_length.length() == 3) {
            send = send + hex_length.substring(1, 3) + "0" + hex_length.charAt(0);

        } else if (hex_length.length() == 4) {
            send = send + hex_length.substring(2, 4) + hex_length.substring(0, 2);

        }
        //计算范围包含从命令代码和数据域-信息域1-序列号域1
        String check = Integer.toHexString(getSumOfData(msg.substring(4)));
        if (check.length() == 1) {
            check = "0" + check;
        }
        send = send + msg + check.substring(check.length() - 2);
        Timber.d("sendGLData结果:%s", send.toUpperCase(Locale.ROOT));
        return send.toUpperCase(Locale.ROOT);
    }

    /**
     * 封装串口发送数据
     *
     * @param msg json内容
     * @return
     */
    public static String sendData(String msg) {
        String send = "55";
        int msg_length = msg.length();
        String hex_length = Integer.toHexString(msg_length);
        if (hex_length.length() == 1) {
            send = send + hex_length + "000";

        } else if (hex_length.length() == 2) {
            send = send + hex_length + "00";

        } else if (hex_length.length() == 3) {
            send = send + hex_length.substring(1, 3) + "0" + hex_length.charAt(0);

        } else if (hex_length.length() == 4) {
            send = send + hex_length.substring(2, 4) + hex_length.substring(0, 2);

        }
        String strToHex = str2HexStr(msg);
        String check = Integer.toHexString(getSumOfData(strToHex));
        send = send + strToHex + check.substring(check.length() - 2);
        return send.toLowerCase(Locale.ROOT);
    }

    /**
     * 检验和 异或 再 取反
     *
     * @param in
     * @return
     */
    public static String checkSum_XOR(String in) {
        String bbc = null;
        int i = 0, j = 0;
        int len = in.length();
        short[] inb = new short[len];

        for (i = 0; i < len; i++) {
            //将String里的每一个char转换为Hex
            inb[i] = charToHex(in.charAt(i));
        }

        //将每两个Hex合并成一个byte
        for (i = 0; i < len; i++) {
            inb[j] = (byte) (((inb[i] << 4) & 0x00f0) | ((inb[i + 1]) & 0x000f));
            i++;
            j++;
        }
        //校验值
        byte temp = 0x00;

        //异或
        for (i = 0; i < len / 2; i++) {
            temp ^= inb[i];
        }

        //取反  二进制Integer.toBinaryString()  十六进制Integer.toHexString()
        bbc = Integer.toHexString(~temp).toUpperCase();
        return bbc.length() >= 2 ? bbc.substring(bbc.length() - 2) : "0" + bbc;
    }

    /**
     * 数据校验 异或处理
     */
    public static String checkBCC(String content) {
        int a = 0;
        for (int i = 0; i < content.length() / 2; i++) {
            a = a ^ Integer.parseInt(content.substring(i * 2, (i * 2) + 2), 16);
        }
        String result = Integer.toHexString(a);
        if (result.length() == 1) {
            return "0" + result;
        } else {
            return result;
        }
    }

    /**
     * 将单个char转换为Hex
     *
     * @param x
     * @return
     */
    public static short charToHex(char x) {
        short result = 0;
        switch (x) {
            case 'a':
            case 'A':
                result = 10;
                break;
            case 'b':
            case 'B':
                result = 11;
                break;
            case 'c':
            case 'C':
                result = 12;
                break;
            case 'd':
            case 'D':
                result = 13;
                break;
            case 'e':
            case 'E':
                result = 14;
                break;
            case 'f':
            case 'F':
                result = 15;
                break;
            default:
                result = (short) Character.getNumericValue(x);
                break;
        }
        return result;
    }

    /**
     * 左侧补零
     *
     * @param s      s的长度超过length,返回s;小于length，左侧不足补零
     * @param length 返回字符串长度
     * @return
     */
    public static String leftZeroShift(String s, int length) {
        if (s == null || s.length() > length) {
            return s;
        }
        String str = getZero(length) + s;
        str = str.substring(str.length() - length);
        return str;
    }

    /**
     * 右侧补零
     *
     * @param s      s的长度超过length,返回s;小于length，右侧不足补零
     * @param length 返回字符串长度
     * @return
     */
    public static String rightZeroShift(String s, int length) {
        if (s == null || s.length() > length) {
            return s;
        }
        String str = s + getZero(length);
        str = str.substring(0, length);
        return str;
    }

    /**
     * 获取0的字符串
     *
     * @param length
     * @return
     */
    public static String getZero(int length) {
        StringBuilder str = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            str.append("0");
        }
        return str.toString();
    }

    /**
     * 卡号反转；eg: 5C30A50D --> 0DA5305C
     *
     * @param number
     * @return
     */
    public static String numberReverse(String number) {
        StringBuilder result = new StringBuilder();
        if (!TextUtils.isEmpty(number)) {
            int length = number.length();
            if (length > 1 && length % 2 == 0) {
                for (int i = 0; i < length; i++) {
                    if (i % 2 == 0) {
                        result.append(number.charAt(length - i - 2));
                    } else {
                        result.append(number.charAt(length - i));
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 十六进制相减
     *
     * @param hexValue
     * @param hexMin
     * @return
     */
    public static String hexMinusHex(String hexValue, String hexMin) {
        long value = Long.parseLong(hexValue, 16);
        long min = Long.parseLong(hexMin, 16);
        String result = Long.toHexString(value - min);
        if (value < min) {
            result = result.substring(result.length() - 2);
        }
        if (result.length() % 2 != 0) {
            result = "0" + result;
        }
        return result.toUpperCase();
    }

    /**
     * 去掉字符串前面的 00
     *
     * @param input
     * @return
     */
    public static String removeLeadingZeros(String input) {
        // 使用正则表达式替换前面的所有“00”
        return input.replaceAll("^00+", "");
    }


    /**
     * 封装发送给南网电动的数据
     * @param msg
     * @return
     */
    public static String sendNWDDData(String msg) {
        String send = "68";
        int msg_length = msg.length()/2;
        String hex_length = Integer.toHexString(msg_length);
        if (hex_length.length() == 1) {
            send = send + hex_length + "000";
        } else if (hex_length.length() == 2) {
            send = send + hex_length + "00";
        } else if (hex_length.length() == 3) {
            send = send + hex_length.substring(1, 3) + "0" + hex_length.charAt(0);
        } else if (hex_length.length() == 4) {
            send = send + hex_length.substring(2, 4) + hex_length.substring(0, 2);
        }
        send = send + msg;

        return send.toUpperCase(Locale.ROOT);
    }

    /**
     * String转换成BCD码
     * @param strValue 转换内容
     * @param bcdLength 转换结果字节长度
     * @return
     */
    public static String stringToBCD(String strValue, int bcdLength) {
        int strLength = strValue.length();
        int bcdActualLength = (strLength + 1) / 2;

        if (bcdLength < bcdActualLength) {
            throw new IllegalArgumentException("bcdLength is too short for the given input string.");
        }

        byte[] bcd = new byte[bcdLength];
        int startIndex = bcdLength - bcdActualLength;

        for (int i = 0; i < strLength; i++) {
            int digit = Character.digit(strValue.charAt(i), 10);
            if (i % 2 == 0) {
                bcd[startIndex + i / 2] = (byte) (digit << 4);
            } else {
                bcd[startIndex + i / 2] |= (byte) digit;
            }
        }

        // 将字节数组转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : bcd) {
            hexString.append(String.format("%02X", b));
        }

        return hexString.toString();
    }

    /**
     * CP56格式转时间---decodeCP56Time2a(hexStringToByteArray("2D311E08170718"))
     * @param bytes
     * @return
     */
    public static LocalDateTime decodeCP56Time2a(byte[] bytes) {
        if (bytes.length != 7) {
            throw new IllegalArgumentException("Invalid CP56Time2a format");
        }

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int milliseconds = buffer.getShort() & 0xFFFF;
        int minute = buffer.get() & 0xFF;
        int hour = buffer.get() & 0xFF;
        int day = buffer.get() & 0xFF;
        int month = buffer.get() & 0xFF;
        int year = buffer.get() & 0xFF;

        int second = milliseconds / 1000;
        int millisecond = milliseconds % 1000;

        // year is encoded as an offset from 2000
        year = year + 2000;

        // mask out the invalid bits
        minute = minute & 0x3F;
        hour = hour & 0x1F;
        day = day & 0x1F;
        month = month & 0x0F;

        // 防止异常出现秒超过60
        second = second % 60;

        return LocalDateTime.of(year, month, day, hour, minute, second, millisecond * 1_000_000);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    /**
     * 当前时间转CP56格式---byteArrayToHexString(encodeCP56Time2a(LocalDateTime.now(ZoneOffset.UTC)))
     * @param dateTime
     * @return
     */
    public static byte[] encodeCP56Time2a(LocalDateTime dateTime) {
        ByteBuffer buffer = ByteBuffer.allocate(7);

        int year = dateTime.getYear() - 2000;
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();
        int millisecond = dateTime.get(ChronoField.MILLI_OF_SECOND);

        int milliseconds = second * 1000 + millisecond;

        buffer.putShort((short) milliseconds);
        buffer.put((byte) minute);
        buffer.put((byte) hour);
        buffer.put((byte) day);
        buffer.put((byte) month);
        buffer.put((byte) year);

        return buffer.array();
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    //二进制字符串转十六进制
    public static String binaryToHex(String binaryString) {
        // 将二进制字符串解析为整数
        long decimalValue = Long.parseLong(binaryString, 2);
        // 将整数转换为十六进制字符串
        return Long.toHexString(decimalValue).toUpperCase();
    }

    /**
     * 转换二进制数值
     *
     * @param value 要转换的数值
     * @param byteLen 该值占用的字节大小
     * @param withDivider 是否每8个bit增加一个分隔符
     * @return
     */
    public static String toBinString(long value, int byteLen, boolean withDivider) {

        int bitLen = byteLen * 8;

        char[] chars = new char[bitLen];
        Arrays.fill(chars, '0');
        int charPos = bitLen;
        do {
            --charPos;
            if ((value & 1) > 0) {
                chars[charPos] = '1';
            }
            value >>>= 1;
        } while (value != 0 && charPos > 0);

        if (withDivider && byteLen > 1) {
            StringBuilder stringBuilder = new StringBuilder();

            boolean alreadyAppend = false;
            for (int i = 0; i < byteLen; i++) {
                if (alreadyAppend) {
                    stringBuilder.append(' ');
                } else {
                    alreadyAppend = true;
                }
                stringBuilder.append(chars, i * 8, 8);
            }

            return stringBuilder.toString();
        }

        return new String(chars);
    }
}
