package cn.medemede.spm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@lombok.Data
@Table(name = "j_role_perm", schema = "j2ee")
public class JRolePerm2 {

    @Id
    @GeneratedValue
    private Integer id;

    private String roleName;

    private String permName;

}
