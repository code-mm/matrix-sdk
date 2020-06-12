package org.ms.matrix.sdk.model.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_location {


    private String msgtype;
    private String body;
    private String geo_uri;
    private Info info;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Info {

        private ThumbnailInfo thumbnail_info;
        private String thumbnail_ur;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ThumbnailInfo {

        private int w;
        private int h;
        private int size;
        private String mimetype;

    }

}
