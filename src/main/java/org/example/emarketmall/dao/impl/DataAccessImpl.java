package org.example.emarketmall.dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import org.example.emarketmall.dao.IDataAccess;
import org.example.emarketmall.utils.StringUtils;

import java.util.List;

/**
 * @Description: 实现列表查询，id查询的通用方法
 * @author: april
 * @date: 2022年05月30日 22:53
 */
public class DataAccessImpl implements IDataAccess {
    @Override
    public <T> List<T> getList(Class<T> clazz) throws Exception {
        String clsName = StringUtils.toUnderScoreCase(clazz.getSimpleName());
        String sql = null;
        if ("order".equals(clsName)) {
            sql = "SELECT * FROM `order` where delFlag= 0";
        } else {
            sql = "SELECT * FROM " + clsName + " where delFlag= 0";
        }
        return new ObjectUtil<T>().getList(sql, clazz);
    }

    @Override
    public <T> T getEntityById(Class<T> clazz, Integer id) throws Exception {
        String sql = null;
        String clsName = StringUtils.toUnderScoreCase(clazz.getSimpleName());
        if ("order".equals(clsName)) {
            sql = "SELECT * FROM `order` where id = ? and delFlag= 0";
        } else {
            sql = "SELECT * FROM " + clsName + " where id = ? and delFlag= 0";
        }
        return new ObjectUtil<T>().getOne(sql, clazz, id);
    }
}
