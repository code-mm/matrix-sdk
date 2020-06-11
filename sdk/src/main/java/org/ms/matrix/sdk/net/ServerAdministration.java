package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAdministration {


    /**
     * 获取有关域的Matrix服务器发现信息。
     * @return
     */
    @GET(".well-known/matrix/client")
    Single<ResponseBody> _well_known_matrix_client();

    /**
     * 获取有关特定用户的信息。
     * @param userId
     * @return
     */
    @GET("_matrix/client/r0/admin/whois/{userId}")
    Single<ResponseBody> _matrix_client_r0_admin_whois_userId(@Query("userId") String userId);


    /**
     * 获取服务器支持的规范版本
     * @return
     */
    @GET("_matrix/client/versions")
    Single<ResponseBody> _matrix_client_versions();


}
