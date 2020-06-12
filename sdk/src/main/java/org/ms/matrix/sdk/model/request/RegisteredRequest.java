package org.ms.matrix.sdk.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredRequest {


    /**
     * username : maohuawei3
     * password : maohuawei3
     * auth : {"type":"m.login.dummy"}
     */

    private String username;
    private String password;
    private AuthBean auth;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthBean getAuth() {
        return auth;
    }

    public void setAuth(AuthBean auth) {
        this.auth = auth;
    }

    public static class AuthBean {
        /**
         * type : m.login.dummy
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
