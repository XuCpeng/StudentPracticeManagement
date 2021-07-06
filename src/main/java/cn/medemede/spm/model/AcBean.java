package cn.medemede.spm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@lombok.Data
public class AcBean {

    @Id
    @GeneratedValue
    private Integer id;

    private String stuId;
    //活动序号
    private Integer acId;
    //活动名
    private String acName;
    //活动时间
    private String acTime;
    //活动时长
    private Float acHour;
    //活动角色
    private String acRole;
    //活动单位
    private String acUnit;
    //证明人
    private String witne;

}
