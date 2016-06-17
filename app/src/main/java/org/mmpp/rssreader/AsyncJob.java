package org.mmpp.rssreader;

import android.os.AsyncTask;
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
import java.util.LinkedList;

/**
 * Created by wataru-n on 2016/06/16.
 */
public class AsyncJob extends AsyncTask<String,String,String> {
    private MainActivity _main;

    public AsyncJob(MainActivity main) {
        super();
        _main = main;
    }

    @Override
    protected String doInBackground(String[] params) {
        String result = "";

        String urlPath = params[0];
        BufferedReader br = null;

        try {
            URL url = new URL(urlPath);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            result = FileUtil.readStringFromReader(br);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // e.printStackTrace();
                }
            }
        }

        return result;
    }
    @Override
    protected void onPostExecute(String result) {
        _main.updateTable(result);
    }

}
