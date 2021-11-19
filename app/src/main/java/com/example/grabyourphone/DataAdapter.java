package com.example.grabyourphone;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<ModelData> myMovieData;
    private Callback callback;

    public interface Callback{
        void onClick(int position);
    }

    public DataAdapter(ArrayList<ModelData> listData, Callback callback) {
        this.myMovieData = listData;
        this.callback = callback;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        holder.textViewName.setText(myMovieData.get(position).getPhoneName());
        holder.textViewDate.setText(myMovieData.get(position).getBrand());
        Picasso.get()
                .load(myMovieData.get(position).getPhoneImage())
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return (myMovieData != null) ? myMovieData.size() : 0;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);
            cardView = itemView.findViewById(R.id.itemCardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });

        }
    }
}
