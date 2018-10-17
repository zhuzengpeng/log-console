package com.roowoo.log.modules.count3.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.count3.entity.Count3l;
import com.roowoo.log.modules.count3.service.Count3lService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * count3Controller
 * @author liujiale
 * @version 2018-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/count3/count3l")
public class Count3lController extends BaseController {

	@Autowired
	private Count3lService count3lService;

	@ModelAttribute
	public Count3l get(@RequestParam(required = false) String id) {
		Count3l entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = count3lService.get(id);
		}
		if (entity == null) {
			entity = new Count3l();
		}
		return entity;
	}
	@RequestMapping(value = "echarts")
	public String echarts1() {
		return "modules/count3/echarts5";
	}

	@RequestMapping(value ="getEcharts3")
	@ResponseBody
	public String list(Count3l count3l, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Count3l> page = count3lService.findPage(new Page<Count3l>(request, response), count3l);
		List<Count3l>list = page.getList();
		List ls = new ArrayList();
		List ls1 = new ArrayList();
		List ls2 = new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getWeek());
			ls1.add(list.get(i).getFailcount());
			ls2.add(list.get(i).getSuccesscount());
		}
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
//		titleMap.put("text", "每周登陆情况分布");
//		titleMap.put("left","center");
//		data.put("title", titleMap);
		//tooltip
		data.put("tooltip", Maps.newHashMap());
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data", "销量");
		data.put("legend",  legendMap);
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("data",ls);
		Map<String, Object> axisLabelMap = Maps.newHashMap();
		axisLabelMap.put("inside", false);
//		axisLabelMap.put("interval",0);
//		axisLabelMap.put("rotate",40);
		xAxisMap.put("axisLabel", axisLabelMap);
		data.put("xAxis",  xAxisMap);
		//yAxis
		Map<String, Object> yAxisMap = Maps.newHashMap();
		Map<String, Object> yaxisLabelMap = Maps.newHashMap();
//		yaxisLabelMap.put("inside", false);
		yaxisLabelMap.put("interval",0);
		yaxisLabelMap.put("rotate",40);
		yAxisMap.put("axisLabel", yaxisLabelMap);
		data.put("yAxis",  yAxisMap);
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "登陆成功");
		seriesMap.put("type", "bar");

		seriesMap.put("data",ls2);
		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "登陆失败");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data",ls1);

		data.put("series", Lists.newArrayList(seriesMap,seriesMap1));
		ArrayList<String> color=new ArrayList<>();
		color.add("#8ecb9b");
		color.add("#83d0f4");
		data.put("color",color);

		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

}