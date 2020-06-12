package org.ms.matrix.sdk.model.event;

import org.ms.module.supper.client.Modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Unsigned {
    private int age;


    public String toJson() {
        return Modules.getUtilsModule().getGsonUtils().toJson(this);
    }


}
