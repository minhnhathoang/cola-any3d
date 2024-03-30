package org.nhathm.user.database;

import org.nhathm.user.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, String> {

    Optional<UserDO> findByUsername(String username);
}

