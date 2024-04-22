package com.moutamid.locationmonitorapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.locationmonitorapp.Activities.Authentication.LoginActivity;
import com.moutamid.locationmonitorapp.Adapter.AllUsersListAdapter;
import com.moutamid.locationmonitorapp.Model.UserModel;
import com.moutamid.locationmonitorapp.R;
import com.moutamid.locationmonitorapp.helper.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class AllUsersFragment extends Fragment {
    ArrayList<UserModel> list;
    RecyclerView recyclerView;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_users, container, false);
        mAuth = FirebaseAuth.getInstance();

        ImageView logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call your logout method
                logoutUser();
            }
        });
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        list = new ArrayList<>();
        getData();
return view;
    }
    private void getData() {
        Dialog lodingbar = new Dialog(getContext());
        lodingbar.setContentView(R.layout.loading);
        Objects.requireNonNull(lodingbar.getWindow()).setBackgroundDrawable(new ColorDrawable(UCharacter.JoiningType.TRANSPARENT));
        lodingbar.setCancelable(false);
        lodingbar.show();
        Constants.UserReference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            list.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                UserModel model = dataSnapshot.getValue(UserModel.class);
                                list.add(model);
                                Log.d("listSize", "ee : "+ model.getId());

                            }


                        }
                        lodingbar.dismiss();
                        AllUsersListAdapter adapter = new AllUsersListAdapter(getContext(), list);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
lodingbar.dismiss();                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void logoutUser() {
        mAuth.signOut(); // This will clear the user's authentication session
        // Redirect user to the login screen or any other desired screen
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish(); // Close the current activity
    }
}