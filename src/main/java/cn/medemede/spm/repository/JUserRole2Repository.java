package cn.medemede.spm.repository;

import cn.medemede.spm.model.JUserRole2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Saber
 */
public interface JUserRole2Repository extends JpaRepository<JUserRole2, Integer> {

    /**
     * 通过id查询用户角色
     *
     * @param stuId
     * @return
     */
    List<JUserRole2> findByStuId(String stuId);

    /**
     * 通过角色名查询角色
     *
     * @param roleName
     * @return
     */
    List<JUserRole2> findByRoleName(String roleName);
}
