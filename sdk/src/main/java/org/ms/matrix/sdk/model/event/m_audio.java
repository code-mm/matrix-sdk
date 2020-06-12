package org.ms.matrix.sdk.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_audio extends BaseContent {

    private String body;
    private String msgtype;
    private String url;
    private Info info;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor

    public static class Info {
        private int size;
        private int duration;
        private String mimetype;
    }
}