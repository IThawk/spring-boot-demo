package com.gupaoedu.security.web;

import com.gupaoedu.core.mvc.controller.BaseController;
import com.gupaoedu.security.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.core.common.ResultMsg;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tom on 2018/12/27.
 */
@RestController
@RequestMapping("/mall")
public class MallController extends BaseController {

    @Autowired private OrderService orderService;

    /**
     * 获取订单详情
     * @param request
     * @param orderId
     * @param enc
     * @return
     */
    @GetMapping(value="/order/detail/{orderId}.json")
    public Mono<Object> getDetailByOrderId(HttpServletRequest request,
                                           @PathVariable("orderId") String orderId,
                                           String enc){
        String local = super.getLocal(request);
        ResultMsg<?> result = orderService.getDetail(local,orderId,enc);
        return Mono.just(result);
    }

    /**
     * 支付
     * @param request
     * @param orderId
     * @param amount
     * @param enc
     * @return
     */
    @PostMapping(value="/order/pay/{orderId}.json")
    public Mono<Object> pay(HttpServletRequest request,
                            @PathVariable("orderId") String orderId,
                            Float amount,
                            String enc){
        String local = super.getLocal(request);
        ResultMsg<?> result = orderService.payment(local,orderId,amount,enc);
        return Mono.just(result);
    }



}
