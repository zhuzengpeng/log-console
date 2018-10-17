package com.roowoo.log.modules.elsedata.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roowoo.log.modules.elsedata.entity.LoginSexDis;
import com.roowoo.log.modules.elsedata.entity.LoginSexNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.modules.elsedata.entity.LoginAccountNum;
import com.roowoo.log.modules.elsedata.service.LoginAccountNumService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 账户登陆次数统计Controller
 * @author hhy
 * @version 2018-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/elsedata/loginAccountNum")
public class LoginAccountNumController<test> extends BaseController {

	@Autowired
	private LoginAccountNumService loginAccountNumService;

	@RequestMapping(value = {"list", ""})
	public String list(LoginAccountNum loginAccountNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/elsedata/echarts3";
	}
	@RequestMapping(value = "errorData")
	@ResponseBody
	public String errorData(LoginAccountNum loginAccountNum){
		List<LoginAccountNum> list=loginAccountNumService.findList(loginAccountNum);
		List<LoginAccountNum> list1=loginAccountNumService.findAllList(loginAccountNum);
		ArrayList<Object> arrayList=new ArrayList<>();
		ArrayList<Object> arrayList1=new ArrayList<>();
		for (LoginAccountNum a :list){
			ArrayList<Object> data=new ArrayList<Object>();
			data.add(a.getNum());
			data.add(a.getCount());
			data.add("成功次数："+a.getCount());
			arrayList.add(data);
		}

		for (LoginAccountNum a :list1){
			ArrayList<Object> data=new ArrayList<Object>();
			data.add(a.getNum());
			data.add(a.getCount());
			data.add("失败次数："+a.getCount());
			arrayList1.add(data);
		}




		Map<String, Object> data = Maps.newHashMap();

		//title
		Map<String, Object> titleMap = Maps.newHashMap();
		Map<String, Object> subtextStyleMap = Maps.newHashMap();
//		titleMap.put("text", "员工登陆成功失败的次数");
		titleMap.put("subtext","横轴:ip号；纵轴：次数");
		subtextStyleMap.put("left","left");
		titleMap.put("subtextStyle",subtextStyleMap);
//		titleMap.put("left","center");
		data.put("title", titleMap);
		//lineStyle
		Map<String, Object> lineStyleMap= Maps.newHashMap();
		lineStyleMap.put("type","dashed");
		//splitLine
		Map<String, Object> splitLineMap= Maps.newHashMap();
		splitLineMap.put("lineStyle",lineStyleMap);
		//legend
		Map<String, Object> legendMap = Maps.newHashMap();
		ArrayList<String> statusList=new ArrayList<String>();
		statusList.add("成功");
		statusList.add("失败");
		legendMap.put("data", statusList);
		legendMap.put("right",10);
		data.put("legend",  legendMap);
		//xAxis
		Map<String, Object> xAxisMap = Maps.newHashMap();
		xAxisMap.put("splitLine",splitLineMap);
		xAxisMap.put("scale",true);
		data.put("xAxis",  xAxisMap);
		//yAxis
		Map<String, Object> yAxisMap = Maps.newHashMap();
		yAxisMap.put("splitLine",splitLineMap);
		yAxisMap.put("scale",true);
		data.put("yAxis",  yAxisMap);
		//series
		List<Map<String, Object>> seriesList =new ArrayList<Map<String, Object>>();

		Map<String, Object> seriesMap1 = Maps.newHashMap();
		seriesMap1.put("name", "成功");
		seriesMap1.put("type", "scatter");
		seriesMap1.put("data", arrayList);
		//lebel
		Map<String, Object> lebelMap = Maps.newHashMap();
		Map<String, Object> emphasisMap = Maps.newHashMap();
		emphasisMap.put("show",true);
		emphasisMap.put("position","top");
		lebelMap.put("emphasis",emphasisMap);
		seriesMap1.put("label",lebelMap);
		//itemStyle
		Map<String, Object> itemStyleMap1 = Maps.newHashMap();
		itemStyleMap1.put("shadowBlur",10);
		itemStyleMap1.put("shadowColor","rgba(120,36,50,0.5)");
		itemStyleMap1.put("shadowOffsetY",5);
		itemStyleMap1.put("color","rgb(204, 46, 72)");
		seriesMap1.put("itemStyle",itemStyleMap1);

		Map<String, Object> seriesMap2 = Maps.newHashMap();
		seriesMap2.put("name", "失败");
		seriesMap2.put("type", "scatter");
		seriesMap2.put("data", arrayList1);
		//lebel
		Map<String, Object> lebelMap1 = Maps.newHashMap();
		Map<String, Object> emphasisMap1 = Maps.newHashMap();
		emphasisMap1.put("show",true);
		emphasisMap1.put("position","top");
		lebelMap1.put("emphasis",emphasisMap1);
		seriesMap2.put("label",lebelMap1);

		Map<String, Object> itemStyleMap2 = Maps.newHashMap();
		itemStyleMap2.put("shadowBlur",10);
		itemStyleMap2.put("shadowColor","rgba(120,36,50,0.5)");
		itemStyleMap2.put("shadowOffsetY",5);
		itemStyleMap2.put("color","rgb(25, 183, 207)");
		seriesMap2.put("itemStyle",itemStyleMap2);

		seriesMap1.put("symbolSize",30);
		seriesMap2.put("symbolSize",30);

		seriesList.add(seriesMap1);
		seriesList.add(seriesMap2);

		data.put("series", Lists.newArrayList(seriesList));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

	@RequestMapping(value = "getSexNum")
	@ResponseBody
	public String getSexNum(LoginSexNum loginSexNum, Model model){
		List<LoginSexNum> list=loginAccountNumService.findSexdata(loginSexNum);
		ArrayList<Object> data=new ArrayList<>();
		for (LoginSexNum num:list){
			ArrayList<Object> data1=new ArrayList<>();
			String value="男生登陆数量:"+num.getManNum()+"; 女生登陆数量："+num.getWomanNum()+".";
			data1.add(num.getLongitude());
			data1.add(num.getLatitude());
			data1.add(num.getCity());
			data1.add(num.getCity()+"的"+value);
			data.add(data1);
		}
		String result = JSON.toJSONString(data);
		return result;
	}

	@RequestMapping(value = "getSexNum1")
	@ResponseBody
	public String getSexNum1(LoginSexNum loginSexNum, Model model){
		List<LoginSexNum> list=loginAccountNumService.findSexdata(loginSexNum);
		ArrayList<Object> data=new ArrayList<>();
		for (LoginSexNum num:list){
			Long all=num.getManNum()+num.getWomanNum();
			ArrayList<Object> data1=new ArrayList<>();
			data1.add(num.getLongitude());
			data1.add(num.getLatitude());
			data1.add(num.getCity());
			data1.add(num.getManNum());
			data1.add(num.getWomanNum());
			data1.add(all);
			data.add(data1);
		}
		String result = JSON.toJSONString(data);
		return result;
	}

	@RequestMapping(value = "getSexNum2")
	@ResponseBody
	public String getSexNum2(LoginSexDis loginSexDis){
		List<LoginSexDis> list=loginAccountNumService.findAccountSexdata(loginSexDis);
		ArrayList<Object> dataz=new ArrayList<>();
		for (LoginSexDis num:list){
			ArrayList<Object> data1=new ArrayList<>();
			data1.add(num.getLongitude());
			data1.add(num.getLatitude());
			data1.add(num.getCity());
			data1.add(num.getManNum());
			data1.add(num.getWomanNum());
			data1.add(num.getAllNum());
			dataz.add(data1);
		}
		String result = JSON.toJSONString(dataz);
		return result;
	}



	@RequestMapping(value = "echarts")
	public String echarts(LoginAccountNum loginAccountNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/elsedata/echarts";
	}

//	@RequestMapping(value = "getdata")
//	@ResponseBody
//	public String getdata(LoginAccountNum loginAccountNum) {
//		List<LoginAccountNum> list = loginAccountNumService.findList(loginAccountNum);
//		List<LoginAccountNum> list1 = loginAccountNumService.findAllList(loginAccountNum);
//		List<Long> data=new ArrayList<>();
//		List<Long> errorNum=new ArrayList<>();
//		List<Long> winNum=new ArrayList<>();
//		long errorSum=0;
//		long winSum=0;
//		for (LoginAccountNum a :list){
//			winSum +=Integer.parseInt(a.getCount());
//			winNum.add(Long.valueOf(a.getCount()));
//		}
//		for (LoginAccountNum a :list1){
//			errorSum +=Integer.parseInt(a.getCount());
//			errorNum.add(Long.valueOf(a.getCount()));
//		}
//		Collections.sort(errorNum);
//		Collections.sort(winNum);
//		long maxErrorNum= errorNum.get(errorNum.size() - 1);
//		long maxWinNum=winNum.get(winNum.size()-1);
//		data.add(winSum);
//		data.add(errorSum);
//		data.add(maxWinNum);
//		data.add(maxErrorNum);
//		String result = JSON.toJSONString(data);
//		logger.info(result);
//		return result;
//	}

	@RequestMapping(value = "getLoginNum")
	@ResponseBody
	public String getLoginNum(LoginSexNum loginSexNum, Model model) {
		List<LoginSexNum> list = loginAccountNumService.findSexdata(loginSexNum);
		ArrayList<Object> data = new ArrayList<>();
		long ctLoginSum=0;
		List<Long> LoginNum=new ArrayList<>();
		for (LoginSexNum num:list){
			ctLoginSum +=num.getManNum();
			ctLoginSum +=num.getWomanNum();
			long a=num.getManNum()+num.getWomanNum();
			LoginNum.add(a);
		}
		Collections.sort(LoginNum);
		long loginNumMax= LoginNum.get(LoginNum.size() - 1);
		data.add(loginNumMax);
		data.add(ctLoginSum);
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

	@RequestMapping(value = "getAccountNum")
	@ResponseBody
	public String getAccountNum(LoginSexDis loginSexDis) {
		List<LoginSexDis> list=loginAccountNumService.findAccountSexdata(loginSexDis);
		ArrayList<Object> data = new ArrayList<>();
		long ctAccountSum=0;
		List<Long> AccountNum=new ArrayList<>();
		for (LoginSexDis num:list){
			ctAccountSum +=num.getAllNum();
			AccountNum.add(num.getAllNum());
		}
		Collections.sort(AccountNum);
		long accountNumMax= AccountNum.get(AccountNum.size() - 1);
		data.add(accountNumMax);
		data.add(ctAccountSum);
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}
}