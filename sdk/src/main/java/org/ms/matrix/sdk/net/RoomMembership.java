package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 客房会员
 */
public interface RoomMembership {


    @GET("_matrix/client/r0/joined_rooms")
    Single<ResponseBody> _matrix_client_r0_joined_rooms(@Query("access_token") String access_token);

}
