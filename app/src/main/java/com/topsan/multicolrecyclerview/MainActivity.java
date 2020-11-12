package com.topsan.multicolrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.topsan.multicolrecyclerview.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BaseRvAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        List<String> data = new ArrayList<>();
        for(int i=1; i <= 100; i++) {
            String text = "" + i;
            data.add(text);
        }

        RecyclerView recyclerView = findViewById(R.id.rvNumbers);
        adapter = new MyRecyclerViewAdapter(this, data);

        //adapter.setLayoutManager(recyclerView, 4);
        adapter.setLayoutManager(recyclerView);

        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        //binding.tv.setText(position + " - " + adapter.getItem(position).toString());
    }

    public void onBtnUp(View v) {
        binding.rvNumbers.smoothScrollToPosition(0);
    }

    public void onBtnDown(View v) {
        binding.rvNumbers.smoothScrollToPosition(17);
    }

    public void onBtnHigh(View v) {
        scrollToPosition(0);
    }

    public void onBtnLow(View v) {
        scrollToPosition(17);
    }

    public void scrollToPosition(int position) {
        binding.rvNumbers.setLayoutManager(new SpeedyLinearLayoutManager(this,
                SpeedyLinearLayoutManager.VERTICAL, false));
        binding.rvNumbers.smoothScrollToPosition(position);
    }

    public class SpeedyLinearLayoutManager extends LinearLayoutManager {

        private static final float MILLISECONDS_PER_INCH = 5f; //default is 25f (bigger = slower)

        public SpeedyLinearLayoutManager(Context context) {
            super(context);
        }

        public SpeedyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public SpeedyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {

            final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

                @Override
                public PointF computeScrollVectorForPosition(int targetPosition) {
                    return super.computeScrollVectorForPosition(targetPosition);
                }

                @Override
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                }
            };

            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }
    }

}