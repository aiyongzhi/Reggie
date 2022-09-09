package com.ayz.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;

public class Setmeal implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.category_id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private Long categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.name
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.price
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.status
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.code
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.description
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.image
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private String image;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.create_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.update_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.create_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.update_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column setmeal.is_deleted
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    private Integer isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.id
     *
     * @return the value of setmeal.id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.id
     *
     * @param id the value for setmeal.id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.category_id
     *
     * @return the value of setmeal.category_id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.category_id
     *
     * @param categoryId the value for setmeal.category_id
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.name
     *
     * @return the value of setmeal.name
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.name
     *
     * @param name the value for setmeal.name
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.price
     *
     * @return the value of setmeal.price
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.price
     *
     * @param price the value for setmeal.price
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.status
     *
     * @return the value of setmeal.status
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.status
     *
     * @param status the value for setmeal.status
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.code
     *
     * @return the value of setmeal.code
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.code
     *
     * @param code the value for setmeal.code
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.description
     *
     * @return the value of setmeal.description
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.description
     *
     * @param description the value for setmeal.description
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.image
     *
     * @return the value of setmeal.image
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.image
     *
     * @param image the value for setmeal.image
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.create_time
     *
     * @return the value of setmeal.create_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.create_time
     *
     * @param createTime the value for setmeal.create_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.update_time
     *
     * @return the value of setmeal.update_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.update_time
     *
     * @param updateTime the value for setmeal.update_time
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.create_user
     *
     * @return the value of setmeal.create_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.create_user
     *
     * @param createUser the value for setmeal.create_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.update_user
     *
     * @return the value of setmeal.update_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.update_user
     *
     * @param updateUser the value for setmeal.update_user
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column setmeal.is_deleted
     *
     * @return the value of setmeal.is_deleted
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column setmeal.is_deleted
     *
     * @param isDeleted the value for setmeal.is_deleted
     *
     * @mbggenerated Mon Aug 22 23:49:55 CST 2022
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}