package com.roowoo.log.modules.bar.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roowoo.log.modules.elsedata.entity.LoginAccountNum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roowoo.log.config.Global;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.bar.entity.LoginPwdNum;
import com.roowoo.log.modules.bar.service.LoginPwdNumService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 一个密码被错误输入的次数Controller
 * @author hhy
 * @version 2018-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bar/loginPwdNum")
public class LoginPwdNumController extends BaseController {

	@Autowired
	private LoginPwdNumService loginPwdNumService;
	
	@ModelAttribute
	public LoginPwdNum get(@RequestParam(required=false) String id) {
		LoginPwdNum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = loginPwdNumService.get(id);
		}
		if (entity == null){
			entity = new LoginPwdNum();
		}
		return entity;
	}
	
//	@RequiresPermissions("bar:loginPwdNum:view")
	@RequestMapping(value = {"list", ""})
	@ResponseBody
	public String list(LoginPwdNum loginPwdNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<LoginPwdNum> list=loginPwdNumService.findAllList(loginPwdNum);


		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "一个密码被错误输入的次数");
		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		ArrayList<String> al1=new ArrayList<String>();
		al1.add("错误次数");
		al1.add("密码数量");
		legendMap.put("data", al1);
		data.put("legend",  legendMap);

		ArrayList<String> al2=new ArrayList<String>();
		ArrayList<String> countData=new ArrayList<String>();
		ArrayList<String> numData=new ArrayList<String>();
		for (LoginPwdNum entity :list){
			al2.add("第"+entity.getId()+"天");
			countData.add(entity.getCount());
			numData.add(entity.getNum());
		}

		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("type","category");
		xAxisMap.put("data", Lists.newArrayList(al2));

		data.put("xAxis",  xAxisMap);
		//yAxis
		data.put("yAxis",  Maps.newHashMap());
		//series
		List<Map<String, Object>> seriesList =new ArrayList<Map<String, Object>>();

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "错误次数");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data", Lists.newArrayList(countData));

		Map<String, Object> seriesMap2 = Maps.newHashMap();
		seriesMap2.put("name", "密码数量");
		seriesMap2.put("type", "bar");
		seriesMap2.put("data", Lists.newArrayList(numData));

		seriesList.add(seriesMap1);
		seriesList.add(seriesMap2);

		data.put("series", Lists.newArrayList(seriesList));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

	@RequestMapping(value = "form")
	@ResponseBody
	public String form(LoginPwdNum loginPwdNum, Model model) {
		List<LoginPwdNum> list=loginPwdNumService.findAllList(loginPwdNum);
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "一个账户登陆失败的次数");
		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		ArrayList<String> al1=new ArrayList<String>();
		al1.add("次数");
		legendMap.put("data", al1);
		data.put("legend",  legendMap);

		ArrayList<String> al2=new ArrayList<String>();
		ArrayList<String> countData=new ArrayList<String>();
		for (LoginPwdNum entity :list){
			al2.add("第"+entity.getId()+"个账户");
			countData.add(entity.getCount());
		}
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("type","category");
		xAxisMap.put("data", Lists.newArrayList(al2));

		data.put("xAxis",  xAxisMap);
		//yAxis
		data.put("yAxis",  Maps.newHashMap());
		//series
		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "次数");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data", Lists.newArrayList(countData));
		data.put("series", Lists.newArrayList(seriesMap1));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

	@RequestMapping(value = "allLoginData")
	@ResponseBody
	public String allLoginData(LoginPwdNum loginPwdNum){
		List<LoginPwdNum> list=loginPwdNumService.findAllList(loginPwdNum);
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "员工登陆成功失败统计");
		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		ArrayList<String> al1=new ArrayList<String>();
		al1.add("成功次数");
		al1.add("失败次数");
		legendMap.put("data", al1);
		data.put("legend",  legendMap);

		ArrayList<String> al2=new ArrayList<String>();
		ArrayList<String> countData=new ArrayList<String>();
		ArrayList<String> numData=new ArrayList<String>();
		for (LoginPwdNum entity :list){
			al2.add(entity.getId()+"号员工");
			countData.add(entity.getCount());
			numData.add(entity.getNum());
		}

		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("type","category");
		xAxisMap.put("data", Lists.newArrayList(al2));

		data.put("xAxis",  xAxisMap);
		//yAxis
		data.put("yAxis",  Maps.newHashMap());
		//series
		List<Map<String, Object>> seriesList =new ArrayList<Map<String, Object>>();

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "成功次数");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data", Lists.newArrayList(countData));

		Map<String, Object> seriesMap2 = Maps.newHashMap();
		seriesMap2.put("name", "失败次数");
		seriesMap2.put("type", "bar");
		seriesMap2.put("data", Lists.newArrayList(numData));

		seriesList.add(seriesMap1);
		seriesList.add(seriesMap2);

		data.put("series", Lists.newArrayList(seriesList));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

}