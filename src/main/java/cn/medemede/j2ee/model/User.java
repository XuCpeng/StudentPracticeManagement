package cn.medemede.j2ee.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "j_user")
@lombok.Data
public class User {

    @Id
    private String stuId;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = {@JoinColumn(name = "stu_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet=new HashSet<>();

}
