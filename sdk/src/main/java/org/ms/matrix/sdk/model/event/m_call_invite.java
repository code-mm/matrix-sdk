package org.ms.matrix.sdk.model.event;

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
public class m_call_invite extends BaseEvent {

    private String room_id;
    private String type;
    private Unsigned unsigned;
    private String event_id;
    private long origin_server_ts;

    private String sender;
    private Invtie content;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Invtie extends BaseContent {

        private int version;
        private String call_id;
        private int lifetime;
        private Offer offer;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
       public static class Offer  extends BaseContent{
            private String sdp;
            private String type;
        }

    }
}
