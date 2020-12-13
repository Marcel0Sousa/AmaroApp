package co.marcelosousa.amaro.network;

import co.marcelosousa.amaro.models.ProdutosResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutosApiService {

    @GET("59b6a65a0f0000e90471257d")
    Call<ProdutosResponse> obterListaDados();
}
