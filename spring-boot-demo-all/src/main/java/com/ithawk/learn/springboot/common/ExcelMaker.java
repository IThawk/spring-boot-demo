package com.ithawk.learn.springboot.common;

import com.ithawk.learn.springboot.entity.ShareEmailDetail;

import java.util.List;

/**
 * @author IThawk
 * @version V1.0
 * @description: 邮件生成接口
 * @date 2020-04-11 20:44
 */
public interface ExcelMaker<T> {

    /**
     * @return java.util.List<T>
     * @description: 获取表格中需要的数据
     * @author IThawk
     * @date 2020/4/11 22:13
     * @param: email
     */
    List<T> getData(ShareEmailDetail email);

    /**
     * @return java.util.List<java.lang.String>
     * @description: 获取表格的列
     * @author IThawk
     * @date 2020/4/11 22:14
     * @param:
     */
    List<String> getColumn();

    /*
     *
     * @description: 表格中添加数据
     * @author IThawk
     * @date 2020/4/11 22:14
     * @param: t
     * @return void
     */
    void addData(List<T> t);


    String[][] makeContent(List<T> t);
}
