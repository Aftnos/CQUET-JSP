package org.example.emarketmall.dao;

import org.example.emarketmall.entity.ProductInfo;

import java.util.List;

public interface ProductInfoDao {
    List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception;

    ProductInfo selectProductInfoById(Integer id) throws Exception;

}
