package org.ms.matrix.sdk.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_image  extends BaseContent{

    private String body;
    private String msgtype;
    private String url;
    private Info info;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private int w;
        private int h;
        private int size;
        private String mimetype;
        private ThumbnailInfo thumbnail_info;
        private String thumbnail_url;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ThumbnailInfo {
        private int h;
        private String mimetype;
        private int size;
        private int w;
    }
}
