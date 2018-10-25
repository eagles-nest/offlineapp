package com.evans.offlineapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {
    private EditText txtEmail,txtPassword,txtId;
    private Button btnAdd;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_add_user, container, false);

        txtEmail=view.findViewById(R.id.txt_email);
        txtPassword=view.findViewById(R.id.txt_password);
        txtId=view.findViewById(R.id.txt_id);
        btnAdd=view.findViewById(R.id.btn_create);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(txtId.getText().toString().trim());
                String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();

                User user = new User();
                user.setId(id);
                user.setEmail(email);
                user.setPassword(password);

                Homepage.myAppDatabase.myDao().addUser(user);
                Toast.makeText(getActivity(), "User added successfully", Toast.LENGTH_LONG).show();

                txtId.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
            }
        });
        return view;
    }

}
