package com.roowoo.log.test.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.roowoo.log.common.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuzengpeng
 * @date 2018/9/21 14:55
 */
@Controller
@RequestMapping(value = "${adminPath}/test/echarts")
public class EchartsTestController extends BaseController {

    @RequestMapping(value = "echarts1")
    public String echarts1() {
        return "modules/test/echarts1";
    }

    @RequestMapping(value = "echarts2")
    public String echarts2() {
        return "modules/test/echarts2";
    }





    @RequestMapping(value = "getEcharts1Data")
    @ResponseBody
    public String getEcharts1Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
        titleMap.put("text", "二级部门帐户分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        legendMap.put("data", "销量");
        data.put("legend",  legendMap);
        //xAxis
        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList("帐户1","帐户2","帐户3","帐户4","帐户5","帐户6"));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        axisLabelMap.put("inside", true);
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series
        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "销量");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(5, 20, 36, 10, 10, 20));
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
