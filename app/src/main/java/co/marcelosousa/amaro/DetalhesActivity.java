package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static co.marcelosousa.amaro.MainActivity.EXTRA_URL;
import static co.marcelosousa.amaro.MainActivity.EXTRA_PRODUTO;

public class DetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        /*String imagaUrl = intent.getStringExtra(EXTRA_URL);
        String nomeProduto = intent.getStringExtra(EXTRA_PRODUTO);*/

        ImageView imageView = findViewById(R.id.img_detalhes);
        TextView textView = findViewById(R.id.nome_detalhes);

        /*Glide.with(this)
                .load(imagaUrl)
                .fitCenter()
                .centerCrop()
                .into(imageView);

        textView.setText(nomeProduto);*/

    }
}