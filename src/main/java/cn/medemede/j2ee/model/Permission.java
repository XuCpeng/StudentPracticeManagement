package cn.medemede.j2ee.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "j_permission")
@lombok.Data
public class Permission implements org.apache.shiro.authz.Permission {

    @Id
    @GeneratedValue
    @Column(name = "perm_id")
    private Integer permId;

    private String permName;

    @ManyToMany(mappedBy = "permissionSet")
//    @JoinTable(
//            name = "j_user_role",
//            joinColumns = {@JoinColumn(name = "perm_id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet=new HashSet<>();

    @Override
    public boolean implies(org.apache.shiro.authz.Permission p) {


        return false;
    }
}
