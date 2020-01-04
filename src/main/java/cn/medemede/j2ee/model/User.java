package cn.medemede.j2ee.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "j_user")
@lombok.Data
public class User {

    @Id
    @Column(name = "stu_id")
    private String stuId;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

//    @ManyToMany(cascade = CascadeType.ALL)
//    private Set<Role> userRoleSet=new HashSet<>();

}
