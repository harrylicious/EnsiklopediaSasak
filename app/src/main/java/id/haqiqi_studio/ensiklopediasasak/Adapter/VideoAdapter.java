package id.haqiqi_studio.ensiklopediasasak.Adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.haqiqi_studio.ensiklopediasasak.Model.*;
import id.haqiqi_studio.ensiklopediasasak.R;


/**
 * Created by Gentong on 03/02/2018.
 */
public class VideoAdapter extends ArrayAdapter<ModelVideo> implements View.OnClickListener{

    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtMeaning;
        TextView txtForm;
        TextView txtWord;
        ImageView info;
    }

    public VideoAdapter(ArrayList<ModelVideo> data, Context context) {
        super(context, R.layout.custom_row, data);
        ArrayList<ModelVideo> dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ModelVideo dataModel=(ModelVideo)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Judul: " +dataModel.getJudul(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ModelVideo dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_row, parent, false);
            viewHolder.txtMeaning = (TextView) convertView.findViewById(R.id.txtMeaning);
            viewHolder.txtForm = (TextView) convertView.findViewById(R.id.txtForm);
            viewHolder.txtWord = (TextView) convertView.findViewById(R.id.txtWord);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtMeaning.setText(dataModel.getJudul());
        viewHolder.txtForm.setText(dataModel.getType());
        viewHolder.txtWord.setText(String.valueOf(dataModel.getDurasi()));
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}