package cn.medemede.spm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@lombok.Data
@Table(name = "student", schema = "j2ee")
public class Stu {

    @Id
    private String stuId;  //学号

    private String stuName; //学生姓名
    private String sex;  //性别
    private String birth;  //出生日期
    private String level; //年级
    private String klass;  //班级
    private String startY; //入学年份
    private String startM; //入学月份
    private String school;  //学校

    public void setByAcProve(AcProve acProve){
        this.stuId=acProve.getStuId();
        this.stuName=acProve.getStuName();
        this.sex=acProve.getSex();
        this.birth=acProve.getBirth();
        this.level=acProve.getLevel();
        this.klass=acProve.getKlass();
        this.startY=acProve.getStartY();
        this.startM=acProve.getStartM();
        this.school=acProve.getSchool();
    }

}
