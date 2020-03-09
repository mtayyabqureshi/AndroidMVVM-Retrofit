package com.android.retrofitexampleii.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.retrofitexampleii.DataModel.CommentsModel;
import com.android.retrofitexampleii.R;

import java.util.ArrayList;
import java.util.List;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.CommentHolder> {

    private List<CommentsModel> comments = new ArrayList<>();
    Context context;


    public CommentsRecyclerViewAdapter(Context context, List<CommentsModel> comments) {
        this.comments = comments;
        this.context = context;

    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment, parent, false);

    //    itemview.getLayoutParams().width = (int) (getScreenWidth() / 5); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new CommentHolder(itemview);
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        Log.wtf("adapter_","list is : ");
        CommentsModel commentsModel = comments.get(position);
        holder.name.setText(commentsModel.getName());
        holder.email.setText(commentsModel.getEmail());

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(List<CommentsModel> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    public CommentsModel getCommentAt(int pos) {
        return comments.get(pos);
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView email;


        private CommentHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_name);
            email = itemView.findViewById(R.id.textview_email);
        }


    }
}
