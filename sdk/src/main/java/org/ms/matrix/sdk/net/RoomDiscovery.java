package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 房间发现
 */
public interface RoomDiscovery {


    /**
     * 列出服务器上的公共房间
     *
     * @param access_token
     * @return
     */
    @GET("_matrix/client/r0/publicRooms")
    Single<ResponseBody> get_matrix_client_r0_publicRooms(String access_token);

    @POST("_matrix/client/r0/publicRooms")
    Single<ResponseBody> matrix_client_r0_publicRooms(@Body RequestBody request);

}
