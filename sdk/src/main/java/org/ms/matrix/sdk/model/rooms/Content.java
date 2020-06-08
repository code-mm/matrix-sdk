package org.ms.matrix.sdk.model.rooms;

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
public class Content {

    private boolean is_direct;
    private String membership;
    private String displayname;


    private String msgtype;
    private String body;

    private int version;
    private String call_id;
    private int lifetime;

    private Offer offer;


    private List<Candidates> candidates;


}
