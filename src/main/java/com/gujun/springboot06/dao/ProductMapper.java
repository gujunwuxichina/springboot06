package com.gujun.springboot06.dao;

import com.gujun.springboot06.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {

    Product getProduct(Integer pId);

    Product positive(Integer pId);

    int decreaseProduct(@Param("pId") Integer pId,@Param("quantity") int quantity);

}
