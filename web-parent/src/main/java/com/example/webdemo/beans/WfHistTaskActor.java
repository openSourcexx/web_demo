package com.example.webdemo.beans;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "wf_hist_task_actor")
public class WfHistTaskActor {
    /**
     * 任务ID
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 参与者ID
     */
    @Column(name = "actor_id")
    private String actorId;

    @Column(name = "tenant_id")
    private String tenantId;

    /**
     * 获取任务ID
     *
     * @return task_id - 任务ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置任务ID
     *
     * @param taskId 任务ID
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 获取参与者ID
     *
     * @return actor_id - 参与者ID
     */
    public String getActorId() {
        return actorId;
    }

    /**
     * 设置参与者ID
     *
     * @param actorId 参与者ID
     */
    public void setActorId(String actorId) {
        this.actorId = actorId == null ? null : actorId.trim();
    }

    /**
     * @return tenant_id
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }
}