package org.ms.matrix.sdk.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomsResponse {


    private List<String> joined_rooms;

    public List<String> getJoined_rooms() {
        return joined_rooms;
    }

    public void setJoined_rooms(List<String> joined_rooms) {
        this.joined_rooms = joined_rooms;
    }
}
