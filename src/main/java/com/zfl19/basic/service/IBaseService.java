package com.zfl19.basic.service;

import java.util.List;

public interface IBaseService<T, Q> {

    // 查询所有
    List<T> getAll();

}
