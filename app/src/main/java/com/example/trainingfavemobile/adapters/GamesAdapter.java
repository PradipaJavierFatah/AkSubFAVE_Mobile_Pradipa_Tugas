package com.example.trainingfavemobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingfavemobile.R;
import com.example.trainingfavemobile.models.GamesResponseItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.viewHolder> {
    List<GamesResponseItem> gamesResponseItemList;
    Context context;

    public GamesAdapter(List<GamesResponseItem> gamesResponseItemList, Context context){
        this.gamesResponseItemList = gamesResponseItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_database, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        GamesResponseItem g = gamesResponseItemList.get(position);
        holder.tv_title.setText(g.getTitle());
        holder.tv_genre.setText(g.getGenre());
        holder.tv_platform.setText(g.getPlatform());
        holder.btn_detail.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(g.getGameUrl())));
        });
        holder.btn_play.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(g.getFreetogameProfileUrl())));
        });
        Picasso.with(context)
                .load(g.getThumbnail())
                .resize(250,250)
                .placeholder(R.drawable.img_1)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .centerCrop()
                .into(holder.iv_thumbnail);
    }

    @Override
    public int getItemCount() {
        return gamesResponseItemList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_genre, tv_platform;
        Button btn_detail, btn_play;
        ImageView iv_thumbnail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_gameTitle);
            tv_genre = itemView.findViewById(R.id.tv_gameGenre);
            tv_platform = itemView.findViewById(R.id.tv_gamePlatform);
            btn_detail = itemView.findViewById(R.id.btn_gameDetails);
            btn_play = itemView.findViewById(R.id.btn_gamePlay);
            iv_thumbnail = itemView.findViewById(R.id.img_gameThumbnail);
        }
    }
}
