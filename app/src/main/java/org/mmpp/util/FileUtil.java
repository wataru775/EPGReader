package org.mmpp.util;

import java.io.File;
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

    /**
     * ファイルの内容を抽出します
     * @param file 対象ファイル
     * @return ファイルの内容
     * @throws IOException 発生例外
     */
    public static String readStringFromFile(File file) throws IOException {
        java.io.BufferedReader br = null;
        try {
            br = new java.io.BufferedReader(new java.io.FileReader(file));

            return readStringFromReader(br);
        }finally{
            if(br!=null)
                br.close();
        }

    }
}
