package com.roowoo.log.modules.count1.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
import com.roowoo.log.modules.count1.entity.Count1l;
import com.roowoo.log.modules.count1.service.Count1lService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * count1Controller
 * @author 刘家乐
 * @version 2018-09-22
 */

@Controller
@RequestMapping(value = "${adminPath}/count1/count1l")
public class Count1lController extends BaseController {

	@Autowired
	private Count1lService count1lService;
	
	@ModelAttribute
	public Count1l get(@RequestParam(required=false) String id) {
		Count1l entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = count1lService.get(id);
		}
		if (entity == null){
			entity = new Count1l();
		}
		return entity;
	}

	@RequestMapping(value = "echarts")
	public String echarts1() {
		return "modules/count1/echarts3";
	}
	
	public  String   findListByParentId(){
		count1lService.findListByParentId();
		return "";
	}

	@RequestMapping(value = "getEcharts3")
	@ResponseBody
	public String list(Count1l count1l, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<Count1l> page = count1lService.findPage(new Page<Count1l>(request, response), count1l);

		List<Count1l> list=page.getList();*/
		List<Count1l> list =count1lService.findListByParentId();
		List ls=new ArrayList();
		List ls1=new ArrayList();
		List ls2=new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getDepartId());
			ls1.add(list.get(i).getSuccesscount());
			ls2.add(list.get(i).getFailcount());
		}
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "1级账户登陆成功失败统计");
		titleMap.put("left","center");
		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		/*//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data", Lists.newArrayList("成功","失败"));
		legendMap.put("show","true");
		data.put("legend",  legendMap);*/
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("data",ls);
		xAxisMap.put("formatter","function(params)");
		Map<String, Object> axisLabelMap = Maps.newHashMap();
		axisLabelMap.put("inside", false);
		xAxisMap.put("axisLabel", axisLabelMap);
		data.put("xAxis",  xAxisMap);
		//yAxis
		data.put("yAxis",  Maps.newHashMap());
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "登陆成功");
		seriesMap.put("type", "bar");
		/*seriesMap.put("data", Lists.newArrayList(5, 20, 36, 10, 10, 20));*/
		seriesMap.put("data",ls1);

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "登陆失败");
		seriesMap1.put("type", "bar");
		/*seriesMap1.put("data", Lists.newArrayList(5, 20, 36, 10, 10, 20));*/
		seriesMap1.put("data",ls2);

		data.put("series", Lists.newArrayList(seriesMap,seriesMap1));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;


	}

	@RequestMapping(value = "getEcharts4")
	@ResponseBody
	public String getNameByCategory(Count1l count1l, HttpServletRequest request, HttpServletResponse response, Model model){
		String result = "";
		String departid=count1l.getDepartId();
		List<Count1l> list = count1lService.findListByParentIdSon(departid);
		List ls=new ArrayList();
		List ls1=new ArrayList();
		List ls2=new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getDepartId());
			ls1.add(list.get(i).getSuccesscount());
			ls2.add(list.get(i).getFailcount());
		}
		if(departid != null &&"1001".equals(departid)){
			result=getWayCommon(ls ,ls1,ls2);
		}
		if(departid != null &&"1002".equals(departid)){
			result=getWayCommon(ls,ls1,ls2);
		}
		if(departid != null &&"1003".equals(departid)){
			result=getWayCommon(ls,ls1,ls2);
		}
		if(departid != null &&"1004".equals(departid)){
			result=getWayCommon(ls,ls1,ls2);
		}
			return result;
	}


	public String getWayCommon(List ls,List ls1,List ls2){
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "1级部门下账户登陆成功失败统计");
		titleMap.put("left","center");
		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		/*//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data", Lists.newArrayList("成功","失败"));
		legendMap.put("show","true");
		data.put("legend",  legendMap);*/
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("data",ls);
		xAxisMap.put("formatter","function(params)");
		Map<String, Object> axisLabelMap = Maps.newHashMap();
		axisLabelMap.put("inside", false);
		xAxisMap.put("axisLabel", axisLabelMap);
		data.put("xAxis",  xAxisMap);
		//yAxis
		data.put("yAxis",  Maps.newHashMap());
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "登陆成功");
		seriesMap.put("type", "bar");
		/*seriesMap.put("data", Lists.newArrayList(5, 20, 36, 10, 10, 20));*/
		seriesMap.put("data",ls1);

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "登陆失败");
		seriesMap1.put("type", "bar");
		/*seriesMap1.put("data", Lists.newArrayList(5, 20, 36, 10, 10, 20));*/
		seriesMap1.put("data",ls2);

		data.put("series", Lists.newArrayList(seriesMap,seriesMap1));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

}