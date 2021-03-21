package com.example.webdemo.framework.rpc;

import java.io.Serializable;
import java.util.Date;

/**
 * @author safe
 * @date 2021/3/5
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 3865454273683328426L;

    private String serialNo;
    private String idemSerialNo;
    private String sign;
    private Date transDateTime;

    public static final class Builder {
        private RpcRequest instance = new RpcRequest();

        public Builder() {
        }

        /**
         * 获取实例
         *
         * @return
         */
        public static Builder getInstance() {
            return new Builder();
        }

        public Builder addSign(String sign) {
            this.instance.setSign(sign);
            return this;
        }

        public Builder addSerialNo(String serialNo) {
            this.instance.serialNo = serialNo;
            return this;
        }

        public Builder addIdemSerialNo(String idemSerialNo) {
            this.instance.idemSerialNo = idemSerialNo;
            return this;
        }

        public Builder addTransDateTime(Date transDateTime) {
            this.instance.transDateTime = transDateTime;
            return this;
        }

        public RpcRequest build() {
            return this.instance;
        }
    }

    public String getIdemSerialNo() {
        return idemSerialNo;
    }

    public void setIdemSerialNo(String idemSerialNo) {
        this.idemSerialNo = idemSerialNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Date getTransDateTime() {
        return transDateTime;
    }

    public void setTransDateTime(Date transDateTime) {
        this.transDateTime = transDateTime;
    }

    /**
     * 深拷贝
     *
     * @return
     */
    public RpcRequest copy() {
        return RpcRequest.Builder.getInstance()
            .addIdemSerialNo(getIdemSerialNo())
            .build();
    }
}
