package cn.medemede.spm.repository;

import cn.medemede.spm.model.JRolePerm2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Saber
 */
public interface JRolePerm2Repository extends JpaRepository<JRolePerm2, Integer> {
    /**
     * 通过角色名查询角色权限
     *
     * @param roleName
     * @return
     */
    List<JRolePerm2> findByRoleName(String roleName);
}
