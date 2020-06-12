package org.ms.matrix.sdk.model.request;

import lombok.Builder;
import lombok.Data;

/**
 * 同步
 */
@Data
@Builder
public class SyncRequest {


    private String since;
    private String filter;
    private boolean full_state;
    // ["offline", "online", "unavailable"]
    private boolean set_presence;
    private int timeout;


}
