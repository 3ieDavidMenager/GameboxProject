package fr.iiie.android.gameboxproject.data.network.rest.service;

import java.util.List;

import fr.iiie.android.gameboxproject.data.model.MyCustomModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService
{
    @GET("posts")
    Call<List<MyCustomModel>> getWsResponse();
}
