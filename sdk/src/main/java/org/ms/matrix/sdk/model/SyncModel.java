package org.ms.matrix.sdk.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SyncModel {


    private String since;
    private String filter;
    private boolean full_state;
    // ["offline", "online", "unavailable"]
    private boolean set_presence;
    private int timeout;


}
