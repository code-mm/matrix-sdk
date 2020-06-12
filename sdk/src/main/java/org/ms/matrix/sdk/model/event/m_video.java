package org.ms.matrix.sdk.model.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_video  extends BaseContent{

    private String msgtype;
    private String body;
    private String url;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
   public class ThumbnailInfo {

        private int w;
        private int h;
        private String mimetype;
        int size;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Info {

        private int duration;
        private int h;
        private int w;
        private int size;
        private String thumbnail_url;
        private String mimetype;
        private ThumbnailInfo thumbnail_info;


    }
}
