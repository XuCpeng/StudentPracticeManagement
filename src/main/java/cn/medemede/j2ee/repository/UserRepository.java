package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
