package com.moutamid.locationmonitorapp.helper;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.locationmonitorapp.Model.UserModel;
import com.moutamid.locationmonitorapp.R;

public class MapDialogClass extends Dialog {

    public Activity c;
    TextView name_person;
    Button welcome;
    String id;

    public MapDialogClass(Activity a, String id) {
        super(a);
        // TODO Auto-generated constructor stub
        this.id = id;
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.map_dailogue);
        name_person = findViewById(R.id.name_person);
        welcome = findViewById(R.id.welcome);
        Constants.UserReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("name")) {
                    UserModel userNew = snapshot.getValue(UserModel.class);
                        if (userNew.getName() != null) {
                            name_person.setText(userNew.getName());
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


}