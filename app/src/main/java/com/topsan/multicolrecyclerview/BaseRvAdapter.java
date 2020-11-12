package com.topsan.multicolrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BaseRvAdapter
        extends RecyclerView.Adapter<BaseRvAdapter.ViewHolder> {
    protected List<Object> mData;
    protected Context context;
    protected ItemClickListener mClickListener;
    protected int itemLayout;
    protected RecyclerView recyclerView = null;
    protected int numberOfColums = 1;

    BaseRvAdapter(Context context, Object data) {
        this.context = context;
        this.mData = (List<Object>)data;
    }

    public void setLayoutManager(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.numberOfColums = 1;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this);
    }

    public void setLayoutManager(RecyclerView recyclerView, int numberOfColums) {
        this.recyclerView = recyclerView;
        this.numberOfColums = numberOfColums;
        recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColums));
        recyclerView.setAdapter(this);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    Object getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(BaseRvAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
