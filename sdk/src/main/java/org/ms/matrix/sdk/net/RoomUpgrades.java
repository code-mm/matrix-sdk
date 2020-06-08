package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 房间升级
 */
public interface RoomUpgrades {


    /**
     * 将房间升级到新的房间版本。
     *
     * @param roomId
     * @return
     */
    @POST("_matrix/client/r0/rooms/{roomId}/upgrade")
    Single<ResponseBody> _matrix_client_r0_rooms_roomId_upgrade(@Path("roomId") String roomId);


}

