package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,String>{

    List<Region> findByParentId(double parentId);

    List<Region> findByRegionNameAndRegionId(String city,int parentId);

}
