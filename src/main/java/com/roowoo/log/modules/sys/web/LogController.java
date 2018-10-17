package com.roowoo.log.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.modules.sys.service.LogService;
import com.roowoo.log.modules.sys.entity.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jxls.template.SimpleExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log);
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

	@RequestMapping(value = "export", method= RequestMethod.POST)
	public void exportFile(Log log, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String fileName = "simple_export_output1.xls";
		List<Log> list = logService.findList(log);
		//准备输出流
		OutputStream os = null;
		try{
			//设置响应头
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(),"iso8859-1"));
			os = response.getOutputStream();
			List<String> headers = Arrays.asList("操作菜单", "URI", "提交方式", "操作者IP", "操作时间");
			SimpleExporter exporter = new SimpleExporter();
			exporter.gridExport(headers, list, "title, requestUri, method, remoteAddr, createDate", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
