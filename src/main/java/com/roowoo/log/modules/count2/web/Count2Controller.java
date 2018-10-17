package com.roowoo.log.modules.count2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roowoo.log.modules.count1.entity.Count1l;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.count2.entity.Count2;
import com.roowoo.log.modules.count2.service.Count2Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * count2Controller
 * @author liu
 * @version 2018-09-27
 */
@Controller
@RequestMapping(value = "${adminPath}/count2/count2")
public class Count2Controller extends BaseController {

	@Autowired
	private Count2Service count2Service;


	@ModelAttribute
	public Count2 get(@RequestParam(required=false) String id) {
		Count2 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = count2Service.get(id);
		}
		if (entity == null){
			entity = new Count2();
		}
		return entity;
	}

	@RequestMapping(value = "echarts")
	public String echarts1() {
		return "modules/count2/echarts4";
	}

	@RequestMapping(value = "getEcharts3")
	@ResponseBody
	public String list(Count2 count2, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*Page<Count2> page = count2Service.findPage(new Page<Count2>(request, response), count2);*/
		List<Count2> list= count2Service.findListByParentId1();
		List ls=new ArrayList();
		List ls1=new ArrayList();
		List ls2=new ArrayList();
		List ls3=new ArrayList();
		List ls4=new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getDepartId());
			ls1.add(list.get(i).getFaceload());
			ls2.add(list.get(i).getFingerprintload());
			ls3.add(list.get(i).getiPhoneload());
			ls4.add(list.get(i).getPCload());
		}
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "1级账户登陆方式成功失败统计");
		titleMap.put("left","center");
		data.put("title", titleMap);
		//tooltip
		Map<String, Object> tooltipMap = Maps.newHashMap();
		tooltipMap.put("trigger","axis");

Map<String, Object> axisPointer = Maps.newHashMap();
		axisPointer.put("type","shadow");
		tooltipMap.put("axisPointer",axisPointer);

		data.put("tooltip", tooltipMap);
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data", Lists.newArrayList("成功","失败"));
		legendMap.put("show","true");
		data.put("legend",  legendMap);
		//grid
		Map<String, Object> gridMap = Maps.newHashMap();
		gridMap.put("left","100");

		data.put("grid",gridMap);
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("type","value");
		xAxisMap.put("boundaryGap","[0, 0.01]");
		data.put("xAxis",xAxisMap);
		Map<String, Object> axisLabelMap = Maps.newHashMap();
		axisLabelMap.put("inside", false);
		xAxisMap.put("axisLabel", axisLabelMap);

		data.put("xAxis",  xAxisMap);
		//yAxis
		Map<String, Object> yAxisMap = Maps.newHashMap();
		yAxisMap.put("type","category");
		/*yAxisMap.put("data",ls);*/
		yAxisMap.put("data",ls);
		data.put("yAxis",  yAxisMap);
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "人脸识别");
		seriesMap.put("type", "bar");
		seriesMap.put("data",ls1);

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "指纹登陆");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data",ls2);

		Map<String, Object> seriesMap2 = Maps.newHashMap();
		seriesMap2.put("name", "电话登陆");
		seriesMap2.put("type", "bar");
		seriesMap2.put("data",ls3);

		Map<String, Object> seriesMap3 = Maps.newHashMap();
		seriesMap3.put("name", "手机登陆");
		seriesMap3.put("type", "bar");
		seriesMap3.put("data",ls4);

		data.put("series", Lists.newArrayList(seriesMap,seriesMap1,seriesMap2,seriesMap3));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

	@RequestMapping(value = "getEcharts4")
	@ResponseBody
	public String getNameByCategory(Count2 count1l, HttpServletRequest request, HttpServletResponse response, Model model){
		String result = "";
		String departid=count1l.getDepartId();
		List<Count2> list = count2Service.findListByParentIdSon1(departid);
		List ls=new ArrayList();
		List ls1=new ArrayList();
		List ls2=new ArrayList();
		List ls3=new ArrayList();
		List ls4=new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getDepartId());
			ls1.add(list.get(i).getFaceload());
			ls2.add(list.get(i).getFingerprintload());
			ls3.add(list.get(i).getiPhoneload());
			ls4.add(list.get(i).getPCload());
		}
		if(departid != null &&"1001".equals(departid)){
			result = getWayCommon(ls ,ls1,ls2,ls3,ls4);
		}
		if(departid != null &&"1002".equals(departid)){
			result = getWayCommon(ls ,ls1,ls2,ls3,ls4);
		}
		if(departid != null &&"1003".equals(departid)){
			result = getWayCommon(ls ,ls1,ls2,ls3,ls4);
		}
		if(departid != null &&"1004".equals(departid)){
			result = getWayCommon(ls ,ls1,ls2,ls3,ls4);
		}
		return result;
	}


	public String getWayCommon( List  ls,List ls1,List ls2,List ls3, List ls4){
		Map<String, Object> data = Maps.newHashMap();
		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		titleMap.put("text", "2级账户登陆方式成功失败统计");
		titleMap.put("left","center");
		data.put("title", titleMap);
		//tooltip
		Map<String, Object> tooltipMap = Maps.newHashMap();
		tooltipMap.put("trigger","axis");

		Map<String, Object> axisPointer = Maps.newHashMap();
		axisPointer.put("type","shadow");
		tooltipMap.put("axisPointer",axisPointer);

		data.put("tooltip", tooltipMap);
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		legendMap.put("data", Lists.newArrayList("成功","失败"));
		legendMap.put("show","true");
		data.put("legend",  legendMap);
		//grid
		Map<String, Object> gridMap = Maps.newHashMap();
		gridMap.put("left","100");

		data.put("grid",gridMap);
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("type","value");
		xAxisMap.put("boundaryGap","[0, 0.01]");
		data.put("xAxis",xAxisMap);
		Map<String, Object> axisLabelMap = Maps.newHashMap();
		axisLabelMap.put("inside", false);
		xAxisMap.put("axisLabel", axisLabelMap);

		data.put("xAxis",  xAxisMap);
		//yAxis
		Map<String, Object> yAxisMap = Maps.newHashMap();
		yAxisMap.put("type","category");
		/*yAxisMap.put("data",ls);*/
		yAxisMap.put("data",ls);
		data.put("yAxis",  yAxisMap);
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "人脸识别");
		seriesMap.put("type", "bar");
		seriesMap.put("data",ls1);

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "指纹登陆");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data",ls2);

		Map<String, Object> seriesMap2 = Maps.newHashMap();
		seriesMap2.put("name", "电话登陆");
		seriesMap2.put("type", "bar");
		seriesMap2.put("data",ls3);

		Map<String, Object> seriesMap3 = Maps.newHashMap();
		seriesMap3.put("name", "手机登陆");
		seriesMap3.put("type", "bar");
		seriesMap3.put("data",ls4);

		data.put("series", Lists.newArrayList(seriesMap,seriesMap1,seriesMap2,seriesMap3));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

}



