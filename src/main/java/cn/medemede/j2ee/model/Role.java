package cn.medemede.j2ee.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "j_role")
@lombok.Data
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer roleId;

    private String roleName;

    @ManyToMany
    @JoinTable(
            name = "j_role_perm",
            joinColumns ={@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "perm_id")})
    private Set<Permission> permissionSet=new HashSet<>();

    @ManyToMany(mappedBy = "roleSet")
//    @JoinTable(
//            name = "j_user_role",
//            joinColumns = {@JoinColumn(name = "role_id")},
//            inverseJoinColumns = {@JoinColumn(name = "stu_id")})
    private Set<User> userSet=new HashSet<>();
}
