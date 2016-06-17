package org.mmpp.rssreader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.mmpp.rssreader.parser.Program;
import org.mmpp.rssreader.parser.suntv.ProgramItemParser;
import org.mmpp.util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asynctask_job();
    }

    public void onClickButton(View view){

    }

    // HTTPボタン押下
//非同期タスク宣言
    private AsyncJob asynctask;


    // 非同期処理を開始する
    private void asynctask_job(){
        final AsyncJob asynctask = new AsyncJob(this);

        asynctask.execute("http://sun-tv.co.jp/i_prog/data/timeTable_SUN_20160613.xml?cache=1466077422410","B","C");

    }

    public void updateTable(String result){
     //   Log.e("== result ==",result);
        java.util.List<String> tables = new LinkedList<String>();
        java.util.List<Program> programs = new LinkedList<Program>();

        programs = ProgramItemParser.getInstance().parse(result);


        // Pgogram >> Table String
        tables = new LinkedList<String>();

        for (Program program : programs) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            StringBuffer buf = new StringBuffer();
            buf.append(simpleDateFormat.format(program.start));
            buf.append(program.title);
            tables.add(buf.toString());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.rowdata, tables);

        ListView listView = (ListView)this.findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }
}