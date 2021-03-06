package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import co.marcelosousa.amaro.models.Produtos;
import co.marcelosousa.amaro.models.ProdutosResponse;
import co.marcelosousa.amaro.models.Tamanhos;
import co.marcelosousa.amaro.network.ProdutosApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.marcelosousa.amaro.models.Assets.EXTRA_DISPONIVEL;
import static co.marcelosousa.amaro.models.Assets.EXTRA_PRODUTO;
import static co.marcelosousa.amaro.models.Assets.EXTRA_TAMANHOS;
import static co.marcelosousa.amaro.models.Assets.EXTRA_URL;
import static co.marcelosousa.amaro.models.Assets.EXTRA_VALOR_ATUAL;
import static co.marcelosousa.amaro.models.Assets.EXTRA_REGULAR;
import static co.marcelosousa.amaro.models.Assets.EXTRA_PARCELAR;
import static co.marcelosousa.amaro.models.Assets.TAG;

public class MainActivity extends AppCompatActivity implements ProdutosAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ProdutosAdapter mProdutosAdapter;
    private ArrayList<Produtos> mListaProdutos;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        mRecyclerView = findViewById(R.id.rv_container);
        mRecyclerView.setHasFixedSize(true);
        mProdutosAdapter = new ProdutosAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mProdutosAdapter);
        mProdutosAdapter.setOnItemClickListener(MainActivity.this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        // Retrofit implement
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.mocky.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obterDados();
    }

    //
    private void obterDados() {

        ProdutosApiService service = retrofit.create(ProdutosApiService.class);
        Call<ProdutosResponse> dadosResponseCall = service.obterListaDados();

        dadosResponseCall.enqueue(new Callback<ProdutosResponse>() {
            @Override
            public void onResponse(Call<ProdutosResponse> call, Response<ProdutosResponse> response) {


                if (!response.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), getString(R.string.onResponse_erro), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "onResponse: " + response.errorBody());

                } else {

                    ProdutosResponse produtosResponse = response.body();
                    assert produtosResponse != null;
                    mListaProdutos = produtosResponse.getProducts();
                    mProdutosAdapter.addListaProduto(mListaProdutos);

                    /*for (int i = 0; i < mListaProdutos.size(); i++) {
                        Produtos produtos = mListaProdutos.get(i);
                        Log.i(TAG, "Produtos " + produtos.getName());

                        for (Tamanhos t : produtos.getSizes()) {
                            Log.i(TAG, "Tamanhos " + t.getSize() + t.isAvailable());
                        }

                        Log.i(TAG, "------------------");
                    }*/

                }
            }

            @Override
            public void onFailure(Call<ProdutosResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getString(R.string.onFailure), Toast.LENGTH_LONG).show();
                Log.i(TAG, "onFailure: " + t.getMessage());

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, DetalhesActivity.class);
        Produtos clickedItem = mListaProdutos.get(position);
        intent.putExtra("produto", clickedItem);


        startActivity(intent);

    }
}