package com.moutamid.locationmonitorapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.moutamid.locationmonitorapp.Model.UserModel;
import com.moutamid.locationmonitorapp.R;

import java.util.ArrayList;

public class AllUsersListAdapter extends RecyclerView.Adapter<AllUsersListAdapter.ChatListVH> {
    Context context;
    ArrayList<UserModel> list;

    public AllUsersListAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChatListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatListVH(LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListVH holder, int position) {
        UserModel model = list.get(holder.getAdapterPosition());
        holder.name.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stash.put("userID", model.getId());
                Stash.put("userName", model.getName());

//                context.startActivity(new Intent(context, .class));

            }
        });
    }

        @Override
        public int getItemCount () {
            return list.size();
        }

        public class ChatListVH extends RecyclerView.ViewHolder {
            TextView name;

            public ChatListVH(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
            }
        }

    }
