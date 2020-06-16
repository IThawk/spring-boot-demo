package com.gupaoedu.security.dao;

import com.gupaoedu.security.model.entity.Order;
import com.gupaoedu.security.model.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2018/12/27.
 */
@Repository
public class OrderDao {
    public Order selectById(String orderId) {

        List<Product> products = new ArrayList<Product>();
        products.add(new Product("P2381037","电热水器",new BigDecimal(351)));
        products.add(new Product("P3606741","Blue高保真话筒",new BigDecimal(560)));
        products.add(new Product("P3606741","USB数据线",new BigDecimal(37.5f).setScale(2, BigDecimal.ROUND_HALF_UP)));
        products.add(new Product("P3606741","蓝牙音箱",new BigDecimal(562.8f).setScale(2, BigDecimal.ROUND_HALF_UP)));

        BigDecimal totalAmount = new BigDecimal(0);
        for (Product product : products) {
            totalAmount = totalAmount.add(product.getPrice());
        }

        return new Order(orderId,totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP),products);
    }
}
