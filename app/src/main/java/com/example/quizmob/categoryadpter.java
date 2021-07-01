package com.example.quizmob;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class categoryadpter extends RecyclerView.Adapter<categoryadpter.categoryViewHolder> {
    Context context;
    ArrayList<Categorymodel>categorymodels;
    public categoryadpter(Context context, ArrayList<Categorymodel>categorymodels){
        this.context=context;
        this.categorymodels=categorymodels;

    }
    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_category, null);

        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryadpter.categoryViewHolder holder, int position) {
        Categorymodel model =categorymodels.get(position);

        holder.txtname.setText(model.getCatname());
        Glide.with(context).load(model.getCatimg()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,Quiz.class);
                intent.putExtra("catid",model.getCatid());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return categorymodels.size();
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtname;

        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.catimg);
            txtname=itemView.findViewById(R.id.category);
        }
    }

}
