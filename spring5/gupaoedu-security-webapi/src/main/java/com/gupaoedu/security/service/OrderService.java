package com.gupaoedu.security.service;

import com.alibaba.fastjson.JSONObject;
import com.gupaoedu.security.codec.CodecToolKit;
import com.gupaoedu.security.common.constans.SystemConstant;
import com.gupaoedu.security.dao.OrderDao;
import com.gupaoedu.security.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.core.common.ResultMsg;
import java.math.BigDecimal;

/**
 * Created by Tom on 2018/12/27.
 */
@Service
public class OrderService {

    @Autowired private OrderDao orderDao;

    public ResultMsg<?> payment(String local, String orderId, Float amount, String enc) {
        //验证参数
        if(StringUtils.isEmpty(orderId) || null == amount || StringUtils.isEmpty(enc)){
            JSONObject error = new JSONObject();
            if(StringUtils.isEmpty(orderId)){
                error.put("orderId", "订单号未填写");
            }
            if(null == amount){
                error.put("amount", "交易金额未填写");
            }
            if(StringUtils.isEmpty(enc)){
                error.put("enc", "授权码未填写");
            }
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "参数不完整",error);
        }


        BigDecimal bigAmount = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
        //检查授权码是否正确
        if(!CodecToolKit.genEnc(orderId+bigAmount.toString()).equals(enc)){
            JSONObject error = new JSONObject();
            error.put("enc", "授权码错误");
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "授权码错误",error);
        }

        Order order = orderDao.selectById(orderId);
        if(0 != order.getPayAmount().setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP))){
            order.setProducts(null);
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "交易失败，支付金额有误",order);
        }

        return new ResultMsg(SystemConstant.RESULT_STATUS_SUCCESS,"支付成功",order);
    }

    public ResultMsg<?> getDetail(String local, String orderId, String enc) {
        //验证参数
        if(StringUtils.isEmpty(orderId) || StringUtils.isEmpty(enc)){
            JSONObject error = new JSONObject();
            if(StringUtils.isEmpty(orderId)){
                error.put("orderId", "订单号未填写");
            }
            if(StringUtils.isEmpty(enc)){
                error.put("enc", "授权码未填写");
            }
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "参数不完整",error);
        }
        //检查授权码是否正确
        if(!CodecToolKit.genEnc(orderId).equals(enc)){
            JSONObject error = new JSONObject();
            error.put("enc", "授权码错误");
            return new ResultMsg<Object>(SystemConstant.RESULT_PARAM_ERROR, "授权码错误",error);
        }

        Order order = orderDao.selectById(orderId);

        return new ResultMsg(SystemConstant.RESULT_STATUS_SUCCESS,"获取成功",order);
    }
}
