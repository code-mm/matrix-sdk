package org.ms.sdk.matrix.client;

//import org.ms.sdk.matrix.Matrix;
//import org.ms.sdk.matrix.MatrixCallBack;
//import org.ms.sdk.matrix.inter.IMatrix;
//
//public class MatrixClient {
//
//
//    private MatrixClient() {
//        iMatrix= new Matrix();
//    }
//
//    public IMatrix matrix() {
//        return iMatrix;
//    }
//
//    IMatrix iMatrix;
//
//
//    public static class Builder {
//
//        private static Builder builder = new Builder();;
//        private MatrixClient client;
//
//        private Builder() {
//            client = new MatrixClient();
//        }
//
//        public static Builder builder() {
//            return builder;
//        }
//
//        public Builder setHomeServer(String homeServer) {
//            client.matrix().setHomeServer(homeServer);
//            return this;
//        }
//
//        public Builder setLog(boolean flag) {
//            return this;
//        }
//
//
//        public MatrixClient build() {
//            return client;
//
//        }
//    }
//}
