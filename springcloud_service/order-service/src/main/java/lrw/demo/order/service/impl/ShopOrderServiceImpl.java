package lrw.demo.order.service.impl;

import lrw.demo.order.entity.ShopOrder;
import lrw.demo.order.mapper.ShopOrderMapper;
import lrw.demo.order.service.ShopOrderService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author by lrw
 * @Classname ShopOrderServiceImpl
 * @Description TODO
 * @Date 2020/12/15 11:03
 */
public class ShopOrderServiceImpl implements ShopOrderService {


    public static void main(String[] args) {
        Class<ShopOrderMapper> shopOrderMapperClass = ShopOrderMapper.class;
        try {
            System.out.println(shopOrderMapperClass.getInterfaces().length);
            Arrays.stream(shopOrderMapperClass.getInterfaces()).forEach(cla -> System.out.println(cla));
            System.out.println(shopOrderMapperClass.getName());
            Method findAll = shopOrderMapperClass.getMethod("findAll");
            System.out.println(findAll.getName());
            System.out.println(findAll.getDeclaringClass());
            System.out.println(new ShopOrderServiceImpl() instanceof Object);
            System.out.println(Object.class.equals(findAll.getDeclaringClass()));
            System.out.println(boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ShopOrder> findAll() {
        return null;
    }
}
