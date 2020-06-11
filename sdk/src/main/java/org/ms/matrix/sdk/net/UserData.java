package org.ms.matrix.sdk.net;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserData {


    /**
     * 获取用户的第三方标识符的列表。
     *
     * @param access_token
     * @return
     */
    @GET("_matrix/client/r0/account/3pid")
    Single<ResponseBody> _matrix_client_r0_account_3pid(@Query("access_token") String access_token);


    /**
     * 通过身份服务将3PID绑定到用户的帐户。
     *
     * @param access_token
     * @return
     */
    @POST("_matrix/client/r0/account/3pid/bind")
    Single<ResponseBody> _matrix_client_r0_account_3pid_bind(@Query("access_token") String access_token);


    /**
     * 从用户帐户中删除第三方标识符
     *
     * @param access_token
     * @return
     */
    @POST("_matrix/client/r0/account/3pid/delete")
    Single<ResponseBody> _matrix_client_r0_account_3pid_delete(@Query("access_token") String access_token);


    /**
     * 从身份服务器中删除用户的第三方标识符
     */

    @POST("_matrix/client/r0/account/3pid/unbind")
    Single<ResponseBody> _matrix_client_r0_account_3pid_unbind();


    /**
     * 停用用户的帐户
     */
    @POST("_matrix/client/r0/account/deactivate")
    Single<ResponseBody> _matrix_client_r0_account_deactivate();


    /**
     * 改用户密码。
     */

    @POST("_matrix/client/r0/account/password")
    Single<ResponseBody> _matrix_client_r0_account_password();


    /**
     * 获取有关访问令牌所有者的信息
     */
    @POST("_matrix/client/r0/account/whoami")
    Single<ResponseBody> _matrix_client_r0_account_whoami();


    /**
     * 获取此用户的个人资料信息。
     *
     * @return
     */
    @GET("_matrix/client/r0/profile/{userId}")
    Single<ResponseBody> _matrix_client_r0_profile_userId();


    /**
     * 获取用户的头像URL。
     */

    @GET("_matrix/client/r0/profile/{userId}/avatar_url")
    Single<ResponseBody> get_matrix_client_r0_profile_userId_avatar_url();

    /**
     * 设置用户的头像URL
     */

    @PUT("_matrix/client/r0/profile/{userId}/avatar_url")
    Single<ResponseBody> put_matrix_client_r0_profile_userId_avatar_url();

    /**
     * 获取用户的显示名称
     */

    @GET("_matrix/client/r0/profile/{userId}/displayname")

    /**
     *设置用户的显示名称
     */

    @PUT("_matrix/client/r0/profile/{userId}/displayname")


    /**
     * 在此家庭服务器上注册一个帐户。
     *
     * @param request
     * @return
     */
    @POST("_matrix/client/r0/register")
    Single<ResponseBody> _matrix_client_r0_register(@Body RequestBody request);

    /**
     * 检查服务器上是否有用户名。
     */
    @GET("_matrix/client/r0/register/available")
    Single<ResponseBody> _matrix_client_r0_register_available();

    /**
     * 为用户获取一些account_data
     */

    @GET("_matrix/client/r0/user/{userId}/account_data/{type}")
    Single<ResponseBody> get_matrix_client_r0_user_userId_account_data_type();

    /**
     * 为用户设置一些account_data
     */

    @PUT("_matrix/client/r0/user/{userId}/account_data/{type}")
    Single<ResponseBody> _matrix_client_r0_user_userId_account_data_type();


    /**
     * 为用户获取一些account_data
     */
    @GET("_matrix/client/r0/user/{userId}/rooms/{roomId}/account_data/{type}")
    Single<ResponseBody> get_matrix_client_r0_user_userId_rooms_roomId_account_data_type();

    /**
     * 为用户设置一些account_data。
     */
    @PUT("_matrix/client/r0/user/{userId}/rooms/{roomId}/account_data/{type}")
    Single<ResponseBody> _matrix_client_r0_user_userId_rooms_roomId_account_data_type();

    /**
     * 列出房间的标签
     */

    @GET("_matrix/client/r0/user/{userId}/rooms/{roomId}/tags")
    Single<ResponseBody> _matrix_client_r0_user_userId_rooms_roomId_tags();

    /**
     *
     */
    @DELETE("_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}")
    Single<ResponseBody> delete_matrix_client_r0_user_userId_rooms_roomId_tags_tag();

    /**
     * 向房间添加标签。
     */
    @PUT("_matrix/client/r0/user/{userId}/rooms/{roomId}/tags/{tag}")
    Single<ResponseBody> _matrix_client_r0_user_userId_rooms_roomId_tags_tag();

    /**
     * 搜索用户目录
     */
    @POST("_matrix/client/r0/user_directory/search")
    Single<ResponseBody> _matrix_client_r0_user_directory_search();


}
