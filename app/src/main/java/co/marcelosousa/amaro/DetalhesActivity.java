package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import co.marcelosousa.amaro.models.Produtos;
import co.marcelosousa.amaro.models.Tamanhos;

import static co.marcelosousa.amaro.models.Assets.EXTRA_COLOR;
import static co.marcelosousa.amaro.models.Assets.TAG;

public class DetalhesActivity extends AppCompatActivity {

    private Produtos produto;

    private ImageView mImageView;
    private TextView mNomeProduto, mValorProduto, mValorRegularProduto, mParcelarProduto,
            mTamanhoPP, mTamanhoP, mTamanhoM, mTamanhoG, mTamanhoGG, mTamanhoU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        this.produto = getIntent().getParcelableExtra("produto");

        mImageView = findViewById(R.id.img_detalhes);
        mNomeProduto = findViewById(R.id.nome_detalhes);
        mValorProduto = findViewById(R.id.valor_detalhes);
        mValorRegularProduto = findViewById(R.id.valor_reg_detalhes);
        mParcelarProduto = findViewById(R.id.parcelar_produc_detalhes);
        mTamanhoPP = findViewById(R.id.tamanhoPP_detalhes);
        mTamanhoP = findViewById(R.id.tamanhoP_detalhes);
        mTamanhoM = findViewById(R.id.tamanhoM_detalhes);
        mTamanhoG = findViewById(R.id.tamanhoG_detalhes);
        mTamanhoGG = findViewById(R.id.tamanhoGG_detalhes);
        mTamanhoU = findViewById(R.id.tamanhoU_detalhes);


        if (produto.getImage().isEmpty()) {
            Glide.with(this)
                    .load(produto.getImage())
                    .error(R.drawable.ic_no_photos)
                    .centerInside()
                    .into(mImageView);
            mImageView.getLayoutParams().width = 400;
            mImageView.getLayoutParams().height = 400;
        }

        Glide.with(this)
                .load(produto.getImage())
                .error(R.drawable.ic_no_photos)
                .centerInside()
                .into(mImageView);

        mNomeProduto.setText(produto.getName());
        mParcelarProduto.setText(getString(R.string.parcelar) + produto.getInstallments());

        if (produto.getRegular_price().equals(produto.getActual_price())) {

            mValorRegularProduto.setText(produto.getActual_price());
            mValorRegularProduto.setTextColor(Color.parseColor("#009688"));
            mValorRegularProduto.setTextSize(18);
            mValorProduto.setVisibility(View.GONE);

        } else {
            mValorProduto.setText(produto.getActual_price());
            mValorRegularProduto.setText(produto.getRegular_price());
            mValorRegularProduto.setPaintFlags(mValorRegularProduto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        for (Tamanhos tamanhos : produto.getSizes()) {

            switch (tamanhos.getSize()) {
                case "PP":
                case "36":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoPP.setText(tamanhos.getSize());
                        mTamanhoPP.setTextColor(Color.parseColor(EXTRA_COLOR));
                        mTamanhoU.setVisibility(View.GONE);
                    } else {
                        mTamanhoPP.setText(tamanhos.getSize());
                        mTamanhoU.setVisibility(View.GONE);
                    }
                    break;
                case "P":
                case "38":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoP.setText(tamanhos.getSize());
                        mTamanhoP.setTextColor(Color.parseColor(EXTRA_COLOR));
                        mTamanhoU.setVisibility(View.GONE);
                    } else {
                        mTamanhoP.setText(tamanhos.getSize());
                        mTamanhoU.setVisibility(View.GONE);
                    }
                    break;
                case "M":
                case "40":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoM.setText(tamanhos.getSize());
                        mTamanhoM.setTextColor(Color.parseColor(EXTRA_COLOR));
                        mTamanhoU.setVisibility(View.GONE);
                    } else {
                        mTamanhoM.setText(tamanhos.getSize());
                        mTamanhoU.setVisibility(View.GONE);
                    }
                case "G":
                case "42":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoG.setText(tamanhos.getSize());
                        mTamanhoG.setTextColor(Color.parseColor(EXTRA_COLOR));
                        mTamanhoU.setVisibility(View.GONE);
                    } else {
                        mTamanhoG.setText(tamanhos.getSize());
                        mTamanhoU.setVisibility(View.GONE);
                    }
                    break;
                case "GG":
                case "44":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoGG.setText(tamanhos.getSize());
                        mTamanhoGG.setTextColor(Color.parseColor(EXTRA_COLOR));
                        mTamanhoU.setVisibility(View.GONE);
                    } else {
                        mTamanhoGG.setText(tamanhos.getSize());
                        mTamanhoU.setVisibility(View.GONE);
                    }
                    break;
                case "U":
                    if (!tamanhos.isAvailable()) {
                        mTamanhoU.setText(tamanhos.getSize());
                        mTamanhoU.setTextColor(Color.parseColor(EXTRA_COLOR));
                    } else {
                        mTamanhoU.setText(tamanhos.getSize());
                        mTamanhoPP.setVisibility(View.GONE);
                        mTamanhoP.setVisibility(View.GONE);
                        mTamanhoM.setVisibility(View.GONE);
                        mTamanhoG.setVisibility(View.GONE);
                        mTamanhoGG.setVisibility(View.GONE);
                    }
                    break;

            }
        }

    }
}