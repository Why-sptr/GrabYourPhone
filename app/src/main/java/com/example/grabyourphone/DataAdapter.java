package com.example.grabyourphone;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
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
    ArrayList<ColorSpace.Model> desclist;
    private Callback callback;

    public DataAdapter(ArrayList<ColorSpace.Model> arrayList){
        this.desclist = arrayList;
    }

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
        ModelData del = myMovieData.get(position);
        holder.textViewName.setText(del.getPhoneName());
        holder.textViewDate.setText(myMovieData.get(position).getBrand());
        Picasso.get().load(del.getPhoneImage()).resize(150,198).into(holder.movieImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailPhoneActivity.class);
                intent.putExtra("name", del.getPhoneName());
                intent.putExtra("image", del.getPhoneImage());
                intent.putExtra("detail", del.getSpecification());
                view.getContext().startActivity(intent);
            }
        });
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
