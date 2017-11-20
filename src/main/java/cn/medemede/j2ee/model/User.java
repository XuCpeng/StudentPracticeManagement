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
    @Column(name = "stu_id")
    private String stuId;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> userRoleSet=new HashSet<>();

    public Set<String> getRolesStringSet(){
        Set<String> roleStringSet=new HashSet<>();
        for (Role role:userRoleSet){
            roleStringSet.add(role.getRoleName());
        }
        return roleStringSet;
    }
}
