package org.ms.matrix.sdk.model.params;

import org.ms.matrix.sdk.model.IModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 登录请求参数
 *
 * 此包 为对外使用请求参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LoginParam extends IParam {

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
