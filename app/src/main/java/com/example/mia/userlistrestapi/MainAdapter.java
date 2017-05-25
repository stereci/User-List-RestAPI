package com.example.mia.userlistrestapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> mDataset;

    public MainAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText(mDataset.get(position));
        holder.imageView.setImageResource(R.drawable.carrick);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle= (TextView) itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);

        }
    }
}
