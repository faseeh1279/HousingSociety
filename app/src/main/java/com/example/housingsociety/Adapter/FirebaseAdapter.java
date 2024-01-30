package com.example.housingsociety.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingsociety.Model.FirebaseModel;
import com.example.housingsociety.Model.SelectListener;
import com.example.housingsociety.R;

import java.util.List;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ViewHolder> {
    Context context;
    List<FirebaseModel> modelList;

    SelectListener selectListener;


    public FirebaseAdapter(Context context, List<FirebaseModel> modelList, SelectListener selectListener) {
        this.context = context;
        this.modelList = modelList;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public FirebaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View obj = layoutInflater.inflate(R.layout.homerecyclerview, null);

        return new ViewHolder(obj);
      }

    @Override
    public void onBindViewHolder(@NonNull FirebaseAdapter.ViewHolder holder, int position) {
        FirebaseModel model = modelList.get(position);
        holder.txt1.setText(model.getName());
        holder.txt2.setText(model.getPhone());
        holder.txt3.setText(model.getFees());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.onItemClicked(modelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.name);
            txt2 = itemView.findViewById(R.id.phone);
            txt3 = itemView.findViewById(R.id.fees);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
