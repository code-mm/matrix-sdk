package org.ms.matrix.sdk.model.event;

import android.content.Context;

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
public class m_room_message<T extends IContent> extends BaseEvent {
    private String type;
    private String sender;
    private T content;
    private Unsigned unsigned;
    private long origin_server_ts;
    private String event_id;
    private String room_id;

}
