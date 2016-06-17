package org.mmpp.util;

import java.io.IOException;

/**
 * ファイルに関するユーティリティ
 * Created by wataru-n on 2016/06/16.
 */
public class FileUtil {

    /**
     * BufferedReaderから内容を取り出す
     * @param bufferedReader
     * @return ファイル内容
     */
    public static String readStringFromReader(java.io.BufferedReader bufferedReader) throws IOException{

        StringBuffer buf = new StringBuffer();
        String line="";
        boolean isReadStart = false;
        while ((line = bufferedReader.readLine()) != null) {
            buf.append(line);
            buf.append("\n");
        }
        return buf.toString();
    }

}
