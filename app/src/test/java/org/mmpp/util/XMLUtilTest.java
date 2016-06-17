package org.mmpp.util;

import org.junit.Test;
import org.mmpp.rssreader.parser.suntv.ProgramItem;
import org.mmpp.rssreader.parser.suntv.ProgramItemParser;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by wataru-n on 2016/06/17.
 */
public class XMLUtilTest {
    private static XMLUtil util;

    @Test
    public void testSplitSourceTagNameSimple() throws IOException {
        java.io.File targetFile = new java.io.File( this.getClass().getClassLoader().getResource("util/simple.txt").getPath());
        java.io.File answerFile = new java.io.File( this.getClass().getClassLoader().getResource("util/simple-answer.txt").getPath());

        String source =FileUtil.readStringFromFile(targetFile);
        String answer =FileUtil.readStringFromFile(answerFile);

        ;

        assertEquals(answer,util.splitSourceTagName(source,"prgItem",true).get(0));
    }
}
