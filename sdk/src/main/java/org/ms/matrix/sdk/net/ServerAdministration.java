package org.ms.matrix.sdk.net;

import org.ms.matrix.sdk.model.VersionResponse;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ServerAdministration {

    @GET("_matrix/client/versions")
    Single<ResponseBody> _matrix_client_versions();

}
