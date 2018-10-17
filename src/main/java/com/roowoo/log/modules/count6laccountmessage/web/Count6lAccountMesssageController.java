package com.roowoo.log.modules.count6laccountmessage.web;

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
import com.roowoo.log.modules.count6laccountmessage.entity.Count6lAccountMesssage;
import com.roowoo.log.modules.count6laccountmessage.service.Count6lAccountMesssageService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * count6laccountmessageController
 * @author liujiale
 * @version 2018-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/count6laccountmessage/count6lAccountMesssage")
public class Count6lAccountMesssageController extends BaseController {

	@Autowired
	private Count6lAccountMesssageService count6lAccountMesssageService;
	
	@ModelAttribute
	public Count6lAccountMesssage get(@RequestParam(required=false) String id) {
		Count6lAccountMesssage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = count6lAccountMesssageService.get(id);
		}
		if (entity == null){
			entity = new Count6lAccountMesssage();
		}
		return entity;
	}

	@RequestMapping(value = {"list", ""})
	public String list(Count6lAccountMesssage count6lAccountMesssage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Count6lAccountMesssage> page = count6lAccountMesssageService.findPage(new Page<Count6lAccountMesssage>(request, response), count6lAccountMesssage); 
		model.addAttribute("page", page);
		return "modules/count6laccountmessage/count6lAccountMesssageList";
	}


//    @RequiresPermissions("count6laccountmessage:count6lAccountMesssage:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public void exportFile(Count6lAccountMesssage count6lAccountMesssage, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String fileName = "simple_export_output1.xls";
        Page<Count6lAccountMesssage> page = count6lAccountMesssageService.findPage(new Page<Count6lAccountMesssage>(request, response), count6lAccountMesssage);
        //准备输出流
        OutputStream os = null;
        try{
            //设置响应头
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(),"iso8859-1"));
            os = response.getOutputStream();
            List<String> headers = Arrays.asList("账户", "所属部门", "登陆时间", "登陆状态", "源IP地址","目标IP地址");
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, page.getList(), "account, depart, loadDate, loadState, sourceAddress,targetAddress", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





	/*@RequiresPermissions("count6laccountmessage:count6lAccountMesssage:view")
	@RequestMapping(value = "form")
	public String form(Count6lAccountMesssage count6lAccountMesssage, Model model) {
		model.addAttribute("count6lAccountMesssage", count6lAccountMesssage);
		return "modules/count6laccountmessage/count6lAccountMesssageForm";
	}

	@RequiresPermissions("count6laccountmessage:count6lAccountMesssage:edit")
	@RequestMapping(value = "save")
	public String save(Count6lAccountMesssage count6lAccountMesssage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, count6lAccountMesssage)){
			return form(count6lAccountMesssage, model);
		}
		count6lAccountMesssageService.save(count6lAccountMesssage);
		addMessage(redirectAttributes, "保存count6laccountmessage成功");
		return "redirect:"+Global.getAdminPath()+"/count6laccountmessage/count6lAccountMesssage/?repage";
	}
	
	@RequiresPermissions("count6laccountmessage:count6lAccountMesssage:edit")
	@RequestMapping(value = "delete")
	public String delete(Count6lAccountMesssage count6lAccountMesssage, RedirectAttributes redirectAttributes) {
		count6lAccountMesssageService.delete(count6lAccountMesssage);
		addMessage(redirectAttributes, "删除count6laccountmessage成功");
		return "redirect:"+Global.getAdminPath()+"/count6laccountmessage/count6lAccountMesssage/?repage";
	}
*/
}