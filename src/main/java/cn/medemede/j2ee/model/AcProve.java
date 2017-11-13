package cn.medemede.j2ee.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@lombok.Data
public class AcProve {

    @Id
    private String stuId;

    private String stuName;
    private String sex;
    private Date birth;
    private String level;
    private String klass;
    private String startY;
    private String startM;
    private String endY;
    private String endM;
    private String school;
    private Date proveDate;


    @OneToMany
    @JoinColumn(name = "stuId")
    private Set<AcBean> acList=new HashSet<>();

}
