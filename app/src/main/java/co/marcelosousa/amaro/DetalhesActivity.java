package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static co.marcelosousa.amaro.models.Assets.EXTRA_PRODUTO;
import static co.marcelosousa.amaro.models.Assets.EXTRA_TAMANHOS_M;
import static co.marcelosousa.amaro.models.Assets.EXTRA_TAMANHOS_P;
import static co.marcelosousa.amaro.models.Assets.EXTRA_URL;
import static co.marcelosousa.amaro.models.Assets.EXTRA_VALOR_ATUAL;
import static co.marcelosousa.amaro.models.Assets.EXTRA_REGULAR;
import static co.marcelosousa.amaro.models.Assets.EXTRA_PARCELAR;

public class DetalhesActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView mNomeProduto, mValorProduto, mValorRegularProduto, mParcelarProduto,
            mTamanhoProdutoP, mTamanhoProdutoM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        String imagaUrl = intent.getStringExtra(EXTRA_URL);
        String nomeProduto = intent.getStringExtra(EXTRA_PRODUTO);
        String valorProduto = intent.getStringExtra(EXTRA_VALOR_ATUAL);
        String valorRegularProduto = intent.getStringExtra(EXTRA_REGULAR);
        String parcelarProduto = intent.getStringExtra(EXTRA_PARCELAR);
        String tamannhoProdutoP = intent.getStringExtra(EXTRA_TAMANHOS_P);
        String tamannhoProdutoM = intent.getStringExtra(EXTRA_TAMANHOS_M);

        imageView = findViewById(R.id.img_detalhes);
        mNomeProduto = findViewById(R.id.nome_detalhes);
        mValorProduto = findViewById(R.id.valor_detalhes);
        mValorRegularProduto = findViewById(R.id.valor_reg_detalhes);
        mParcelarProduto = findViewById(R.id.parcelar_produc_detalhes);
        mTamanhoProdutoP = findViewById(R.id.tamanhosP_detalhes);
        mTamanhoProdutoM = findViewById(R.id.tamanhosM_detalhes);


        if (imagaUrl.isEmpty()) {
            Glide.with(this)
                    .load(imagaUrl)
                    .error(R.drawable.ic_no_photos)
                    .centerInside()
                    .into(imageView);
            imageView.getLayoutParams().width = 400;
            imageView.getLayoutParams().height = 400;
        }

        Glide.with(this)
                .load(imagaUrl)
                .error(R.drawable.ic_no_photos)
                .centerInside()
                .into(imageView);

        mNomeProduto.setText(nomeProduto);
        mParcelarProduto.setText(getString(R.string.parcelar) + parcelarProduto);
        mTamanhoProdutoP.setText(tamannhoProdutoP);
        mTamanhoProdutoM.setText(tamannhoProdutoM);

        if (valorRegularProduto.equals(valorProduto)) {

            mValorRegularProduto.setText(valorRegularProduto);
            mValorRegularProduto.setTextColor(Color.parseColor("#009688"));
            mValorRegularProduto.setTextSize(18);
            mValorProduto.setVisibility(View.GONE);

        } else {
            mValorProduto.setText(valorProduto);
            mValorRegularProduto.setText(valorRegularProduto);
            mValorRegularProduto.setPaintFlags(mValorRegularProduto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }
}