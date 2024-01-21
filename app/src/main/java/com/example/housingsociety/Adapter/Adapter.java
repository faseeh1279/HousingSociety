package com.example.housingsociety.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsociety.Model.Model;
import com.example.housingsociety.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Model> modelList = new ArrayList<>();
    Context context;

    public Adapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View obj = layoutInflater.inflate(R.layout.homerecyclerview, null);

        return new ViewHolder(obj);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.txt1.setText(model.getEmail());
        holder.txt2.setText(model.getPassword());
        holder.img.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.email);
            txt2 = itemView.findViewById(R.id.password);
            img = itemView.findViewById(R.id.ImageView);
        }
    }
}
