package org.ms.matrix.sdk.client;

public final class MatrixClient {

    private static final MatrixClient instance= new MatrixClient();

    public static MatrixClient getInstance() {
        return instance;
    }



}
