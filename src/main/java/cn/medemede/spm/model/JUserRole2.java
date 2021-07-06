package cn.medemede.spm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Saber
 */
@Entity
@lombok.Data
@Table(name = "j_user_role", schema = "j2ee")
public class JUserRole2 {

    @Id
    @GeneratedValue
    private Integer id;

    private String stuId;

    private String roleName;

}
