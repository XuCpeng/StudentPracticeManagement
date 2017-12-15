package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.AcBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcBeanRepository extends JpaRepository<AcBean,Integer> {
    public List<AcBean> findByAcName(String acName);
}
