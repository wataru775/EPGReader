package org.mmpp.util;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by wataru-n on 2016/06/21.
 */
public class StringUtilTest {

    @Test
    public void testCastStringMap1(){

        String line = "a=b";

        Map<String,String> result = StringUtil.castStringMap(line);

        assertEquals(1,result.size());
        assertEquals(new String[]{"a"},result.keySet().toArray(new String[1]));
        assertEquals("b",result.get("a"));
    }
    @Test
    public void testCastStringMap2(){

        String line = "a=b b=c";

        Map<String,String> result = StringUtil.castStringMap(line);

        assertEquals(2,result.size());
        assertEquals(new String[]{"a","b"},result.keySet().toArray(new String[2]));
        assertEquals("b",result.get("a"));
        assertEquals("c",result.get("b"));
    }
    @Test
    public void testCastStringMap3(){

        String line = "a=\'b\' b=\"c\"";

        Map<String,String> result = StringUtil.castStringMap(line);

        assertEquals(2,result.size());
        assertEquals(new String[]{"a","b"},result.keySet().toArray(new String[2]));
        assertEquals("b",result.get("a"));
        assertEquals("c",result.get("b"));
    }
    @Test
    public void testCastStringMap4(){

        String line = "\"a\"=\'b\'   \"b\"=\"c\"";

        Map<String,String> result = StringUtil.castStringMap(line);

        assertEquals(2,result.size());
        assertEquals(new String[]{"a","b"},result.keySet().toArray(new String[2]));
        assertEquals("b",result.get("a"));
        assertEquals("c",result.get("b"));
    }

    @Test
    public void testEscapeQuaternion(){
        assertEquals("test",StringUtil.escapeQuaternion("\"test\""));
        assertEquals("test",StringUtil.escapeQuaternion("\'test\'"));
        assertEquals("test",StringUtil.escapeQuaternion("  \'test\'"));
        assertEquals("test",StringUtil.escapeQuaternion("  \'test\'  "));
    }
}
