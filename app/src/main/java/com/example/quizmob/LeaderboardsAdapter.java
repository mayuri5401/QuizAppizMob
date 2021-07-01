package com.example.quizmob;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizmob.databinding.RowLeaderboardBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.LeaderboardViewHolder> {
    Context context;
    ArrayList<User> users;

    public LeaderboardsAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboard, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull LeaderboardsAdapter.LeaderboardViewHolder holder, int position) {
        User user =users.get(position);

        holder.binding.name.setText(user.getName());
        holder.binding.coins.setText(String.valueOf(user.getCoins()));
        holder.binding.index.setText(String.format("#%d", position+1));


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    protected class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        RowLeaderboardBinding binding;
        public LeaderboardViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding=RowLeaderboardBinding.bind(itemView);
        }
    }
}

