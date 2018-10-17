package com.roowoo.log.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roowoo.log.modules.deptlog.entity.DeptLogin;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roowoo.log.config.Global;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.terminal.entity.LoginTerminal;
import com.roowoo.log.modules.terminal.service.LoginTerminalService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 终端登陆信息统计Controller
 * @author hhy
 * @version 2018-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/loginTerminal")
public class LoginTerminalController extends BaseController {

	@Autowired
	private LoginTerminalService loginTerminalService;
	
	@ModelAttribute
	public LoginTerminal get(@RequestParam(required=false) String id) {
		LoginTerminal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = loginTerminalService.get(id);
		}
		if (entity == null){
			entity = new LoginTerminal();
		}
		return entity;
	}
	
	@RequiresPermissions("terminal:loginTerminal:view")
	@RequestMapping(value = {"list", ""})
	public String list(LoginTerminal loginTerminal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LoginTerminal> page = loginTerminalService.findPage(new Page<LoginTerminal>(request, response), loginTerminal); 
		model.addAttribute("page", page);
		return "modules/terminal/loginTerminalList";
	}

	@RequiresPermissions("terminal:loginTerminal:view")
	@RequestMapping(value = "form")
	public String form(LoginTerminal loginTerminal, Model model) {
		model.addAttribute("loginTerminal", loginTerminal);
		return "modules/terminal/loginTerminalForm";
	}




    @RequiresPermissions("terminal:loginTerminal:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public void exportFile(LoginTerminal loginTerminal, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String fileName = "simple_export_output1.xls";
        Page<LoginTerminal> page = loginTerminalService.findPage(new Page<LoginTerminal>(request, response), loginTerminal);
        //准备输出流
        OutputStream os = null;
        try{
            //设置响应头
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(),"iso8859-1"));
            os = response.getOutputStream();
            List<String> headers = Arrays.asList("终端登录时间", "终端IP", "登录账户数", "页面访问总数", "生产网访问数","旅游网访问数","学习网访问数","访问成功数","访问失败数");
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, page.getList(), "loginTime, terminalIp, accountNumber, visitNumber, manuVisitNumber,travelVisitNumber,studyVisitNumber,winNumber,failNumber", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}