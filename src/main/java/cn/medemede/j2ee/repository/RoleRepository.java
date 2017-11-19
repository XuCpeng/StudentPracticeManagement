package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Role findByRoleName(String roleName);

}
