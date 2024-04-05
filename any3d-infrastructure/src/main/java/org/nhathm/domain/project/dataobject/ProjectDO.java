package org.nhathm.domain.project.dataobject;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
public class ProjectDO {

    private String id;

    private String userId;

    private String name;
}
