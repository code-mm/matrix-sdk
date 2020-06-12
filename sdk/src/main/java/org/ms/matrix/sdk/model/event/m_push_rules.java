package org.ms.matrix.sdk.model.event;

import java.util.List;
import java.util.concurrent.locks.Condition;

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
public class m_push_rules extends BaseEvent {

    private String type;


}