package com.zfl19.basic.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回统一的前端数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {

    private String code;    // 状态码

    private Boolean success;    // 请求状态

    private String message;     // 返回消息

    private Object data;    // 返回给前端的数据

    /**
     * 请求成功返回的数据
     * @return
     */
    public static AjaxResult createSucces() {
        return new AjaxResult("0000", true, "操作成功", null);
    }

    /**
     * 请求成功返回的数据
     * @return
     */
    public static AjaxResult createSucces(Object obj) {
        return new AjaxResult("0000", true, "操作成功", obj);
    }

    /**
     * 请求失败返回的数据
     * @return
     */
    public static AjaxResult createError(String msg) {
        return new AjaxResult("1001", false, msg, null);
    }

    /**
     * 请求失败返回的数据
     * @param code
     * @param msg
     * @return
     */
    public static AjaxResult createError(String code, String msg) {
        return new AjaxResult(code, false, msg, null);
    }

}
