package org.ms.matrix.sdk.model.event;

import java.util.List;

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
public class m_call_candidates extends BaseEvent {

    private String room_id;
    private String type;
    private String sender;
    private long origin_server_ts;
    private Unsigned unsigned;
    private String event_id;

    private Candidates content;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Candidate  extends BaseContent{

        private int sdpMLineIndex;
        private String sdpMid;
        private String candidate;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Candidates  extends BaseContent{
        private int version;
        private String call_id;
        private List<Candidate> candidates;

    }
}

