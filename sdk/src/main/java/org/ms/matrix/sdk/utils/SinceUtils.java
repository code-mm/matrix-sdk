package org.ms.matrix.sdk.utils;

public class SinceUtils {

    public static String since() {
        String timeStr = System.currentTimeMillis() + "";
        return
                "s" + timeStr.substring(0, 1) + "_" +
                        timeStr.substring(1, 4) + "_" +
                        timeStr.substring(4, 8) + "_" +
                        timeStr.substring(8, 12);
    }
}
