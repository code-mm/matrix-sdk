package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionManagement {

    @GET("_matrix/client/r0/login")
    Single<ResponseBody> get_matrix_client_r0_login();

    @POST("_matrix/client/r0/login")
    Single<ResponseBody> _matrix_client_r0_login(@Body RequestBody request);

    @POST("_matrix/client/r0/logout")
    Single<ResponseBody> _matrix_client_r0_logout();

    @POST("_matrix/client/r0/logout/all")
    Single<ResponseBody> _matrixClientR0LogoutAll();

}
