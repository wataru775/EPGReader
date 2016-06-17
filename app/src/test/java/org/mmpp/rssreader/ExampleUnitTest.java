package org.mmpp.rssreader;

import org.junit.Test;
import org.mmpp.rssreader.parser.suntv.ProgramItem;
import org.mmpp.rssreader.parser.suntv.ProgramItemParser;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        java.io.BufferedReader br=null;
        try{

            java.net.URL url = new java.net.URL("http://sun-tv.co.jp/i_prog/data/timeTable_SUN_20160614.xml");
            java.net.URLConnection conn = url.openConnection();

        }catch(java.net.MalformedURLException e){
e.printStackTrace();
        }catch(java.io.IOException e){

        }finally{
            if(br!=null) {
                br.close();
            }
        }


        assertEquals(4, 2 + 2);
    }
}