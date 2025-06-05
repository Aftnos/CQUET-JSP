package org.example.emarketmall.entity;

import org.example.emarketmall.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类
 */
public class ProductCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 分类等级
     */
    private Integer categoryLevel;

    /**
     * 分类状态
     */
    private Integer categoryStatus;

    /**
     * 分类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 分类名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 分类编码
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * 分类编码
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 父ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 父ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 分类等级
     */
    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    /**
     * 分类等级
     */
    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * 分类状态
     */
    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    /**
     * 分类状态
     */
    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + getId() + '\'' +
                "categoryName=" + categoryName + '\'' +
                "categoryCode=" + categoryCode + '\'' +
                "parentId=" + parentId + '\'' +
                "categoryLevel=" + categoryLevel + '\'' +
                "categoryStatus=" + categoryStatus + '\'' +
                "delFlag=" + getDelFlag() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "createTime=" + getCreateTime() + '\'' +
                "updateBy=" + getUpdateBy() + '\'' +
                "updateTime=" + getUpdateTime() + '\'' +
                "REMARK=" + getRemark() + '\'' +
                '}';
    }
}
