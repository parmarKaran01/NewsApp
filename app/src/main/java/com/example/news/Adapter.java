package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Models.Articles;
import com.example.news.Models.Headlines;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;
    private NewsItemClicked listener;

    public Adapter(Context context, List<Articles> articles, Callback<Headlines> listener) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent , false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onItemClicked(articles.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articles.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvData.setText(a.getPublishedAt());

        String imageUrl = a.getUrlToImage();
        String url = a.getUrl();

        Picasso.with(context).load(imageUrl).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvSource, tvData;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvData = itemView.findViewById(R.id.tvData);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
            
        }
    }
}

interface NewsItemClicked{
    public void onItemClicked(Articles articles);

}
