package cn.medemede.j2ee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@lombok.Data
public class JUserRole2 {

    @Id
    @GeneratedValue
    private Integer id;

    private String stuId;

    private String roleName;

}
