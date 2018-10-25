package com.evans.offlineapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {
    public TextView TxtInfo;

    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);

        TxtInfo=view.findViewById(R.id.txt_Info);
        List<User> users = Homepage.myAppDatabase.myDao().getUsers();
        String txtInfo="";

        //foreach(users as user)
        for(User user : users){
            int id=user.getId();
            String email=user.getEmail();
            String password=user.getPassword();
            txtInfo=txtInfo+"\n\n\n\nID: "+id+"\n Email: "+email+"\n Password: "+password;
        }
        TxtInfo.setText(txtInfo);


        return  view;
    }

}
