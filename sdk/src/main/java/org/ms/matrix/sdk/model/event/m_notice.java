package org.ms.matrix.sdk.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class m_notice {

    private String msgtype;
    private String body;
    private String format;
    private String formatted_body;
}
