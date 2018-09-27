package com.example.nimra.dron;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class adminfragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mfirebaseDatabase;
    private TextView name;
    private TextView cnic;
    private TextView type;
    private TextView contactNumber;
    private DatabaseReference mDatabase;
    private String userID;
    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.admin, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("system");

        firebaseAuth = FirebaseAuth.getInstance();
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mfirebaseDatabase.getReference();


        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity().getApplication(), login.class));
        }

        FirebaseUser user  = firebaseAuth.getCurrentUser();
        assert user != null;
        userID = user.getUid();


        img= (ImageView)view.findViewById(R.id.img);
        name=(TextView)view.findViewById(R.id.name);
        cnic=(TextView)view.findViewById(R.id.cnic);
        contactNumber=(TextView)view.findViewById(R.id.phoneNumber);
        type=(TextView)view.findViewById(R.id.type);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    adminInfo uInfo = new adminInfo();
                    uInfo.setName(ds.child("employee").child(userID).getValue(adminInfo.class).getName());
                    uInfo.setCnic(ds.child("employee").child(userID).getValue(adminInfo.class).getCnic());
                    uInfo.setContactNumber(ds.child("employee").child(userID).getValue(adminInfo.class).getContactNumber());
                    uInfo.setType(ds.child("employee").child(userID).getValue(adminInfo.class).getType());
                    uInfo.setImg(ds.child("employee").child(userID).getValue(adminInfo.class).getImg());
                    name.setText("Name: " + uInfo.getName());
                    cnic.setText("Cnic: "+uInfo.getCnic());
                    contactNumber.setText("Phone: "+uInfo.getContactNumber());
                    type.setText("Type: "+uInfo.getType());
                    Picasso.with(getActivity()).load(uInfo.getImg().toString()).into(img);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("DronAID");

    }
}

