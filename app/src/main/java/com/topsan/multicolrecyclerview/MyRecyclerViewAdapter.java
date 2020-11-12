package com.topsan.multicolrecyclerview;

import android.content.Context;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class MyRecyclerViewAdapter
    extends BaseRvAdapter {

    MyRecyclerViewAdapter(Context context, Object data) {
        super(context, data);
        itemLayout = R.layout.recyclerview_item;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv = holder.itemView.findViewById(R.id.info_text);
        String text = (String)mData.get(position);
        tv.setText(text);
    }

}
