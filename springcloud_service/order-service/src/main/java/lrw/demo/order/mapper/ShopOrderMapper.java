package lrw.demo.order.mapper;

import lrw.demo.order.entity.ShopOrder;

import java.util.Arrays;
import java.util.List;

/**
 * @author by lrw
 * @Classname ShopOrderMapper
 * @Description TODO
 * @Date 2020/12/15 11:01
 */
public interface ShopOrderMapper {

    default List<ShopOrder> findAll(){
        return null;
    }

}
