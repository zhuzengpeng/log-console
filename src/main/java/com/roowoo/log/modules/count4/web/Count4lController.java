package com.roowoo.log.modules.count4.web;

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
import com.roowoo.log.modules.count4.entity.Count4l;
import com.roowoo.log.modules.count4.service.Count4lService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * count4Controller
 * @author liujaile
 * @version 2018-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/count4/count4l")
public class Count4lController extends BaseController {

	@Autowired
	private Count4lService count4lService;
	@ModelAttribute
	public Count4l get(@RequestParam(required=false) String id) {
		Count4l entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = count4lService.get(id);
		}
		if (entity == null){
			entity = new Count4l();
		}
		return entity;
	}

	@RequestMapping(value = "echarts")
	public String echarts1() {
		return "modules/count4/echarts6";
	}


	@RequestMapping(value ="getEcharts3")
	@ResponseBody
	public String list(Count4l count4l, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Count4l> page = count4lService.findPage(new Page<Count4l>(request, response), count4l);
		List<Count4l> list=page.getList();
		List ls = new ArrayList();
		List lsway = new ArrayList();
		List lscount = new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getWeekly());
			lsway.add(list.get(i).getLoadfail());
			lscount.add(list.get(i).getLoadsuccess());
		}
		Map<String, Object> data = Maps.newHashMap();
		//title
//		Map<String, Object> titleMap = Maps.newHashMap();
//		titleMap.put("text", "每周登陆成功失败情况百分比");
//		titleMap.put("left","center");
//		data.put("title", titleMap);
		//tooltip
		Map<String, Object> tooltip = Maps.newHashMap();
		tooltip.put("formatter","{c}%");
		data.put("tooltip",tooltip);

		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data","['成功'，'失败']" );
		data.put("legend",  legendMap);
		//lable
		Map<String, Object> lable = Maps.newHashMap();
		lable.put("show","true");
		lable.put("position","top");
		//lable.put("formatter","{b}\\n{c}%");
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("data",ls);
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
		seriesMap.put("data",lscount);

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "登陆失败");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data",lsway);
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