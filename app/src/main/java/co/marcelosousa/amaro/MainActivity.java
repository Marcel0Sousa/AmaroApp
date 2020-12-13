package co.marcelosousa.amaro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import co.marcelosousa.amaro.model.Produtos;
import co.marcelosousa.amaro.model.ProdutosResponse;
import co.marcelosousa.amaro.util.ProdutosApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Dados";

    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListaProdutosAdapter listaProdutosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //
        recyclerView = findViewById(R.id.rv_container);
        listaProdutosAdapter = new ListaProdutosAdapter(MainActivity.this);
        recyclerView.setAdapter(listaProdutosAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);


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

                    listaProdutosAdapter.addListaProduto(listProdutos);

                    /*for (int i = 0; i < listDados.size(); i++) {
                        Produtos produtos = listDados.get(i);
                        Log.i(TAG, "Resultado: " + produtos.getName());
                    }*/

                } else {

                    Log.e(TAG, "onResponse: " + response.errorBody());

                }

            }

            @Override
            public void onFailure(Call<ProdutosResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());

            }
        });

    }
}