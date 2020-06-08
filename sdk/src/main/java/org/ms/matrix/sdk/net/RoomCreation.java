package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 创建房间
 */
public interface RoomCreation {


    /**
     * 创建一个新房间
     *
     * @param requestBody
     * @return
     */
    @POST("_matrix/client/r0/createRoom")
    Single<ResponseBody> _matrix_client_r0_createRoom(@Body RequestBody requestBody);


}
