package co.marcelosousa.amaro.util;

import co.marcelosousa.amaro.model.ProdutosResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutosApiService {

    @GET("59b6a65a0f0000e90471257d")
    Call<ProdutosResponse> obterListaDados();
}
