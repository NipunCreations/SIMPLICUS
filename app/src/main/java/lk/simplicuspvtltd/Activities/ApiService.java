package lk.simplicuspvtltd.Api;

import java.util.List;

import lk.simplicuspvtltd.Models.Item;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("items")
    Call<List<Item>> getItems();

    @POST("items")
    Call<Item> addItem(@Body Item item);

    @DELETE("items/{name}")
    Call<Void> deleteItem(@Path("name") String name);

    @PUT("items/{name}")
    Call<Item> updateItem(@Path("name") String name, @Body Item item);
}
