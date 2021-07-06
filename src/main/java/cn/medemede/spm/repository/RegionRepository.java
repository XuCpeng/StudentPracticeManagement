package cn.medemede.spm.repository;

import cn.medemede.spm.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Saber
 */
public interface RegionRepository extends JpaRepository<Region,String>{

    /**
     * 查询地区名称
     * @param parentId
     * @return
     */
    List<Region> findByParentId(double parentId);

    /**
     * 通过城市查询地区名称
     * @param city
     * @param parentId
     * @return
     */
    List<Region> findByRegionNameAndRegionId(String city,int parentId);

}
