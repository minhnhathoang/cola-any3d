package org.nhathm.user.dataobject;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "t_user")
public class UserDO {

    @Id
    private String username;

    private String hashedPassword;

    private Long userInfoId;
}
