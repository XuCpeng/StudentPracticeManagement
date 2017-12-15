package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.Stu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuRepository extends JpaRepository<Stu,String> {
    public List<Stu> findByLevelAndKlass(String level,String Klass);
}
