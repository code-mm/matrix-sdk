package org.ms.matrix.sdk.supper.inter.callback;

public interface MatrixCallBack<S, F> {

    void onSuccess(S s);

    void onFailure(F f);

}
