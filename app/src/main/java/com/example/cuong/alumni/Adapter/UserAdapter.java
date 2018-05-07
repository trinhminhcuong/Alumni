package com.example.cuong.alumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cuong.alumni.DataModel.User;
import com.example.cuong.alumni.R;
import java.util.List;

/**
 * Created by Cuong on 5/5/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;
    List<User> objects;


    public UserAdapter(@NonNull Context context, int resource,List<User> objects) {
        super(context, resource,objects);
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

        TextView tvName08=convertView.findViewById(R.id.tvName08);
        TextView tvDob08=convertView.findViewById(R.id.tvDob08);
        TextView tvJob08=convertView.findViewById(R.id.tvJob08);
        TextView tvYear08=convertView.findViewById(R.id.tvYear08);

        User u=objects.get(position);
        tvName08.setText(u.getUserName());
        tvDob08.setText(u.getDob());
        tvJob08.setText(u.getJob());
        tvYear08.setText(u.getStartYear()+"-"+u.getEndYear());

       return convertView;
    }
}
