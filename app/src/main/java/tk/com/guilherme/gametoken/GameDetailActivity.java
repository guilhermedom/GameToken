package tk.com.guilherme.gametoken;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameDetailActivity extends AppCompatActivity {

    private TextView textViewName, textViewPlatforms, textViewDate;
    private ImageView imageView, imageViewTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        textViewName = (TextView) findViewById(R.id.game_detail_name_id);
        textViewPlatforms = (TextView) findViewById(R.id.game_detail_platform_id);
        textViewDate = (TextView) findViewById(R.id.game_detail_release_id);
        imageView = (ImageView) findViewById(R.id.game_detail_image_id);
        imageViewTrailer = (ImageView) findViewById(R.id.game_detail_trailer_button_id);

        Intent intent = getIntent();
        String gameName = intent.getExtras().getString("Name");
        String platforms = intent.getExtras().getString("Plataformas");
        String releaseDate = intent.getExtras().getString("Data");
        String urlImage = intent.getExtras().getString("urlImage");
        final String urlTrailer = intent.getExtras().getString("urlTrailer");

        textViewName.setText(gameName);
        textViewPlatforms.setText(platforms);
        textViewDate.setText(releaseDate);
        new ImageDownloader(imageView).execute(urlImage);


        imageViewTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlTrailer));
                startActivity(browserIntent);
            }
        });
    }
}
