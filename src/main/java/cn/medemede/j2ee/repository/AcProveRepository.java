package cn.medemede.j2ee.repository;

import cn.medemede.j2ee.model.AcProve;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AcProveRepository extends JpaRepository<AcProve, String> {

    //方法名不能乱写！！！一定要按照这个规则写，idea会有自动提示
    List<AcProve> findByStuId(String stuId);
}
