package com.zfl19.basic.service.impl;

import com.zfl19.basic.domain.BaseDomain;
import com.zfl19.basic.query.BaseQuery;
import com.zfl19.basic.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public class BaseServiceImpl<T extends BaseDomain, Q extends BaseQuery> implements IBaseService {

    @Autowired
    private Mapper<T> mapper;

    @Override
    public List getAll() {
        return mapper.selectAll();
    }

}
