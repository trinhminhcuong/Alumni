package com.example.cuong.alumni.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cuong.alumni.DataModel.Job;
import com.example.cuong.alumni.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Cuong on 4/25/2018.
 */

public class JobAdapter extends ArrayAdapter<Job> {

    private Context context;
    private int resource;
    private List<Job> jobs;


    public JobAdapter(@NonNull Context context, int resource, List<Job> jobs) {
        super(context, resource,jobs);
        this.context=context;
        this.resource=resource;
        this.jobs=jobs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,null);
        }

        TextView tvJobName=convertView.findViewById(R.id.tvJobName);
        TextView tvCompany=convertView.findViewById(R.id.tvCompany);
        TextView tvJobInfomation=convertView.findViewById(R.id.tvJobInfomation);

        Job job=jobs.get(position);
        tvJobName.setText(job.getJobName());
        tvCompany.setText(job.getCompanyName());
        tvJobInfomation.setText(job.getJobInfomation());


        return convertView;
    }
}
