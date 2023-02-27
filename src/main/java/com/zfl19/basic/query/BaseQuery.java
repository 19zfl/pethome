package com.zfl19.basic.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收前端传递的分页数据
 */
@Data
@ApiModel(value = "BaseQuery",description = "分页参数实体类")
public class BaseQuery {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;    // 页码

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;   // 每页条数

    private Long[] ids;     // 批量删除接受参数

}
