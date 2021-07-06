package cn.medemede.spm.repository;

import cn.medemede.spm.model.Stu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xcp
 */
public interface StuRepository extends JpaRepository<Stu, String> {

    /**
     * 通过年级和班级查询学生
     *
     * @param level
     * @param Klass
     * @return
     */
    public List<Stu> findByLevelAndKlass(String level, String Klass);
}
