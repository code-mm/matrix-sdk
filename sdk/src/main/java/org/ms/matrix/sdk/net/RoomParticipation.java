package org.ms.matrix.sdk.net;


import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 房间参与
 */
public interface RoomParticipation {


    /**
     * 获取此房间的 m.room.member 事件
     *
     * @param roomId
     * @param access_token
     * @return
     */
    @GET("_matrix/client/r0/rooms/{roomId}/members")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_members(@Path("roomId") String roomId, @Query("access_token") String access_token);

    /**
     * 获取此房间的事件列表
     *
     * @param roomId
     * @param access_token
     * @return
     */
    @GET("_matrix/client/r0/rooms/{roomId}/messages")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_messages(@Path("roomId") String roomId, @Query("access_token") String access_token);

    /**
     * 将消息事件发送到给定的房间。
     */

    @PUT("_matrix/client/r0/rooms/{roomId}/send/{eventType}/{txnId}")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_send_eventType_txnId_(
            @Path("roomId") String roomId,
            @Path("eventType") String eventType,
            @Path("txnId") String txnId,
            @Query("access_token") String access_token,
            @Body RequestBody requestBody
    );


    @GET("_matrix/client/r0/sync")
    Single<ResponseBody> _matrix_client_v0_sync(@Query("since") String since, @Query("access_token") String access_token);


}
