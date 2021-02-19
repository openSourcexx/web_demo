package com.example.webdemo.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "import_info")
public class ImportInfo {
    /**
     * 导入编号
     */
    @Id
    @Column(name = "import_id")
    private String importId;

    /**
     * 导入流水
     */
    @Column(name = "serial_no")
    private String serialNo;

    /**
     * 附件id
     */
    @Column(name = "attachment_id")
    private String attachmentId;

    /**
     * 导入类型
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 处理状态EnumImportState[00-处理完成 01-处理中 02-未处理]
     */
    @Column(name = "status")
    private String status;

    /**
     * 数据总量
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * 导入成功数量
     */
    @Column(name = "success_count")
    private Integer successCount;

    /**
     * 导入失败数量
     */
    @Column(name = "fail_count")
    private Integer failCount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取导入编号
     *
     * @return import_id - 导入编号
     */
    public String getImportId() {
        return importId;
    }

    /**
     * 设置导入编号
     *
     * @param importId 导入编号
     */
    public void setImportId(String importId) {
        this.importId = importId == null ? null : importId.trim();
    }

    /**
     * 获取导入流水
     *
     * @return serial_no - 导入流水
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 设置导入流水
     *
     * @param serialNo 导入流水
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
    }

    /**
     * 获取附件id
     *
     * @return attachment_id - 附件id
     */
    public String getAttachmentId() {
        return attachmentId;
    }

    /**
     * 设置附件id
     *
     * @param attachmentId 附件id
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId == null ? null : attachmentId.trim();
    }

    /**
     * 获取导入类型
     *
     * @return biz_type - 导入类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置导入类型
     *
     * @param bizType 导入类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    /**
     * 获取处理状态EnumImportState[00-处理完成 01-处理中 02-未处理]
     *
     * @return status - 处理状态EnumImportState[00-处理完成 01-处理中 02-未处理]
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置处理状态EnumImportState[00-处理完成 01-处理中 02-未处理]
     *
     * @param status 处理状态EnumImportState[00-处理完成 01-处理中 02-未处理]
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取数据总量
     *
     * @return total_count - 数据总量
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 设置数据总量
     *
     * @param totalCount 数据总量
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取导入成功数量
     *
     * @return success_count - 导入成功数量
     */
    public Integer getSuccessCount() {
        return successCount;
    }

    /**
     * 设置导入成功数量
     *
     * @param successCount 导入成功数量
     */
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    /**
     * 获取导入失败数量
     *
     * @return fail_count - 导入失败数量
     */
    public Integer getFailCount() {
        return failCount;
    }

    /**
     * 设置导入失败数量
     *
     * @param failCount 导入失败数量
     */
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}