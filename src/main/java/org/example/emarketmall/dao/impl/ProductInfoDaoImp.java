package org.example.emarketmall.dao.impl;

import org.example.emarketmall.dao.IDataAccess;
import org.example.emarketmall.dao.ProductInfoDao;
import org.example.emarketmall.entity.ProductInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年06月11日 22:03
 */
public class ProductInfoDaoImp implements ProductInfoDao {
    private IDataAccess dac;

    public ProductInfoDaoImp(){
        dac=new DataAccessImpl();
    }

    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception {
        if(productInfo!=null){
            return selectProductInfoByProductParams(productInfo);
        }
        return dac.getList(ProductInfo.class);
    }

    private List<ProductInfo> selectProductInfoByProductParams(ProductInfo param) throws Exception {
        List<ProductInfo> list = new ArrayList<>();
        //是否输入了多个查询条件
        List<String> params = Arrays.asList("id", "productName", "publishStatus");

        for (String p : params) {
            switch (p) {
                case "id":
                    if (param.getId() != null) {
                        list.add(selectProductInfoById(param.getId()));
                    }
                    break;
                case "loginName":
                    if (param.getProductName() != null) {
                        //do it yourself
                    }
                    break;
                case "publishStatus":
                    if (param.getPublishStatus() != null) {
                        //the same
                    }

                default:
                    break;
            }
        }
        return list;
    }

    @Override
    public ProductInfo selectProductInfoById(Integer id) throws Exception {
        return dac.getEntityById(ProductInfo.class,id);
    }
}
