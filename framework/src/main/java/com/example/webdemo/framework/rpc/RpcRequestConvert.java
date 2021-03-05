package com.example.webdemo.framework.rpc;

import org.apache.commons.lang3.StringUtils;

/**
 * @author safe
 * @date 2021/3/6
 */
public class RpcRequestConvert {
    public static final String SERIAL_NO = "serialNo";
    public static final String IDEM_SERIAL_NO = "idemSerialNo";
    public static final String SIGN = "sign";
    public static final String TRANS_DATE_TIME = "transDateTime";

    public RpcRequestConvert() {
    }

    public static String encode(RpcRequest rpcRequest) {
        if (rpcRequest == null) {
            return "";
        } else {
            StringBuilder builder = new StringBuilder();
            if (StringUtils.isNoneBlank(rpcRequest.getSerialNo())) {
                builder.append(SERIAL_NO)
                    .append("=")
                    .append(rpcRequest.getSerialNo())
                    .append("&");
            }
            if (StringUtils.isNoneBlank(rpcRequest.getIdemSerialNo())) {
                builder.append(IDEM_SERIAL_NO)
                    .append("=")
                    .append(rpcRequest.getIdemSerialNo())
                    .append("&");
            }
            if (rpcRequest.getTransDateTime() != null) {
                builder.append(TRANS_DATE_TIME)
                    .append("=")
                    .append(rpcRequest.getTransDateTime())
                    .append("&");
            }
            if (StringUtils.isNoneBlank(rpcRequest.getSign())) {
                builder.append(SIGN)
                    .append("=")
                    .append(rpcRequest.getSign())
                    .append("&");
            }
            return builder.length() == 0 ? "" : builder.substring(0, builder.length() - 1);
        }
    }

    public static RpcRequest decode(String uriStr) {
        if (StringUtils.isBlank(uriStr)) {
            return null;
        } else {
            RpcRequest build = RpcRequest.Builder.getInstance()
                .build();
            // todo 解析url &
            return build;
        }
    }
}
