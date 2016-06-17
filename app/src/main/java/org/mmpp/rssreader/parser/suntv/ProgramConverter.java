package org.mmpp.rssreader.parser.suntv;

import org.mmpp.rssreader.parser.Program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wataru-n on 2016/06/16.
 */
public class ProgramConverter {

    public static java.util.List<Program> convert(java.util.List<ProgramItem> programItems){
        java.util.List<Program> programs = new java.util.LinkedList<Program>();

        for(ProgramItem programItem : programItems){
            programs.add(convert(programItem));
        }

        return programs;
    }
    public static Program convert(ProgramItem programItem){

        Program program = new Program();

        program.channelName = "サンテレビ";

        program.title = programItem.name;
        try {
            program.start = convertDate(programItem.date,programItem.start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            program.end = convertDate(programItem.date,programItem.end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;


        return program;
    }

    public static Date convertDate(String date,String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-hhmm");

        return simpleDateFormat.parse(date+"-"+time);
    }
}
