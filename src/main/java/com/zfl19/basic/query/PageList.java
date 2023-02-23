package com.zfl19.basic.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据返回工具类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {

    private Long total;     // 分页总条数

    private List<T> list;       // 存放分页数据

}
