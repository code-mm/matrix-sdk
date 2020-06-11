package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Capabilities {


    /**
     * 获取有关服务器功能的信息
     * @return
     */
    @GET("_matrix/client/r0/capabilities")
    Single<ResponseBody> _matrix_client_r0_capabilities();


}
