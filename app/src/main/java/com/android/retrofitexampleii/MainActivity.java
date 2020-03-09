package com.android.retrofitexampleii;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.retrofitexampleii.Adapter.CommentsRecyclerViewAdapter;
import com.android.retrofitexampleii.DataModel.CommentsModel;
import com.android.retrofitexampleii.ViewModel.CommentsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RelativeLayout prgressLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prgressLayout=findViewById(R.id.relativeLayoutProgressBar);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.hasFixedSize();
        getLoadingProgress();
        getData();
    }

    private void getLoadingProgress() {
        CommentsViewModel model = ViewModelProviders.of(this).get(CommentsViewModel.class);
        model.init();
        model.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    Log.wtf("info", "loading finished");
                    prgressLayout.setVisibility(View.GONE);
                } else {
                    Log.wtf("info", "loading data");
                    prgressLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void getData(){
        CommentsViewModel model = ViewModelProviders.of(this).get(CommentsViewModel.class);
        model.init();
        model.getData().observe(this, new Observer<List<CommentsModel>>() {
            @Override
            public void onChanged(@Nullable List<CommentsModel> commentList) {
               RecyclerView.Adapter adapter = new CommentsRecyclerViewAdapter(getApplicationContext(),commentList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
