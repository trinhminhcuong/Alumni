package com.example.cuong.alumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cuong.alumni.DataModel.Comment;
import com.example.cuong.alumni.R;

import java.util.List;

/**
 * Created by Cuong on 5/6/2018.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {

    Context context;
    int resource;
    List<Comment> objects;

    public CommentAdapter(@NonNull Context context, int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }
        TextView tvUserComment=convertView.findViewById(R.id.tvUserComment);
        TextView tvComment=convertView.findViewById(R.id.tvComment);

        String a=objects.get(position).getCom().toString().replace("-"," ");

        tvComment.setText(a);
        tvUserComment.setText(objects.get(position).getUsercomment().toString());
        return convertView;
    }
}
