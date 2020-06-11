package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RoomDirectory {

    @DELETE("_matrix/client/r0/directory/room/{roomAlias}")
    Single<ResponseBody> delete_matrix_client_r0_directory_room_roomAlias(@Path("roomAlias") String roomAlias, @Query("access_token") String access_token);

    @GET("_matrix/client/r0/directory/room/{roomAlias}")
    Single<ResponseBody> get_matrix_client_r0_directory_room_roomAlias(@Path("roomAlias") String roomAlias, @Query("access_token") String access_token);

    @PUT("_matrix/client/r0/directory/room/{roomAlias}")
    Single<ResponseBody> _matrix_client_r0_directory_room_roomAlias(@Path("roomAlias") String roomAlias, @Query("access_token") String access_token);

    @GET("_matrix/client/r0/rooms/{roomId}/aliases")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_aliases(@Path("roomId") String roomId, @Query("access_token") String access_token);

}
