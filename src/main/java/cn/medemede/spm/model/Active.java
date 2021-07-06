package cn.medemede.spm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@lombok.Data
public class Active {

    @Id
    private String acName; //活动名

    private String acTime;  //活动时间
    private String acUnit; //活动单位
    private Integer struts; //状态

    private Date creatDate;//创建时间
    private Date clearDate; //过期时间

}
