package org.nhathm.domain.user.entity;

import com.alibaba.cola.domain.Entity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
}
