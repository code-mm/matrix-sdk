package org.ms.matrix.sdk.net;

import org.ms.matrix.sdk.model.RegisteredRequest;
import org.ms.matrix.sdk.model.RegisteredResponse;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserData {

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @POST("_matrix/client/r0/register")
    Single<ResponseBody> _matrix_client_r0_register(@Body RequestBody  request);
}
