package com.zfl19.org.domain;

import com.zfl19.basic.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_employee")
public class Employee extends BaseDomain {

    // id来自继承
    private String username;
    private String email;
    private String phone;
    private String password;
    private Integer age;
    private Integer state;
    private Long department_id;
    private Long logininfo_id;
    private Long shop_id;
    private Long parent_id;
    private Long manager_id;

}
