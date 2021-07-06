package cn.medemede.spm.repository;

import cn.medemede.spm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Saber
 */
public interface UserRepository extends JpaRepository<User, String> {
}
