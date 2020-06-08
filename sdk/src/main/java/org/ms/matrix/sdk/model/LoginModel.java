package org.ms.matrix.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 登录请求参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginModel extends IModel {

    private String user;
    private String password;
    private String type;

    private String initial_device_display_name;
    private String device_id;
    private String token;
    private String address;
    private String medium;
    private String identifier;

}
