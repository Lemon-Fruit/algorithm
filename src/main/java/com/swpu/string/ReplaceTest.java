package com.swpu.string;

public class ReplaceTest {
    public static String replaceSpace(StringBuffer str) {
        int length = str.length();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if (String.valueOf(b).equals(" ")) {
                result.append("%20");
            } else {
                result.append(b);
            }
        }
        return result.toString();
    }

    public static String replaceSpace2(StringBuffer str){
        return str.toString().replaceAll("\\s", "%20");
    }
}
