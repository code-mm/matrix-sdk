package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Presence {


    /**
     * 获取此用户的状态
     *
     * @param userId
     * @return
     */
    @GET("_matrix/client/r0/presence/{userId}/status")
    Single<ResponseBody> get_matrix_client_r0_presence_userId_status(@Path("userId") String userId);

    /**
     * 更新此用户的状态
     *
     * @param userId
     * @return
     */
    @PUT("_matrix/client/r0/presence/{userId}/status")
    Single<ResponseBody> put_matrix_client_r0_presence_userId_status(@Path("userId") String userId);


}
