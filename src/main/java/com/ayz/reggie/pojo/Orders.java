package com.ayz.reggie.pojo;

import java.math.BigDecimal;

public class Orders {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.number
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.status
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.user_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.address_book_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private Long addressBookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.order_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String orderTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.checkout_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String checkoutTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.pay_method
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private Integer payMethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.amount
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private BigDecimal amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.remark
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.phone
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.address
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.user_name
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.consignee
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    private String consignee;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.id
     *
     * @return the value of orders.id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.id
     *
     * @param id the value for orders.id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.number
     *
     * @return the value of orders.number
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.number
     *
     * @param number the value for orders.number
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.status
     *
     * @return the value of orders.status
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.status
     *
     * @param status the value for orders.status
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.user_id
     *
     * @return the value of orders.user_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.user_id
     *
     * @param userId the value for orders.user_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.address_book_id
     *
     * @return the value of orders.address_book_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public Long getAddressBookId() {
        return addressBookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.address_book_id
     *
     * @param addressBookId the value for orders.address_book_id
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.order_time
     *
     * @return the value of orders.order_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.order_time
     *
     * @param orderTime the value for orders.order_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.checkout_time
     *
     * @return the value of orders.checkout_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getCheckoutTime() {
        return checkoutTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.checkout_time
     *
     * @param checkoutTime the value for orders.checkout_time
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime == null ? null : checkoutTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.pay_method
     *
     * @return the value of orders.pay_method
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.pay_method
     *
     * @param payMethod the value for orders.pay_method
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.amount
     *
     * @return the value of orders.amount
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.amount
     *
     * @param amount the value for orders.amount
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.remark
     *
     * @return the value of orders.remark
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.remark
     *
     * @param remark the value for orders.remark
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.phone
     *
     * @return the value of orders.phone
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.phone
     *
     * @param phone the value for orders.phone
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.address
     *
     * @return the value of orders.address
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.address
     *
     * @param address the value for orders.address
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.user_name
     *
     * @return the value of orders.user_name
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.user_name
     *
     * @param userName the value for orders.user_name
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.consignee
     *
     * @return the value of orders.consignee
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.consignee
     *
     * @param consignee the value for orders.consignee
     *
     * @mbggenerated Thu Aug 25 14:32:48 CST 2022
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }
}