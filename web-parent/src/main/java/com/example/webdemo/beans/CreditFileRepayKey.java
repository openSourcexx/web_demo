package com.example.webdemo.beans;

public class CreditFileRepayKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_file_repay.biz_serial_id
     *
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    private String bizSerialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_file_repay.tenant_id
     *
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    private String tenantId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_file_repay.biz_serial_id
     *
     * @return the value of credit_file_repay.biz_serial_id
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    public String getBizSerialId() {
        return bizSerialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_file_repay.biz_serial_id
     *
     * @param bizSerialId the value for credit_file_repay.biz_serial_id
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    public void setBizSerialId(String bizSerialId) {
        this.bizSerialId = bizSerialId == null ? null : bizSerialId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_file_repay.tenant_id
     *
     * @return the value of credit_file_repay.tenant_id
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_file_repay.tenant_id
     *
     * @param tenantId the value for credit_file_repay.tenant_id
     * @mbggenerated Tue Oct 08 16:25:08 CST 2019
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}