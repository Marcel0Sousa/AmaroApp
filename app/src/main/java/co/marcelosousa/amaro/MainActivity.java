package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import co.marcelosousa.amaro.models.Produtos;
import co.marcelosousa.amaro.models.ProdutosResponse;
import co.marcelosousa.amaro.network.ProdutosApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ProdutosAdapter.OnItemClickListener {

    public static final String TAG = "Dados";

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_PRODUTO = "nomeProduto";

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


                if (response.isSuccessful()) {

                    ProdutosResponse produtosResponse = response.body();
                    ArrayList<Produtos> listProdutos = produtosResponse.getProducts();
                    mProdutosAdapter.addListaProduto(listProdutos);

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.onResponse_erro), Toast.LENGTH_LONG).show();
                    Log.e(TAG, "onResponse: " + response.errorBody());

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
        /*Produtos clickedItem = mListaProdutos.get(position);
        intent.putExtra(EXTRA_URL, clickedItem.getImage());
        intent.putExtra(EXTRA_PRODUTO, clickedItem.getName());*/
        startActivity(intent);

    }
}