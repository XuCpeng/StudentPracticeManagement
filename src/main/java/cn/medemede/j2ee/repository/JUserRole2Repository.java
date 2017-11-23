package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.JUserRole2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JUserRole2Repository extends JpaRepository<JUserRole2,Integer> {

    List<JUserRole2> findByStuId(String StuId);

    List<JUserRole2> findByRoleName(String roleName);
}
