package org.nhathm.project.dataobject;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@ToString
@Entity
@Table(name = "projects", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "name"})
})
public class ProjectDO {

    @Id
    private String id;

    private String userId;

    private String name;
}
