package org.example.emarketmall.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.filter.ServletRequestHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 客户端工具类
 */
public class ServletUtils {

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request，存在问题，勿使用
     */
    public static HttpServletRequest getRequest() {
        return ServletRequestHolder.getRequest();
    }

    /**
     * 获取response，存在问题，勿使用
     */
    public static HttpServletResponse getResponse() {
        return ServletRequestHolder.getResponse();
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }

    /**
     * 判断前端请求是 是form请求
     *
     * @param request
     * @return
     */
    public static boolean isFormUrlencodedRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return StringUtils.isNotEmpty(contentType) && contentType.contains("application/x-www-form-urlencoded");
    }

    /**
     * 判断前端请求ContentType是否为application/json
     *
     * @param request
     * @return
     */
    public static boolean isJsonContentTypeRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return StringUtils.isNotEmpty(contentType) && contentType.contains("application/json");
    }

    /**
     * 读取content-type为非form形式的请求数据
     *
     * @param req
     * @return
     */
    public static String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        ;
        try(BufferedReader reader =req.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }/*finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }*/
        return sb.toString();
    }

    /**
     * 从form形式的payload数据中取值
     *
     * @param payload
     * @return
     */
    public static Map<String, String> getFormDataFromPayload(String payload) {
        Map<String, String> formData = new HashMap<>();
        String[] payloads = payload.split("&");
        String[] datas;
        for (String pay : payloads) {
            datas = pay.split("=");
            if (datas.length < 2) {
                continue;
            }
            formData.put(datas[0] + "", datas[1]);

        }
        return formData;
    }

    /**
     * 把payload中的数据转换为对应泛型对象
     *
     * @param req
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObjectFromPayload(HttpServletRequest req, Class<T> clazz) {
        String payload = "";
        T t = null;
        Map<String, String> params = new HashMap<>();
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //是否为application/x-www-form-urlencoded; charset=UTF-8
        if (!isFormUrlencodedRequest(req)) {
            //是否为application/json
            if (isJsonContentTypeRequest(req)) {
                params = getParamMapFromRequest(req);
                return convertParamMap2Object(params, clazz);
            }
            payload = getRequestPayload(req);
            if (StringUtils.isEmpty(payload)) {
                return null;
            }
            //判读payload中的内容是否为Json格式
            if (payload.contains("{") && payload.contains("}")) {
                t = JSON.parseObject(payload, clazz);
            } else {
                //判断payload中的内容是否为keya=value1&keyb=value的形式
                Map<String, String> map = getFormDataFromPayload(payload);
                if (map.isEmpty()) {
                    return null;
                }
                for (String k : map.keySet()) {
                    //用户信息
                    if (t instanceof UserInfo) {
                        try {
                            t = BeanUtils.copyMap2Object(map, clazz);
                        } catch (IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                        /*if ("name".equalsIgnoreCase(k)) {
                            ((UserInfo) t).setName(map.get(k));
                            continue;
                        }
                        if ("loginName".equalsIgnoreCase(k)) {
                            ((UserInfo) t).setLoginName(map.get(k));
                            continue;
                        }
                        if ("password".equalsIgnoreCase(k)) {
                            ((UserInfo) t).setPassword(map.get(k));
                            continue;
                        }
                        if ("phone".equalsIgnoreCase(k)) {
                            ((UserInfo) t).setPhone(map.get(k));
                        }*/
                    }
                }

            }
        } else {
            params = getParamMapFromRequest(req);
            t = convertParamMap2Object(params, clazz);

        }
        return t;
    }

    private static <T> T convertParamMap2Object(Map map, Class<T> clazz) {
        if (!map.isEmpty()) {
            try {
                return BeanUtils.copyMap2Object(map, clazz);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * contentType
     *
     * @param req
     * @return
     */
    private static Map<String, String> getParamMapFromRequest(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> reqParameterMap = req.getParameterMap();
        AtomicInteger count = new AtomicInteger();
        reqParameterMap.forEach((k, v) -> {
            if (StringUtils.isEmpty(v)) {
                count.getAndIncrement();
            }
            params.put(k, v[0]);
        });
        if (count.intValue() == reqParameterMap.size()) {
            return null;
        }
        if (params.isEmpty()) {
            return null;
        }
        return params;
    }

    /**
     * 根据参数名从request请求中获取参数值的操作
     *
     * @param req
     * @param paramName
     * @return 参数值
     */
    public static String getParamFromPayLoad(HttpServletRequest req, String paramName) {
        //表单请求
        String payload = "";
        String param = req.getParameter(paramName);
        Map map;
        if (StringUtils.isNotEmpty(param)) {
            return param;
        } else {
            payload = getRequestPayload(req);
            //判读是否Json
            if (payload.contains("{") && payload.contains("}")) {
                map = JSONObject.parseObject(payload, Map.class);
            } else {
                map = getFormDataFromPayload(payload);
            }
        }
        if (!map.isEmpty()) {
            for (Object key : map.keySet()) {
                if (paramName.equalsIgnoreCase(key.toString())) {
                    return map.get(key).toString();
                }
            }
        }
        return null;
    }

}
