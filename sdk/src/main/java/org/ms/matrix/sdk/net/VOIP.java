package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VOIP {

    @GET("_matrix/client/r0/voip/turnServer")
    Single<ResponseBody> _matrix_client_r0_voip_turnServer(@Query("access_token") String access_token);

}
