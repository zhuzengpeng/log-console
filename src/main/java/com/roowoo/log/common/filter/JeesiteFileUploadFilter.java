package com.roowoo.log.common.filter;

import com.ckfinder.connector.FileUploadFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 
 * @author zzp
 *
 */
@WebFilter(urlPatterns = "/static/ckfinder/core/connector/java/connector.java", initParams = {
        @WebInitParam(name = "sessionCookieName", value = "JSESSIONID"),
        @WebInitParam(name = "sessionParameterName", value = "jsessionid")
})
public class JeesiteFileUploadFilter extends FileUploadFilter {
}
