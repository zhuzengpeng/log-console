package com.roowoo.log.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roowoo.log.config.Global;
import com.ckfinder.connector.ConnectorServlet;
import com.roowoo.log.common.utils.FileUtils;
import com.roowoo.log.modules.sys.utils.UserUtils;
import com.roowoo.log.modules.sys.security.SystemAuthorizingRealm;

/**
 * CKFinderConnectorServlet
 *
 * @author ThinkGem
 * @version 2014-06-25
 */
@WebServlet(urlPatterns = "/static/ckfinder/core/connector/java/connector.java", initParams = {
        @WebInitParam(name = "XMLConfig", value = "classpath:ckfinder.xml"),
        @WebInitParam(name = "debug", value = "false"),
        @WebInitParam(name = "configuration", value = "CKFinderConfig")
}, loadOnStartup = 1)
public class CKFinderConnectorServlet extends ConnectorServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        prepareGetResponse(request, response, false);
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        prepareGetResponse(request, response, true);
        super.doPost(request, response);
    }

    private void prepareGetResponse(final HttpServletRequest request,
                                    final HttpServletResponse response, final boolean post) throws ServletException {
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        if (principal == null) {
            return;
        }
        String command = request.getParameter("command");
        String type = request.getParameter("type");
        // 初始化时，如果startupPath文件夹不存在，则自动创建startupPath文件夹
        if ("Init".equals(command)) {
            // 当前文件夹可指定为模块名
            String startupPath = request.getParameter("startupPath");
            if (startupPath != null) {
                String[] ss = startupPath.split(":");
                if (ss.length == 2) {
                    String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
                            + principal + "/" + ss[0] + ss[1];
                    FileUtils.createDirectory(FileUtils.path(realPath));
                }
            }
        }
        // 快捷上传，自动创建当前文件夹，并上传到该路径
        else if ("QuickUpload".equals(command) && type != null) {
            // 当前文件夹可指定为模块名
            String currentFolder = request.getParameter("currentFolder");
            String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
                    + principal + "/" + type + (currentFolder != null ? currentFolder : "");
            FileUtils.createDirectory(FileUtils.path(realPath));
        }
    }

}