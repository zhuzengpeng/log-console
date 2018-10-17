package com.roowoo.log.modules.deptlog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.roowoo.log.common.utils.DateUtils;
import com.roowoo.log.common.utils.excel.ExportExcel;
import com.roowoo.log.modules.sys.entity.Log;
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
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.deptlog.entity.DeptLogin;
import com.roowoo.log.modules.deptlog.service.DeptLoginService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 部门登录Controller
 * @author zz
 * @version 2018-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/deptlog/deptLogin")
public class DeptLoginController extends BaseController {

	@Autowired
	private DeptLoginService deptLoginService;
	
	@ModelAttribute
	public DeptLogin get(@RequestParam(required=false) String id) {
		DeptLogin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = deptLoginService.get(id);
		}
		if (entity == null){
			entity = new DeptLogin();
		}
		return entity;
	}
	
	@RequiresPermissions("deptlog:deptLogin:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeptLogin deptLogin, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<DeptLogin> page = deptLoginService.findPage(new Page<DeptLogin>(request, response), deptLogin);
		//model.addAttribute("page", page);

        List<DeptLogin> list = Lists.newArrayList();
        List<DeptLogin> sourcelist = deptLoginService.findAll(deptLogin);
        //DeptLogin.sortList(list, sourcelist,true);
        model.addAttribute("list", sourcelist);

		return "modules/deptlog/deptLoginList";
	}



    /*@RequiresPermissions("deptlog:deptLogin:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(DeptLogin deptLogin, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "部门登录信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<DeptLogin> sourcelist = deptLoginService.findAll(deptLogin);
            //Page<User> page = systemService.findUser(new Page<User>(request, response, -1), user);
            new ExportExcel("部门登录信息", DeptLogin.class).setDataList(sourcelist).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
        return "redirect:" + adminPath + "/deptlog/deptLogin/list?repage";
    }*/


    @RequiresPermissions("deptlog:deptLogin:view")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public void exportFile(DeptLogin deptLogin, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String fileName = "simple_export_output1.xls";
        List<DeptLogin> sourcelist = deptLoginService.findAll(deptLogin);
        //准备输出流
        OutputStream os = null;
        try{
            //设置响应头
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(),"iso8859-1"));
            os = response.getOutputStream();
            List<String> headers = Arrays.asList("部门", "生产网地址", "源地址", "登录状态", "登录账户","登录时间");
            SimpleExporter exporter = new SimpleExporter();
            exporter.gridExport(headers, sourcelist, "state, productIp, sourceIp, status, account,time", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



	@RequiresPermissions("deptlog:deptLogin:view")
	@RequestMapping(value = "form")
	public String form(DeptLogin deptLogin, Model model) {
		model.addAttribute("deptLogin", deptLogin);
		return "modules/deptlog/deptLoginForm";
	}

	@RequiresPermissions("deptlog:deptLogin:edit")
	@RequestMapping(value = "save")
	public String save(DeptLogin deptLogin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, deptLogin)){
			return form(deptLogin, model);
		}
		deptLoginService.save(deptLogin);
		addMessage(redirectAttributes, "保存查看登录成功");
		return "redirect:"+Global.getAdminPath()+"/deptlog/deptLogin/?repage";
	}
	
	@RequiresPermissions("deptlog:deptLogin:edit")
	@RequestMapping(value = "delete")
	public String delete(DeptLogin deptLogin, RedirectAttributes redirectAttributes) {
		deptLoginService.delete(deptLogin);
		addMessage(redirectAttributes, "删除查看登录成功");
		return "redirect:"+Global.getAdminPath()+"/deptlog/deptLogin/?repage";
	}

}