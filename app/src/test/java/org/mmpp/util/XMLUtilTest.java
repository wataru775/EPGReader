package org.mmpp.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by wataru-n on 2016/06/17.
 */
public class XMLUtilTest {

    @Test
    public void testSplitSourceTagNameSimple() throws IOException {
        java.io.File targetFile = new java.io.File( this.getClass().getClassLoader().getResource("util/simple.txt").getPath());
        java.io.File answerFile = new java.io.File( this.getClass().getClassLoader().getResource("util/simple-answer.txt").getPath());

        String source =FileUtil.readStringFromFile(targetFile);
        String answer =FileUtil.readStringFromFile(answerFile);

        assertEquals(answer,XMLUtil.splitSourceTagName(source,"prgItem",true).get(0));
    }
    @Test
    public void testHTML1() throws IOException {
        java.io.File targetFile = new java.io.File( this.getClass().getClassLoader().getResource("util/xmlutil-test1.txt").getPath());
        java.io.File answerFile = new java.io.File( this.getClass().getClassLoader().getResource("util/xmlutil-test1.txt").getPath());

        String source =FileUtil.readStringFromFile(targetFile);
        String answer =FileUtil.readStringFromFile(answerFile);

        assertEquals(answer,XMLUtil.splitSourceTagNameWithAttribute(source,"div","class","program_225",true).get(0));

    }
}
