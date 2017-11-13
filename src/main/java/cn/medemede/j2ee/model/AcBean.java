package cn.medemede.j2ee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@lombok.Data
public class AcBean {

    @Id
    @GeneratedValue
    private Integer id; //主键，自增

    private Integer acId;  //活动序号
    private String acName; //活动名
    private Date acTime;  //活动时间
    private Float acHour;  //活动时长
    private String acRole;  //活动角色
    private String acUnit; //活动单位
    private String witne;  //证明人

}
