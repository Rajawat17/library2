package com.example.library2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class libraryadapter  extends RecyclerView.Adapter<libraryadapter.ExampleViewHolder> {

    private ArrayList<person> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mdelete;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mdelete = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.tvname);
            mTextView2 = itemView.findViewById(R.id.tvbranch);
            mTextView3 = itemView.findViewById(R.id.tvcomponent
            );


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            mdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });
        }
    }

    public libraryadapter (ArrayList<person> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        person currentItem = mExampleList.get(position);

       // holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getBranch());
        holder.mTextView3.setText(currentItem.getComponent());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
