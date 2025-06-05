package org.example.emarketmall.common;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Entity基类
 * apache commons-dbutils包只支持数据字段使用驼峰命名，不支持下划线
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 数据唯一标识 */
    private Integer id;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private String createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private String updateTime;

    /** 备注 */
    private String remark;

    /** 删除状态 0已删除，1正常。默认值1 */
    private String delFlag = "1";

    /**
     * 通用唯一识别码，是由一组32位数的16进制数字所构成
     * 总共有 2^ 128个数字，大概是3.4 * 10^38个
     * 特点：不会重复
     * 举例：550e8400-e29b-41d4-a716-446655440000
     * @return 返回一个UUID字符串，不含连接符-
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
