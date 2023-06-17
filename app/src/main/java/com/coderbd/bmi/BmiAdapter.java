package com.coderbd.bmi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


    public class BmiAdapter extends BaseAdapter {
        private final Context context;
        private List<BmiData> bmiDataList;
        LayoutInflater inflater;
        public BmiAdapter(Context context, List<BmiData> contactList) {
            this.context = context;
            this.bmiDataList = contactList;

        }

        @Override
        public int getCount() {
            return bmiDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return bmiDataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v=View.inflate(context, R.layout.list, null);
            // TextView ids=(TextView)v.findViewById(R.id.view_id);
            TextView name=(TextView)v.findViewById(R.id.view_name);
            TextView status=(TextView)v.findViewById(R.id.view_status);
            TextView score=(TextView)v.findViewById(R.id.view_score);
            TextView date=(TextView)v.findViewById(R.id.view_date);
            // set Text
            // ids.setText(contactList.get(i).getID());
            name.setText(bmiDataList.get(i).get_name());
            status.setText(bmiDataList.get(i).get_status());
            score.setText(bmiDataList.get(i).get_score());
            date.setText(bmiDataList.get(i).getDate());
            return v;
        }
    }
