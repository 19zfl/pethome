package com.zfl19.basic.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 新增数据之后自动返回新增数据的id
    private Long id;

}
