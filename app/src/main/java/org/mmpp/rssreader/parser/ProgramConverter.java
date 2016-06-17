package org.mmpp.rssreader.parser;

import java.util.List;

/**
 * Created by wataru-n on 2016/06/17.
 */
public class ProgramConverter<E> {
    static ProgramConverter converter;
    public static <E extends AbstractProgramItem> List<Program> convert(List<E> items) {
        java.util.List<Program> programs = new java.util.LinkedList<Program>();

        for(AbstractProgramItem programItem : items){
            programs.add(programItem.convert());
        }

        return programs;
    }
}
