package org.ms.matrix.sdk.model.event;

import android.telephony.SignalStrength;

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
public class m_call_answer extends BaseEvent {

    private String type;
    private String event_id;
    private Unsigned unsigned;
    private String sender;
    private String room_id;
    private long origin_server_ts;
    private Content content;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
  public   static class Content extends BaseContent {

        private int version;
        private int lifetime;
        private String call_id;
        private Answer answer;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
   public static class Answer extends BaseContent {
        private String sdp;
        private String type;
    }
}
