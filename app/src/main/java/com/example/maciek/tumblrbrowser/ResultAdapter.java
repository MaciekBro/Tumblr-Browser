package com.example.maciek.tumblrbrowser;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by Maciek on 2017-03-10.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {

    private List<Post> postList = Collections.emptyList();
    private Post post;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutPostsLists = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_post, parent, false);
        return new MyViewHolder(layoutPostsLists);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        post = postList.get(position);
        Glide.with(holder.postImage.getContext()).load("https://lh3.googleusercontent.com/uMUf2PLg4wcuyzXox8sScHWeUzcuvI7jMfT1GjOXp2_GDol5bBYEL1988xGXVzu37OM=w300");
        holder.postName.setText(post.getTumblelog().getName());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView postImage;
        TextView postName;

        public MyViewHolder(View itemView) {
            super(itemView);
            postImage = (ImageView) itemView.findViewById(R.id.image_post);
            postName = (TextView) itemView.findViewById(R.id.name_post);
        }

    }
}
