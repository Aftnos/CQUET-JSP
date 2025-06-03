package org.example.emarketmall.dao;

import java.util.List;

public interface IDataAccess {
    <T> List<T> getList(Class<T> clazz) throws Exception;

    <T> T getEntityById(Class<T> clazz, Integer id) throws Exception;
}
