package com.roowoo.log.modules.pie.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roowoo.log.modules.bar.entity.LoginPwdNum;
import com.roowoo.log.modules.bar.service.LoginPwdNumService;
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
import com.roowoo.log.modules.pie.entity.LoginIpuserDis;
import com.roowoo.log.modules.pie.service.LoginIpuserDisService;

import java.util.*;

/**
 * Ip登陆过的用户数分布Controller
 * @author hhy
 * @version 2018-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/pie/loginIpuserDis")
public class LoginIpuserDisController extends BaseController {

	@Autowired
	private LoginIpuserDisService loginIpuserDisService;

	@Autowired
	private LoginPwdNumService loginPwdNumService;
	
	@ModelAttribute
	public LoginIpuserDis get(@RequestParam(required=false) String id) {
		LoginIpuserDis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = loginIpuserDisService.get(id);
		}
		if (entity == null){
			entity = new LoginIpuserDis();
		}
		return entity;
	}
	
//	@RequiresPermissions("pie:loginIpuserDis:view")
	@RequestMapping(value = {"list", ""})
	@ResponseBody
	public String list(LoginIpuserDis loginIpuserDis, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<LoginIpuserDis> list=loginIpuserDisService.findAllList(loginIpuserDis);
		Map<String, Object> data = Maps.newHashMap();
		//title
		data.put("title", new HashMap<String, Object>() {
			{
//				put("text", "生产网IP登陆过的用户数分布");
				put("x", "center");
			}
		});
		//tooltip
		data.put("tooltip", new HashMap<String, Object>() {
			{
				put("trigger", "item");
				put("formatter", "{a} <br/>{b} : {c} ({d}%)");
			}
		});
		//添加legend数据
		List<String> legenddata=new ArrayList<String>();
		for (LoginIpuserDis entity :list){
			legenddata.add(entity.getId()+"的登陆数");
			legenddata.add(entity.getId()+"的IP数");
		}
		//legend
		data.put("legend",  new HashMap<String, Object>() {
			{
				put("orient", "vertical");
				put("left", "left");
				put("data", Lists.newArrayList(legenddata));
			}
		});
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "访问来源");
		seriesMap.put("type", "pie");
		seriesMap.put("radius", "45%");
		seriesMap.put("center", Lists.newArrayList("51%","56%"));
		ArrayList<HashMap<String,Object>> arrayList=new ArrayList<HashMap<String,Object>>();
		for (LoginIpuserDis entity :list){
			HashMap<String,Object> map1=new HashMap<String, Object>();
			map1.put("value", entity.getCount());
			map1.put("name", entity.getId()+"的登陆数");
			HashMap<String,Object> map2=new HashMap<String, Object>();
			map2.put("value", entity.getNum());
			map2.put("name", entity.getId()+"的IP数");
			arrayList.add(map1);
			arrayList.add(map2);
		}
		seriesMap.put("data", Lists.newArrayList(arrayList));
		seriesMap.put("itemStyle",
				new HashMap<String, Object>() {
					{
						put("emphasis",
								new HashMap<String, Object>() {
									{
										put("shadowBlur", 10);
										put("shadowOffsetX", 0);
										put("shadowColor", "rgba(0, 0, 0, 0.5)");
									}
								}
						);
					}
				}
		);
		data.put("series", Lists.newArrayList(seriesMap));
		String result = JSON.toJSONString(data);
		logger.info(result);
		return result;
	}

//	@RequiresPermissions("pie:loginIpuserDis:view")
	@RequestMapping(value = "form")
	public String form(LoginIpuserDis loginIpuserDis, Model model) {
		model.addAttribute("loginIpuserDis", loginIpuserDis);
		return "modules/pie/loginIpuserDisForm";
	}


//	@RequestMapping(value = "data")
//	@ResponseBody
//	public String getdata(LoginIpuserDis loginIpuserDis){
//        List<LoginIpuserDis> list=loginIpuserDisService.findAllList(loginIpuserDis);
//		List<Long> data=new ArrayList<>();
//		List<Long> lodinNum=new ArrayList<>();
//        List<Long> IPNum=new ArrayList<>();
//
//		long loginSum=0;
//        long IPSum=0;
//		for (LoginIpuserDis num1:list){
//			loginSum +=Integer.parseInt(num1.getCount());
//			lodinNum.add(Long.valueOf(num1.getCount()));
//			IPSum += Integer.parseInt(num1.getNum());
//            IPNum.add(Long.valueOf(num1.getNum()));
//		}
//        Collections.sort(lodinNum);
//        Collections.sort(IPNum);
//        long maxLodinNum= lodinNum.get(lodinNum.size() - 1);
//        long maxIPNum=IPNum.get(IPNum.size()-1);
//        data.add(loginSum);
//        data.add(IPSum);
//        data.add(maxLodinNum);
//		data.add(maxIPNum);
//        String result = JSON.toJSONString(data);
//        logger.info(result);
//        return result;
//	}
}