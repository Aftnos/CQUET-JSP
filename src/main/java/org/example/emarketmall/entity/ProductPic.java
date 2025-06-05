package org.example.emarketmall.entity;

import org.example.emarketmall.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片
 */
public class ProductPic extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 图片描述
     */
    private String picDesc;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 商品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 商品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 图片描述
     */
    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc;
    }

    /**
     * 图片描述
     */
    public String getPicDesc() {
        return picDesc;
    }

    /**
     * 图片地址
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 图片地址
     */
    public String getPicUrl() {
        return picUrl;
    }

    @Override
    public String toString() {
        return "ProductPic{" +
                "id=" + getId() + '\'' +
                "productId=" + productId + '\'' +
                "picDesc=" + picDesc + '\'' +
                "picUrl=" + picUrl + '\'' +
                "delFlag=" + getDelFlag() + '\'' +
                "createBy=" + getCreateBy() + '\'' +
                "createTime=" + getCreateTime() + '\'' +
                "updateBy=" + getUpdateBy() + '\'' +
                "updateTime=" + getUpdateTime() + '\'' +
                "REMARK=" + getRemark() + '\'' +
                '}';
    }
}
