package org.mmpp.rssreader.parser.suntv;

import org.mmpp.rssreader.parser.AbstractProgramParser;
import org.mmpp.rssreader.parser.Program;

import java.util.List;

/**
 * Created by wataru-n on 2016/06/14.
 * 			<prgItem>
 <st>2530</st>
 <en>2600</en>
 <name>くまみこ</name>
 <icon>[終]</icon>
 <rate>決断◇山奥にある熊を奉る神社の巫女まちは、都会の学校に行きたい！世間知らずのまちに、後見人のクマがあらゆる試練を与える！</rate>
 <content_nibble>
 <level_1>アニメ／特撮</level_1>
 <level_2>国内アニメ</level_2>
 </content_nibble>
 <extended_event>
 <name>出演者</name>
 <item>【声の出演】
 日岡なつみ
 安元洋貴
 興津和幸
 喜多村英梨
 ほか</item>
 </extended_event>
 </prgItem>

 */
public class ProgramItemParser extends AbstractProgramParser {
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
    public java.util.List<ProgramItem> parseItems(String source){
        java.util.ArrayList<ProgramItem> programItems = new java.util.ArrayList<ProgramItem>();

        String startDate = readAttribute(source,"prgDataEntry","date");

        // 要素を取り出す
        // prgItemタグの間
        for(String dataItemSource : getTagValues2(source,"prgData")) {
            String yobi = readAttribute(dataItemSource,"prgData","yobi");

            for(String prgItemSource : getTagValues(dataItemSource,"prgItem")) {

                ProgramItem programItem = parseElement(prgItemSource);

                programItem.date = String.valueOf( Integer.valueOf(startDate) + Integer.valueOf(yobi) -1);
                programItems.add(programItem);
            }
        }
        return programItems;
    }

    private java.util.List<String> getTagValues2(String source, String tagName) {
        java.util.List<String> results = new java.util.ArrayList<String>();
        StringBuffer buf = new StringBuffer();
        boolean isReadStart = false;
        for (String line : source.split("\n")) {
            if(contentEquals(line,tagName)) {
//                    System.out.println("   == Start == : " + results.size());
                isReadStart = true;
                buf = new StringBuffer();
            }
            if(contentEquals(line,"/"+tagName)) {
                if(buf.length()==0){
                    continue;
                }
//                    System.out.println("   ==  END  == " + results.size() + " size : "+buf.length());
                isReadStart = false;
                results.add(buf.toString());
            }
            if(isReadStart){
                buf.append(line);
                buf.append("\n");
            }
        }
        return results;
    }

    private java.util.List<String> getTagValues(String source, String tagName) {
        java.util.List<String> results = new java.util.ArrayList<String>();

//        System.out.println("----------------------");
//        System.out.println(source);
//        System.out.println("======================");

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

    @Override
    public List<Program> parse(String source) {
        return ProgramConverter.convert(parseItems(source));
    }
}
