package org.mmpp.rssreader.parser.suntv;

import org.mmpp.rssreader.parser.AbstractProgramItem;
import org.mmpp.rssreader.parser.Program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * サンテレビの番組情報格納変数
 * Created by wataru-n on 2016/06/14.
 */
public class ProgramItem extends AbstractProgramItem{

    /**
     * 終了時間
     * tag : <end>
     */
    public String end;

    /**
     * 表示アイコン
     */
    public String icon;

    /**
     * 放送日
     * startは開始時間の文字列のみを格納しています
     */
    public String date;
    /**
     * 放送局名
     */
    public String channel = "サンテレビ";

    @Override
    public Program convert() {

        Program program = new Program();


        program.title = this.name;
        try {
            program.start = convertDate(this.date,this.start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            program.end = convertDate(this.date,this.end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;


        return program;
    }

    /**
     * 文字列の日付情報を日付変数に変換する
     * @param date 日付
     * @param time 時間
     * @return 日付変数
     * @throws ParseException 書式エラー
     */
    public static Date convertDate(String date, String time) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");

        if(time.length()==3)
            time="0"+time;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date+"-"+time));

        return calendar.getTime();
    }
}
