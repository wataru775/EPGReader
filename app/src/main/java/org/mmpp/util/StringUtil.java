package org.mmpp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 文字列ユーティリティ
 * Created by wataru-n on 2016/06/21.
 */
public class StringUtil {
    /**
     * 文字列からMapを生成します
     * @param source 文字列
     * @return Map形式
     */
    public static Map<String,String> castStringMap(String source) {
        Map<String,String> results = new HashMap<String,String>();
        for(String line:source.split(" ")){
            Map<String,String> map = castStringKeyValue(line);
            if(map==null)
                continue;
            results.putAll(map);
        }
        return results;
    }

    /**
     * 文字列からMapを生成します。
     * 1つの要素を抽出
     * @param source 文字列
     * @return 値
     */
    private static Map<String,String> castStringKeyValue(String source){
        if(!source.contains("="))
            return null;
        Map<String,String> results = new HashMap<String,String>();
        String key = escapeQuaternion(source.substring(0,source.indexOf("=")));
        String value = escapeQuaternion(source.substring(source.indexOf("=")+1));

        results.put(key,value);
        return results;
    }

    /**
     * 前後に付いているクォーテーションを排除します
     * @param value 文字列
     * @return 値
     */
    public static String escapeQuaternion(String value){
        String currentValue = value.trim();
        if(currentValue.startsWith("\""))
            if(currentValue.endsWith("\""))
                return currentValue.substring(1,currentValue.length()-1);
        if(currentValue.startsWith("\'"))
            if(currentValue.endsWith("\'"))
                return currentValue.substring(1,currentValue.length()-1);
        return value;
    }
}
