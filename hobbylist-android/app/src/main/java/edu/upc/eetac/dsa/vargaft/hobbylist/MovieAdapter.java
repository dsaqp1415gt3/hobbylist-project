package edu.upc.eetac.dsa.vargaft.hobbylist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.vargaft.hobbylist.api.Lista;

/**
 * Created by Vargaft on 07/06/2015.
 */
public class MovieAdapter extends BaseAdapter {

    ArrayList<Lista> data;
    LayoutInflater inflater;

    public MovieAdapter(Context context, ArrayList<Lista> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDirector;
        TextView tvTag;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Lista) getItem(position)).getListid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_movie, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView
                    .findViewById(R.id.tvTitle);
            viewHolder.tvDirector = (TextView) convertView
                    .findViewById(R.id.tvDirector);
            viewHolder.tvTag = (TextView) convertView
                    .findViewById(R.id.tvTag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = data.get(position).getTitle();
        String director = data.get(position).getDirector();
        String tag = data.get(position).getTag();
        viewHolder.tvTitle.setText(title);
        viewHolder.tvDirector.setText(director);
        viewHolder.tvTag.setText(tag);
        return convertView;
    }
}
