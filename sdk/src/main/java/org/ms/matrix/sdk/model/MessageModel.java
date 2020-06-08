package org.ms.matrix.sdk.model;

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
public class MessageModel extends IModel {

    private String eventType;
    private MessageContent content;


    /**
     * 房间事件类型
     */
    public static interface RoomEventType {

        String M_ROOM_MESSAGE = "m.room.message";
        String M_ROOM_MESSAGE_FEEDBACK = "m.room.message.feedback";
        String M_ROOM_NAME = "m.room.name";
        String M_ROOM_TOPIC = "m.room.topic";
        String M_ROOM_AVATAR = "m.room.avatar";
        String M_ROOM_EVENTS = "m.room.pinned_events";

    }

    /**
     * VOIP 事件类型
     */
    public static interface CallEventType {
        String M_CALL_INVITE = "m.call.invite";
        String M_CALL_CANDIDATES = "m.call.candidates";
        String M_CALL_ANSWER = "m.call.answer";
        String M_CALL_HANGUP = "m.call.hangup";
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class MessageContent extends IModel {
        private String msgtype;
        private String body;


        public static interface MessageType {

            String M_TEXT = "m.text";
            String M_EMOTE = "m.emote";
            String M_NOTICE = "m.notice";
            String M_IMAGE = "m.image";
            String M_FILE = "m.file";
            String M_AUDIO = "m.audio";
            String M_LOCATION = "m.location";
            String M_VIDEO = "m.video";


        }

    }


}
