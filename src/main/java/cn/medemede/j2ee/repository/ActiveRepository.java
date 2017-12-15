package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.Active;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveRepository extends JpaRepository<Active,String> {
    //public Active findByAcName(String acName);

}
