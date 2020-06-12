package org.ms.matrix.sdk.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_file extends BaseContent {


    private String body;
    private String msgtype;
    private String filename;
    private String url;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Info {
        private int size;
        private String mimetype;
    }
}
