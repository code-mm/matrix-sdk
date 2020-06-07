package org.ms.matrix.sdk.model;

import java.util.List;

public class GetLoginResponse {


    private List<FlowsBean> flows;

    public List<FlowsBean> getFlows() {
        return flows;
    }

    public void setFlows(List<FlowsBean> flows) {
        this.flows = flows;
    }

    public static class FlowsBean {
        /**
         * type : m.login.password
         */

        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
