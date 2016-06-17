package org.mmpp.rssreader.parser.suntv;

import org.mmpp.rssreader.parser.AbstractProgramParser;

/**
 * サンテレビの番組表から番組情報を抽出するクラス
 * Created by wataru-n on 2016/06/14.
 */
public class ProgramItemParser extends AbstractProgramParser<ProgramItem> {
    // 唯一のパーサー
    private static ProgramItemParser programItemParser = new ProgramItemParser();

    /**
     * インスタンスの取得メソッド
     * @return Paserメソッド
     */
    public static ProgramItemParser getInstance(){
        return programItemParser;
    }
    /**
     * 番組情報解析
     * @param source 対象のHTMLソース
     * @return 番組情報一覧
     */
    @Override
    public java.util.List<ProgramItem> parseItems(String source){
        java.util.ArrayList<ProgramItem> programItems = new java.util.ArrayList<ProgramItem>();

        // 放送日を取得します
        String startDate = readAttribute(source,"prgDataEntry","date");

        // 放送日の番組情報を解析します
        // prgItemタグの間
        for(String dataItemSource : splitSourceTagName(source,"prgData")) {
            String yobi = readAttribute(dataItemSource,"prgData","yobi");

            for(String prgItemSource : getTagValues(dataItemSource,"prgItem")) {

                ProgramItem programItem = parseElement(prgItemSource);

                programItem.date = String.valueOf( Integer.valueOf(startDate) + Integer.valueOf(yobi) -1);
                programItems.add(programItem);
            }
        }
        return programItems;
    }

    /**
     * ソースの中からタグ名で括られた範囲を分割します
     * @param source ソース
     * @param tagName タグ名
     * @return 分割ソース
     */
    private java.util.List<String> splitSourceTagName(String source, String tagName) {
        java.util.List<String> results = new java.util.ArrayList<String>();
        StringBuffer buf = new StringBuffer();
        // タグ開始、読み込み開始フラグ
        boolean flgReadStart = false;
        for (String line : source.split("\n")) {

            // タグ開始判断
            if(contentEquals(line,tagName)) {
                flgReadStart = true;
                buf = new StringBuffer();
            }
            // タグ終了判断
            if(contentEquals(line,"/"+tagName)) {
                if(buf.length()==0){
                    continue;
                }
                flgReadStart = false;
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

    private java.util.List<String> getTagValues(String source, String tagName) {
        java.util.List<String> results = new java.util.ArrayList<String>();
            StringBuffer buf = new StringBuffer();
            boolean isReadStart = false;
            for (String line : source.split("\n")) {
                if(contentEquals(line,tagName)) {
//                    System.out.println("   == Start == : " + results.size());
                    isReadStart = true;
                    buf = new StringBuffer();
                    continue;
                }
                if(contentEquals(line,"/"+tagName)) {
                    if(buf.length()==0){
                        continue;
                    }
//                    System.out.println("   ==  END  == " + results.size() + " size : "+buf.length());
                    isReadStart = false;
                    results.add(buf.toString());
                    continue;
                }
                if(isReadStart){
                    buf.append(line);
                    buf.append("\n");
                }
            }
            return results;
        }

    public ProgramItem parseElement(String prgItemValue){
//        System.out.println("----------------------");
//        System.out.println(prgItemValue);
//        System.out.println("======================");
        ProgramItem programItem =new ProgramItem();
        programItem.name = getTagValue(prgItemValue, "name");
        programItem.start = getTagValue(prgItemValue, "st");
        programItem.end = getTagValue(prgItemValue, "en");

        programItem.icon = getTagValue(prgItemValue, "icon");

        return programItem;

    }
    private boolean contentEquals(String line,String tagName) {
        String tag = "<" + tagName ;
        return (line.toLowerCase().contains(tag.toLowerCase()));
    }

    public static String getTagValue(String planValue,String tagName) throws StringIndexOutOfBoundsException {
        try{
            return planValue.substring(planValue.indexOf(tagName)+tagName.length()+1,planValue.indexOf("/"+tagName)-1);
        }catch(Exception e){

        }
        return null;
    }


    /**
     * タグ名のアトリビュートの値を取得します
     * @param source ソース
     * @param tagName 対象タグ名
     * @param attributeName アトリビュート
     * @return アトリビュート値
     */
    public static String readAttribute(String source, String tagName, String attributeName) {
        String tagSource = readTag(source,tagName);
        if(tagSource==null)
            return null;

        tagSource = tagSource.substring(1,tagSource.length()-1);
        for(String attributeSource : tagSource.split(" ")){
            // atttribute='a'
            if(attributeSource.indexOf("=")<0)
                continue;
            // key
            String key = attributeSource.split("=")[0];
            String value = attributeSource.split("=")[1];

            // trim value
            return trim(value);
        }
        return null;
    }

    private static String trim(String value) {
        String result = value.trim();
        if(result.startsWith("'")|result.endsWith("'"))
            return result.substring(1,result.length()-1);
        if(result.startsWith("\"")|result.endsWith("\""))
            return result.substring(1,result.length()-1);
        return result;
    }

    public static String readTag(String source, String tagName) {
        try{
            int start = source.indexOf("<"+tagName);
            return source.substring(start,source.indexOf(">",start)+1);
        }catch(Exception e){

        }
        return null;
    }
}
