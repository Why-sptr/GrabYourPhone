package com.example.grabyourphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ModelData[] myMovieData;
    Context context;

    public DataAdapter(ModelData[] myMovieData,AppleActivity activity) {
        this.myMovieData = myMovieData;
        this.context = activity;
    }

    public DataAdapter(ModelData[] myMovieData, SamsungActivity samsungActivity) {
        this.myMovieData = myMovieData;
        this.context = samsungActivity;
    }

    public DataAdapter(ModelData[] myMovieData, RealmeActivity realmeActivity) {
        this.myMovieData = myMovieData;
        this.context = realmeActivity;
    }

    public DataAdapter(ModelData[] myMovieData, XiaomiActivity xiaomiActivity) {
        this.myMovieData = myMovieData;
        this.context = xiaomiActivity;
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
        final ModelData myMovieDataList = myMovieData[position];
        holder.textViewName.setText(myMovieDataList.getMovieName());
        holder.textViewDate.setText(myMovieDataList.getMovieDate());
        Picasso.get()
                .load(myMovieDataList.getMovieImage())
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myMovieDataList.getMovieName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myMovieData.length;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView textViewName;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewDate = itemView.findViewById(R.id.textdate);

        }
    }
}
