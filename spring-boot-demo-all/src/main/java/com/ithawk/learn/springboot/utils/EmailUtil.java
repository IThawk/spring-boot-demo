package com.ithawk.learn.springboot.utils;

import com.ithawk.learn.springboot.common.ExcelMaker;
import com.ithawk.learn.springboot.entity.ShareEmailDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author IThawk
 * @version V1.0
 * @description:
 * @date 2020-04-11 18:16
 */
@Slf4j
public class EmailUtil {

    /*
     *
     * @description: //TODO
     * @author IThawk
     * @date 2020/4/11 22:09
     * @param: excelMaker
     * @param: email
     * @return void
     */
    public  static<T> void makeExcelEmail( ExcelMaker<T> excelMaker, ShareEmailDetail email) {
        List<T> t = excelMaker.getData(email);
        System.out.println(t);
        List columns = excelMaker.getColumn();
        System.out.println(columns);
        excelMaker.addData(t);
    }
}
