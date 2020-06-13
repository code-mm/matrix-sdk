package org.ms.matrix.app.ui.activity.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageBean {

    private String msgtype;

    private String sender;

    private String content;


}
