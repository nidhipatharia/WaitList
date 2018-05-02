package com.nidhi.waitlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class WaitingListAdapter extends ArrayAdapter {

    public WaitingListAdapter(Context context, int resource) {
        super(context, resource);
    }
    List list = new ArrayList();

    public void add(UserWaiting userWaiting) {
        list.add(userWaiting);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserWaitingHolder userWaitingHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_waiting_list_row,parent,false);
            userWaitingHolder = new UserWaitingHolder();
            userWaitingHolder.tvEvent_Id = (TextView) row.findViewById(R.id.tvEventId);
            userWaitingHolder.tvUser_Name = (TextView) row.findViewById(R.id.tvName);
            userWaitingHolder.tvEmail = (TextView) row.findViewById(R.id.tvEmail);
            userWaitingHolder.tvProcessed = (TextView) row.findViewById(R.id.tvProcessed);
            row.setTag(userWaitingHolder);
        }
        else{
            userWaitingHolder = (UserWaitingHolder) row.getTag();
        }

        UserWaiting userWaiting = (UserWaiting) getItem(position);
        int processed = userWaiting.getProcessed();
        String status;

        userWaitingHolder.tvEvent_Id.setText(userWaiting.getEvent_Id());
        userWaitingHolder.tvEvent_Id.setVisibility(View.INVISIBLE);
        userWaitingHolder.tvUser_Name.setText(userWaiting.getUser_Name());
        userWaitingHolder.tvEmail.setText(userWaiting.getEmail());
        userWaitingHolder.tvEmail.setVisibility(View.INVISIBLE);
        userWaitingHolder.tvProcessed.setText("Process");

        return row;
    }
    public static class UserWaitingHolder{
        TextView tvEvent_Id, tvUser_Name, tvEmail, tvProcessed;
    }
}
