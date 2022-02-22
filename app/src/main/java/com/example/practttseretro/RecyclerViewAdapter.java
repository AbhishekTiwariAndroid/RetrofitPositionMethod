package com.example.practttseretro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    Context context;
    List<Model> modelList;
    RecyclerViewAdapter(Context context, List<Model> models){
        this.context = context;
        this.modelList = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(modelList.get(position).name);
        holder.username.setText(modelList.get(position).username);
        holder.email.setText(modelList.get(position).email);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,username,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);


        }
    }
}
