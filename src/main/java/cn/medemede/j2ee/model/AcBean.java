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
    private Integer id;

    private Integer acId;
    private String acName;
    private Date acTime;
    private Float acHour;
    private String acRole;
    private String acUnit;
    private String witne;

}
