package org.ms.matrix.sdk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomJoinedUserInfo {


    private String room_id;
    private String avatar_url;
    private String display_name;

}
