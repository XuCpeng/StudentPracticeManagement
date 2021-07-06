package cn.medemede.spm.repository;

import cn.medemede.spm.model.AcBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xcp
 */
public interface AcBeanRepository extends JpaRepository<AcBean, Integer> {

    /**
     * 通过活动名称查找活动
     *
     * @param acName
     * @return
     */
    public List<AcBean> findByAcName(String acName);
}
