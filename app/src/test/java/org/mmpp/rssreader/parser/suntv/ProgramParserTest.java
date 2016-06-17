package org.mmpp.rssreader.parser.suntv;

import org.junit.Test;
import org.mmpp.util.FileUtil;

import static org.junit.Assert.*;

/**
 * Created by wataru-n on 2016/06/14.
 */
public class ProgramParserTest {

    @Test
    /**
     * 番組内容を抽出するテスト
     * ファイルの中に要素だけが入っています
     */
    public void testParseElement1() throws Exception{
        java.io.File myTestFile = new java.io.File( this.getClass().getClassLoader().getResource("suntv/suntv-element.txt").getPath());

        java.io.BufferedReader bis = null;
        try {
            bis = new java.io.BufferedReader(new java.io.FileReader(myTestFile));

            String source = FileUtil.readStringFromReader(bis);

            ProgramItem programItem = ProgramItemParser.getInstance().parseElement(source);

            assertNotNull(programItem);
            assertNotNull(programItem.name);
            assertEquals("くまみこ",programItem.name);
            assertEquals("2530",programItem.start);
            assertEquals("2600",programItem.end);
            assertEquals("[終]",programItem.icon);
        }finally{
            if(bis!=null)
                bis.close();
        }
    }

    @Test
    public void testParseElement2() throws Exception{

        java.io.File myTestFile = new java.io.File( this.getClass().getClassLoader().getResource("suntv/suntv-elements.txt").getPath());

        java.io.BufferedReader bis = null;
        try {
            bis = new java.io.BufferedReader(new java.io.FileReader(myTestFile));

            String source = FileUtil.readStringFromReader(bis);
            ProgramItem programItem = ProgramItemParser.getInstance().parseElement(source);


            assertNotNull(programItem);
            assertNotNull(programItem.name);
            assertEquals("くまみこ",programItem.name);
        }finally{
            if(bis!=null)
                bis.close();
        }
    }
    @Test
    public void testParse3() throws Exception{
        java.io.File myTestFile = new java.io.File( this.getClass().getClassLoader().getResource("suntv/suntv-fulldata.xml").getPath());

        java.io.BufferedReader bis = null;
        try {
            bis = new java.io.BufferedReader(new java.io.FileReader(myTestFile));
            String source = FileUtil.readStringFromReader(bis);

            java.util.List<ProgramItem> programItems = ProgramItemParser.getInstance().parseItems(source);
            for(ProgramItem programItem : programItems) {


                assertNotNull(programItem);
                assertNotNull(programItem.name);
                System.out.println( programItem.date + " " + programItem.start);
            }
        }finally{
            if(bis!=null)
                bis.close();
        }
    }


    @Test
    public void testGetTagValue(){
        String planText = " <name>くまみこ</name>";
        String value = ProgramItemParser.getTagValue(planText,"name");

        assertEquals("くまみこ",value);
    }
    @Test
    public void testGetTagValue2() {
        String planText = "\t\t\t\t\t<item>【声の出演】\n" +
                "日岡なつみ\n" +
                "安元洋貴\n" +
                "興津和幸\n" +
                "喜多村英梨\n" +
                "ほか</item>\n";
        String value = ProgramItemParser.getTagValue(planText,"item");

        assertEquals("【声の出演】\n" +
                "日岡なつみ\n" +
                "安元洋貴\n" +
                "興津和幸\n" +
                "喜多村英梨\n" +
                "ほか", value);
    }

    @Test
    public void testReadAttribute(){
        String source ="\t<prgDataEntry date='20160620'>\n";
        String startDate = ProgramItemParser.readAttribute(source,"prgDataEntry","date");

        assertEquals("20160620",startDate);
    }
    @Test
    public void testReadTag(){
        String source ="\t<prgDataEntry date='20160620'>\n";
        String startDate = ProgramItemParser.readTag(source,"prgDataEntry");

        assertEquals("<prgDataEntry date='20160620'>",startDate);
    }


}
