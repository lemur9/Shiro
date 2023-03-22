package com.lemur.shiro.constant;

import javax.servlet.http.HttpServletRequest;

public interface CommonConstant {


    /**
     * 微服务读取配置文件属性 服务地址
     */
    String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";

    String TOKEN_IS_INVALID_MSG = "Token失效，请重新登录!";

    Integer SC_OK_200 = 200;

    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;

    Integer SC_JEECG_NO_AUTHZ=510;

    /**
     * 多租户 请求头
     */
    String TENANT_ID = "tenant-id";

    String X_USERNAME = "X-Access-UserName";

    String X_ACCESS_TOKEN = "X-Access-Token";

    /**
     * @param request
     *            IP
     * @return IP Address
     */
    static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
