package com.example.cuong.alumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cuong.alumni.DataModel.Topic;
import com.example.cuong.alumni.R;

import java.util.List;

/**
 * Created by Cuong on 5/6/2018.
 */

public class TopicAdapter extends ArrayAdapter<Topic> {

    Context context;
    int resource;
    List<Topic> topics;



    public TopicAdapter(@NonNull Context context, int resource,List<Topic> topics) {
        super(context, resource,topics);
        this.context=context;
        this.resource=resource;
        this.topics=topics;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(resource,null);
        }

        TextView tvTopicName=convertView.findViewById(R.id.tvTopicName);
        TextView tvOwner=convertView.findViewById(R.id.tvOwner);

        Topic t=topics.get(position);
        String a=t.getContent().toString().replace("-"," ");
        tvTopicName.setText(a);
        tvOwner.setText("username: "+t.getUser());

        return convertView;
    }
}
