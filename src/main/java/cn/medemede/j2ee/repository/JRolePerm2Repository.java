package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.JRolePerm2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JRolePerm2Repository extends JpaRepository<JRolePerm2,Integer> {
    List<JRolePerm2> findByRoleName(String roleName);
}
