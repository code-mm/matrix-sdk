package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 客房会员
 */
public interface RoomMembership {

    /**
     * 获取用户当前房间
     *
     * @param access_token
     * @return
     */
    @GET("_matrix/client/r0/joined_rooms")
    Single<ResponseBody> _matrix_client_r0_joined_rooms(@Query("access_token") String access_token);


    /**
     * 禁止用户进入房间
     */




    /**
     * 禁止用户进入会议室
     */
    @POST("_matrix/client/r0/rooms/{roomId}/unban")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_unban(@Path("roomId") String roomId, @Query("access_token") String access_token);

}
