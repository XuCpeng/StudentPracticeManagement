package cn.medemede.j2ee.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
    private Set<Permission> permissionSet;

    @ManyToMany(mappedBy = "userRoleSet")
    private Set<User> userSet=new HashSet<>();


    public Set<String> getPermsStringSet(){
        Set<String> permsStringSet=new HashSet<>();
        for(Permission permission:permissionSet){
            permsStringSet.add(permission.getPermName());
        }
        return permsStringSet;
    }
}
