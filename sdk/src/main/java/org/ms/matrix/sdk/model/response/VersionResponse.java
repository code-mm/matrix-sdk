package org.ms.matrix.sdk.model.response;

import java.util.List;

public class VersionResponse {


    /**
     * versions : ["r0.0.1","r0.1.0","r0.2.0","r0.3.0","r0.4.0","r0.5.0"]
     * unstable_features : {"m.id_access_token":true,"m.require_identity_server":false,"m.separate_add_and_bind":true,"org.matrix.label_based_filtering":true,"org.matrix.e2e_cross_signing":true,"org.matrix.msc2432":true}
     */

    private UnstableFeaturesBean unstable_features;
    private List<String> versions;

    public UnstableFeaturesBean getUnstable_features() {
        return unstable_features;
    }

    public void setUnstable_features(UnstableFeaturesBean unstable_features) {
        this.unstable_features = unstable_features;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public static class UnstableFeaturesBean {
        @com.google.gson.annotations.SerializedName("m.id_access_token")
        private boolean _$MId_access_token264; // FIXME check this code
        @com.google.gson.annotations.SerializedName("m.require_identity_server")
        private boolean _$MRequire_identity_server297; // FIXME check this code
        @com.google.gson.annotations.SerializedName("m.separate_add_and_bind")
        private boolean _$MSeparate_add_and_bind43; // FIXME check this code
        @com.google.gson.annotations.SerializedName("org.matrix.label_based_filtering")
        private boolean _$OrgMatrixLabel_based_filtering152; // FIXME check this code
        @com.google.gson.annotations.SerializedName("org.matrix.e2e_cross_signing")
        private boolean _$OrgMatrixE2e_cross_signing287; // FIXME check this code
        @com.google.gson.annotations.SerializedName("org.matrix.msc2432")
        private boolean _$OrgMatrixMsc243223; // FIXME check this code

        public boolean is_$MId_access_token264() {
            return _$MId_access_token264;
        }

        public void set_$MId_access_token264(boolean _$MId_access_token264) {
            this._$MId_access_token264 = _$MId_access_token264;
        }

        public boolean is_$MRequire_identity_server297() {
            return _$MRequire_identity_server297;
        }

        public void set_$MRequire_identity_server297(boolean _$MRequire_identity_server297) {
            this._$MRequire_identity_server297 = _$MRequire_identity_server297;
        }

        public boolean is_$MSeparate_add_and_bind43() {
            return _$MSeparate_add_and_bind43;
        }

        public void set_$MSeparate_add_and_bind43(boolean _$MSeparate_add_and_bind43) {
            this._$MSeparate_add_and_bind43 = _$MSeparate_add_and_bind43;
        }

        public boolean is_$OrgMatrixLabel_based_filtering152() {
            return _$OrgMatrixLabel_based_filtering152;
        }

        public void set_$OrgMatrixLabel_based_filtering152(boolean _$OrgMatrixLabel_based_filtering152) {
            this._$OrgMatrixLabel_based_filtering152 = _$OrgMatrixLabel_based_filtering152;
        }

        public boolean is_$OrgMatrixE2e_cross_signing287() {
            return _$OrgMatrixE2e_cross_signing287;
        }

        public void set_$OrgMatrixE2e_cross_signing287(boolean _$OrgMatrixE2e_cross_signing287) {
            this._$OrgMatrixE2e_cross_signing287 = _$OrgMatrixE2e_cross_signing287;
        }

        public boolean is_$OrgMatrixMsc243223() {
            return _$OrgMatrixMsc243223;
        }

        public void set_$OrgMatrixMsc243223(boolean _$OrgMatrixMsc243223) {
            this._$OrgMatrixMsc243223 = _$OrgMatrixMsc243223;
        }
    }
}
