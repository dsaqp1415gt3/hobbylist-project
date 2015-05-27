package edu.upc.eetac.dsa.vargaft.hobbylist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.vargaft.hobbylist.api.Movie;

public class MovieAdapter extends BaseAdapter {

    ArrayList<Movie> data;
    LayoutInflater inflater;

    public MovieAdapter(Context context, ArrayList<Movie> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
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
        return ((Movie) getItem(position)).getMovieid();
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvUsername;
        TextView tvTag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_movie, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView
                    .findViewById(R.id.tvTitle);
            viewHolder.tvUsername = (TextView) convertView
                    .findViewById(R.id.tvUsername);
            viewHolder.tvTag = (TextView) convertView
                    .findViewById(R.id.tvTag);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = data.get(position).getTitle();
        String username = data.get(position).getUsername();
        String tag = data.get(position).getTag();
        viewHolder.tvTitle.setText(title);
        viewHolder.tvUsername.setText(username);
        viewHolder.tvTag.setText(tag);
        return convertView;
    }
}
