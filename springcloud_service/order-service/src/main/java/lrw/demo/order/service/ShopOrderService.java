package lrw.demo.order.service;

import lrw.demo.order.entity.ShopOrder;

import java.util.List;

/**
 * @author by lrw
 * @Classname ShopOrderService
 * @Description TODO
 * @Date 2020/12/15 11:03
 */
public interface ShopOrderService {


    List<ShopOrder> findAll();
}
