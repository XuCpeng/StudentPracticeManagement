package cn.medemede.spm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@lombok.Data
public class AcProve {

    @Id
    private String stuId;  //学号

    private String stuName; //学生姓名
    private String sex;  //性别
    private String birth;  //出生日期
    private String level; //年级
    private String klass;  //班级
    private String startY; //入学年份
    private String startM; //入学月份
    private String endY;  //结束年分
    private String endM;  //结束月份
    private String school;  //学校
    private Date proveDate;  //时间


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stuId")  //设置外建
    private Set<AcBean> acList=new HashSet<>();  //活动列表

}
