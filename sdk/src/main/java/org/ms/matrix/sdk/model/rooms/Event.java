package org.ms.matrix.sdk.model.rooms;

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
public class Event {


    private String roomId;

    private String type;
    private String sender;
    private String state_key;
    private long origin_server_ts;
    private String event_id;
    private Content content;
    private Unsigned unsigned;
}
