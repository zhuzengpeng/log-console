package com.roowoo.log.modules.count5.web;

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
import com.roowoo.log.modules.count5.entity.Count5l;
import com.roowoo.log.modules.count5.service.Count5lService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * count5Controller
 * @author liujiale
 * @version 2018-09-25
 */
@Controller
@RequestMapping(value = "${adminPath}/count5/count5l")
public class Count5lController extends BaseController {

	@Autowired
	private Count5lService count5lService;
	
	@ModelAttribute
	public Count5l get(@RequestParam(required=false) String id) {
		Count5l entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = count5lService.get(id);
		}
		if (entity == null){
			entity = new Count5l();
		}
		return entity;
	}

	@RequestMapping(value = "echarts")
	public String echarts1() {
		return "modules/count5/echarts7";
	}

	@RequestMapping(value ="getEcharts3")
	@ResponseBody
	public String list(Count5l count5l, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Count5l> page = count5lService.findPage(new Page<Count5l>(request, response), count5l); 
		List<Count5l> list = page.getList();
		List ls = new ArrayList();
		List lsway = new ArrayList();
		for (int i=0;i<list.size();i++){
			ls.add(list.get(i).getIp());
			lsway.add(list.get(i).getLoadcount());
		}
		Map<String, Object> data = Maps.newHashMap();
		//title
		data.put("title", new HashMap<String, Object>() {
			{
				put("text", "生产网终端关联网");
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

		//legend
		data.put("legend",  new HashMap<String, Object>() {
			{
				put("orient", "vertical");
				put("left", "left");
				put("data", ls );
			}
		});
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "访问来源");
		seriesMap.put("type", "pie");
		seriesMap.put("radius", "55%");
		seriesMap.put("center", Lists.newArrayList("50%","60%"));
		ArrayList<Map<String,Object>> ll=new ArrayList<Map<String,Object>> ();

		for (Count5l count5: list
			 ) {
			HashMap<String,Object> hashMap=new HashMap<>();
			hashMap.put("value",count5.getLoadcount());
			hashMap.put("name",count5.getIp());
			ll.add(hashMap);
		}
		seriesMap.put("data", ll);
		/*seriesMap.put("data", Lists.newArrayList(hashMap));*/
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
	@RequestMapping(value = "getEcharts2Data")
	@ResponseBody
	public String getEcharts2Data() {
		Map<String, Object> data = Maps.newHashMap();
		//title
		data.put("title", new HashMap<String, Object>() {
			{
				put("text", "生产网登陆用户数分布");
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

		//legend
		data.put("legend",  new HashMap<String, Object>() {
			{
				put("orient", "vertical");
				put("left", "left");
				put("data", Lists.newArrayList("机器1","机器2","机器3","机器4","机器5"));
			}
		});
		//series
		Map<String, Object> seriesMap = Maps.newHashMap();
		seriesMap.put("name", "访问来源");
		seriesMap.put("type", "pie");
		seriesMap.put("radius", "55%");
		seriesMap.put("center", Lists.newArrayList("50%","60%"));
		seriesMap.put("data", Lists.newArrayList(
				new HashMap<String, Object>() {
					{
						put("value", "335");
						put("name", "机器1");
					}
				},
				new HashMap<String, Object>() {
					{
						put("value", "310");
						put("name", "机器2");
					}
				},
				new HashMap<String, Object>() {
					{
						put("value", "234");
						put("name", "机器3");
					}
				},
				new HashMap<String, Object>() {
					{
						put("value", "135");
						put("name", "机器4");
					}
				},
				new HashMap<String, Object>() {
					{
						put("value", "1548");
						put("name", "机器5");
					}
				}
		));
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
}