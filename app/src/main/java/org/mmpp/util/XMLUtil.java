package org.mmpp.util;

/**
 * Created by wataru-n on 2016/06/17.
 */
public class XMLUtil {
    /**
     * ソースの中からタグ名で括られた範囲を分割します
     * @param source ソース
     * @param tagName タグ名
     * @return 分割ソース
     */
    public static java.util.List<String> splitSourceTagName(String source, String tagName,boolean isTag) {
        java.util.List<String> results = new java.util.ArrayList<String>();
        StringBuffer buf = new StringBuffer();
        // タグ開始、読み込み開始フラグ
        boolean flgReadStart = false;
        for (String line : source.split("\n")) {

            // タグ開始判断
            if(line.toLowerCase().contains("<" + tagName.toLowerCase())) {
                flgReadStart = true;
                buf = new StringBuffer();
                if(!isTag)
                    continue;
            }

            // タグ終了判断
            if(line.toLowerCase().contains("</" + tagName.toLowerCase())) {
                if(buf.length()==0){
                    continue;
                }
                flgReadStart = false;
                // 閉じるタグを追加
                if(isTag) {
                    buf.append(line);
                    buf.append("\n");
                }
                results.add(buf.toString());
            }
            // タグ範囲書き込み判断
            if(flgReadStart){
                buf.append(line);
                buf.append("\n");
            }
        }
        return results;
    }

}
