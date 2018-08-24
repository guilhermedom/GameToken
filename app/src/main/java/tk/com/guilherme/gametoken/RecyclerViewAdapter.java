package tk.com.guilherme.gametoken;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TheViewHolder> {

    private Context ctx;
    private List<Game> gameList;

    public RecyclerViewAdapter(Context ctx, List<Game> gameList) {
        this.ctx = ctx;
        this.gameList = gameList;
    }

    @Override
    public TheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        view = layoutInflater.inflate(R.layout.cardview_item, parent, false);
        return new TheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TheViewHolder holder, final int position) {

        holder.textViewGameName.setText(gameList.get(position).getName());
        //holder.imageViewGameImage.setImageResource(gameList.get(position).getImage());
        //holder.imageViewGameImage.drawable
        //holder.imageViewGameImage.setImageDrawable(getImageFromUrl("https://at-cdn-s01.audiotool.com/2015/05/26/documents/VXL58qZHo7ydkCHTJSmRY2NOZVUgU/0/cover256x256-94ebc094d4ba4ede99de745fd44725bc.jpg"));
        new ImageDownloader(holder.imageViewGameImage).execute("https://static.wixstatic.com/media/25fb95_1c2087ab878140ad95e52104ce00e8d7~mv2.jpg/v1/fill/w_500,h_500,al_c,q_90/file.jpg");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx, GameDetailActivity.class);
                intent.putExtra("Nome", gameList.get(position).getName());
                intent.putExtra("Plataformas", gameList.get(position).getPlatform());
                intent.putExtra("Data", gameList.get(position).getReleaseDate());
                intent.putExtra("urlImage", gameList.get(position).getImageUrl());
                intent.putExtra("urlTrailer", gameList.get(position).getTrailerUrl());

                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class TheViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGameName;
        ImageView imageViewGameImage;
        CardView cardView;

        public TheViewHolder (View itemView) {
            super(itemView);

            textViewGameName = (TextView) itemView.findViewById(R.id.game_title_id);
            imageViewGameImage = (ImageView) itemView.findViewById(R.id.game_image_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
