package frontiere.com.outerspacemanager.outerspacemanager;

/**
 * Created by rfrontiere on 23/01/2018.
 */

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Manager {
    @POST("auth/create")
    Call<Token> user(@Body User user);

    @POST("auth/login")
    Call<Token> login(@Body User user);

    @GET("buildings/list")
    Call<Buildings> building(@Header("x-access-token") String token);

    @GET("users/get")
    Call<UserGet> getUser(@Header("x-access-token") String token);

    @GET("users/1/20")
    Call<Users> users(@Header("x-access-token") String token);

    @GET("ships")
    Call<Ships> ships(@Header("x-access-token") String token);

    @POST("buildings/create/{buildingId}")
    Call<Buildings> createbuilding(@Header("x-access-token") String token, @Path("buildingId") String buildingId);

    @POST("ships/create/{shipId}")
    Call<Ships> createship(@Header("x-access-token") String token, @Path("shipId") String shipId, @Body HashMap<String, String> amount );

    @GET("fleet/list")
    Call<Fleet> getFleet(@Header("x-access-token") String token);


}