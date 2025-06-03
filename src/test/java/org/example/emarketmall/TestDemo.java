package org.example.emarketmall;

import cqcet.aibd.soft.DruidConfig;
import cqcet.aibd.soft.ObjectUtil;
import org.example.emarketmall.dao.IDataAccess;
import org.example.emarketmall.dao.UserInfoDao;
import org.example.emarketmall.dao.impl.DataAccessImpl;
import org.example.emarketmall.dao.impl.UserInfoDaoImpl;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.login.PasswordService;
import org.example.emarketmall.utils.BeanUtils;
import org.example.emarketmall.utils.DateUtils;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年05月23日 17:42
 */
public class TestDemo {
    @Test
    public void testDruidDataSourcePool(){
        String sql = "select * from user_info";
        IDataAccess dataAccess=new DataAccessImpl();
        try {
            List<UserInfo> userInfoList=dataAccess.getList(UserInfo.class);
            userInfoList.forEach(u->{
                System.out.println(u.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBeanUtils(){
        List<String> properties=BeanUtils.getBeanProperties(new UserInfo());
        properties.forEach(System.out::println);

    }

    @Test
    public void testUserDao(){
        UserInfoDao dao=new UserInfoDaoImpl();
        UserInfo user=new UserInfo();
        user.setLoginName("sa");
        try {
            List<UserInfo> userInfoList=dao.selectUserInfoList(user);
            userInfoList.forEach(u->
                    System.out.println(u.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFieldValue(){
        UserInfoDao dao=new UserInfoDaoImpl();
        UserInfo user= null;
        try {
            user = dao.selectUserInfoById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value=BeanUtils.getFieldValueByName("name",user);
        System.out.println(value);
    }

    @Test
    public void testSelectUserInfoList(){
        UserInfoDao dao=new UserInfoDaoImpl();
        UserInfo user= new UserInfo();
        user.setName("six");
        List<UserInfo> users=null;
        try {
            users = dao.selectUserInfoList(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users != null) {
            users.forEach(u->{
                System.out.println(u.toString());
            });
        }
    }

    @Test
    public void testCheckLoginNameUnique(){
        UserInfoDao dao=new UserInfoDaoImpl();
        int i=dao.checkLoginNameUnique("san");
        System.out.println(i);
    }

    @Test
    public void testUpdateUserInfo(){
        UserInfoDao dao=new UserInfoDaoImpl();
        /*dao.deleteUserInfoById(3);
        try {
            List<UserInfo> users=dao.selectUserInfoList(null);
            users.forEach(u->
                    System.out.println(u.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo u=dao.selectUserInfoByIdIncDel(3);
        u.setDelFlag("1");*/
        UserInfo u= null;
        try {
            u = dao.selectUserInfoById(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        u.setEmail("zhangsan@163.com");
        if(dao.updateUserInfo(1,u)<1){
            System.out.println("update error...");
        }else {
            try {
                u = dao.selectUserInfoById(1);
                System.out.println(u.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testPath(){
        String path=Object.class.getResource("/db.properties").getPath();
        System.out.println(path);
    }

    @Test
    public void testCopyMap2Object(){
        Map<String,String> map=new HashMap<>();
        map.put("name", "san");
        map.put("phone","13123456789");
        UserInfo userInfo=null;
        try {
            userInfo=BeanUtils.copyMap2Object(map,UserInfo.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(userInfo.toString());
    }


}
