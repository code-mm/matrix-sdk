package org.ms.matrix.sdk.model.rooms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidates {


    private int sdpMLineIndex;
    private String sdpMid;
    private String candidate;


}
