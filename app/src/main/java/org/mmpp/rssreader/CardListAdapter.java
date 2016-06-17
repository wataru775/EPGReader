package org.mmpp.rssreader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.mmpp.rssreader.parser.Program;

import java.text.SimpleDateFormat;

public class CardListAdapter extends ArrayAdapter<Program> {

    LayoutInflater mInflater;
    PackageManager packageManager;

    public CardListAdapter(Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rowdata, parent, false);
        }
        Program program = getItem(position);


        ((TextView) convertView.findViewById(R.id.start)).setText(new SimpleDateFormat("MM/dd HH:mm").format(program.start) +" - "+new SimpleDateFormat("HH:mm").format(program.end));
        ((TextView) convertView.findViewById(R.id.title)).setText(program.title);

        return convertView;
    }
}
