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
public class m_push_rules implements IEvent {

    private String type;
    private Content content;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    class Content {

        private Global global;
        List<String> sender;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @EqualsAndHashCode(callSuper = false)
        class Global {
            List<Underride> underride;

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            @EqualsAndHashCode(callSuper = false)
            class Underride {
                private List<Condition> conditions;


                private String rule_id;
                private boolean _default;
                private boolean enabled;


                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                @EqualsAndHashCode(callSuper = false)
                class Conditions {

                    private String kind;
                    private String key;
                    private String pattern;
                }
            }
        }
    }
}