package com.roowoo.log.modules.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roowoo.log.config.Global;
import com.roowoo.log.common.persistence.Page;
import com.roowoo.log.common.web.BaseController;
import com.roowoo.log.common.utils.StringUtils;
import com.roowoo.log.modules.account.entity.AccountLogin;
import com.roowoo.log.modules.account.service.AccountLoginService;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 账户登录Controller
 * @author zz
 * @version 2018-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/account/accountLogin")
public class AccountLoginController extends BaseController {

	@Autowired
	private AccountLoginService accountLoginService;

	@ModelAttribute
	public AccountLogin get(@RequestParam(required=false) String id) {
		AccountLogin entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountLoginService.get(id);
		}
		if (entity == null){
			entity = new AccountLogin();
		}
		return entity;
	}


//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts1")
    @ResponseBody
    public String getEcharts1Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
//        titleMap.put("text", "二级部门帐户分布");
        data.put("title", titleMap);

        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("账户数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        List<Map<String, Object>> arr = accountLoginService.findDeptList();
        List stateList = new ArrayList();
        List accountNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                stateList.add(map.get("state"));
                accountNumList.add(map.get("account_number"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(stateList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

       /* Map<String, Object> colorMap = Maps.newHashMap();
        List colorList = new ArrayList();
        colorList.add("red");
        colorList.add("blue");
        colorList.add("white");
        colorList.add("pink");
        colorList.add("black");
        colorMap.put("color",colorList);
        Map styleMap = Maps.newHashMap();
        styleMap.put("normal",colorMap);*/



        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "账户数");
        seriesMap.put("type", "bar");
        /*seriesMap.put("itemStyle", "blue");*/
        seriesMap.put("data", Lists.newArrayList(accountNumList));
        data.put("series", Lists.newArrayList(seriesMap));
        ArrayList<String> color=new ArrayList<>();
        color.add("#8ecb9b");
        color.add("#83d0f4");
        data.put("color",color);
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts2")
    @ResponseBody
    public String getEcharts2Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
//        titleMap.put("text", "二级部门登录次数分布");
        data.put("title", titleMap);
        //tooltip
        Map<String, Object> toolMap = Maps.newHashMap();
        toolMap.put("trigger","axis");
        toolMap.put("type","cross");

        data.put("tooltip", toolMap);
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("登录次数");
        al1.add("平均登录次数");

        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        List<Map<String, Object>> arr = accountLoginService.findLoginNum();
        List stateList = new ArrayList();
        List loginNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                stateList.add(map.get("state"));
                loginNumList.add(map.get("login_number"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(stateList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        List<Map<String, Object>> seriesList = new ArrayList();

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "登录次数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(loginNumList));

        List avgList = new ArrayList();
        avgList.add("100");
        avgList.add("200");
        avgList.add("70");
        avgList.add("80");
        avgList.add("250");
        Map<String, Object> seriesMap1 = Maps.newHashMap();
        seriesMap1.put("name", "平均登录次数");
        seriesMap1.put("type", "line");
        seriesMap1.put("data", Lists.newArrayList(avgList));

        seriesList.add(seriesMap);
        seriesList.add(seriesMap1);


        data.put("series", Lists.newArrayList(seriesList));
        ArrayList<String> color=new ArrayList<>();
        color.add("#8ecb9b");
        color.add("#83d0f4");
        data.put("color",color);
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts3")
    @ResponseBody
    public String getEcharts3Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
//        titleMap.put("text", "一级部门登录次数分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("登录次数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        List<Map<String, Object>> arr = accountLoginService.findLoginNum2();
        List stateList = new ArrayList();
        List loginNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                stateList.add(map.get("state"));
                loginNumList.add(map.get("login_number"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(stateList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "登录次数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(loginNumList));
        data.put("series", Lists.newArrayList(seriesMap));
        data.put("color","#8ecb9b");
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts4")
    @ResponseBody
    public String getEcharts4Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
        titleMap.put("text", "一个账户一天登录次数分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("登录次数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        String logdate = "2018-09-25";
        List<Map<String, Object>> arr = accountLoginService.findLoginNum3(logdate);
        List userList = new ArrayList();
        List loginNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                userList.add(map.get("username"));
                loginNumList.add(map.get("login_num"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(userList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "登录次数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(loginNumList));
        data.put("series", Lists.newArrayList(seriesMap));
        data.put("color","#8ecb9b");
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts5")
    @ResponseBody
    public String getEcharts5Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
        titleMap.put("text", "一个ip一天登录次数分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("登录次数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        String logdate = "2018-09-25";
        List<Map<String, Object>> arr = accountLoginService.findLoginNum4(logdate);
        List loginIP = new ArrayList();
        List loginNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                loginIP.add(map.get("login_ip"));
                loginNumList.add(map.get("login_num"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(loginIP));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        //axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "登录次数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(loginNumList));
        data.put("series", Lists.newArrayList(seriesMap));
        data.put("color","#8ecb9b");
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts6")
    @ResponseBody
    public String getEcharts6Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
        titleMap.put("text", "一个账户登录失败的天数分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("天数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        List<Map<String, Object>> arr = accountLoginService.findLoginNum5();
        List userList = new ArrayList();
        List logfailNumList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                userList.add(map.get("username"));
                logfailNumList.add(map.get("logfail_num"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(userList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval", "0");
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "天数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(logfailNumList));
        data.put("series", Lists.newArrayList(seriesMap));
        data.put("color","#8ecb9b");
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//    @RequiresPermissions("account:accountLogin:view")
    @RequestMapping(value = "getEcharts7")
    @ResponseBody
    public String getEcharts7Data() {
        Map<String, Object> data = Maps.newHashMap();
        //title
        Map<String, Object> titleMap = Maps.newHashMap();
//        titleMap.put("text", "用户活跃天数分布天数分布");
        data.put("title", titleMap);
        //tooltip
        data.put("tooltip", Maps.newHashMap());
        //legend
        Map<String, Object> legendMap = Maps.newHashMap();
        ArrayList<String> al1=new ArrayList<String>();
        al1.add("账户数");
        legendMap.put("data", al1);
        data.put("legend",  legendMap);
        //xAxis
        List<Map<String, Object>> arr = accountLoginService.findLoginNum6();
        List loginNumList = new ArrayList();
        List userCountList = new ArrayList();
        if(null != arr&&arr.size()>0){
            for(Map<String, Object> map : arr){
                loginNumList.add(map.get("login_num"));
                userCountList.add(map.get("user_count"));
            }
        }

        Map<String, Object> xAxisMap = Maps.newHashMap();
        xAxisMap.put("data", Lists.newArrayList(loginNumList));
        Map<String, Object> axisLabelMap = Maps.newHashMap();
        //axisLabelMap.put("inside", true);
        axisLabelMap.put("type", "category");
        axisLabelMap.put("interval",1);
//        axisLabelMap.put("rotate",40);
        xAxisMap.put("axisLabel", axisLabelMap);
        data.put("xAxis",  xAxisMap);
        //yAxis
        data.put("yAxis",  Maps.newHashMap());
        //series

        Map<String, Object> seriesMap = Maps.newHashMap();
        seriesMap.put("name", "账户数");
        seriesMap.put("type", "bar");
        seriesMap.put("data", Lists.newArrayList(userCountList));
        data.put("series", Lists.newArrayList(seriesMap));
        data.put("color","#8ecb9b");
        String result = JSON.toJSONString(data);
        logger.info(result);
        return result;
    }



//	@RequiresPermissions("account:accountLogin:view")
	@RequestMapping(value = {"list", ""})
	public String list() {
		//Page<AccountLogin> page = accountLoginService.findPage(new Page<AccountLogin>(request, response), accountLogin);
		//model.addAttribute("page", page);
		return "modules/account/accountLoginList";
	}

//	@RequiresPermissions("account:accountLogin:view")
	@RequestMapping(value = "form")
	public String form(AccountLogin accountLogin, Model model) {
		model.addAttribute("accountLogin", accountLogin);
		return "modules/account/accountLoginForm";
	}

//	@RequiresPermissions("account:accountLogin:edit")
	@RequestMapping(value = "save")
	public String save(AccountLogin accountLogin, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accountLogin)){
			return form(accountLogin, model);
		}
		accountLoginService.save(accountLogin);
		addMessage(redirectAttributes, "保存登录成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountLogin/?repage";
	}
	
//	@RequiresPermissions("account:accountLogin:edit")
	@RequestMapping(value = "delete")
	public String delete(AccountLogin accountLogin, RedirectAttributes redirectAttributes) {
		accountLoginService.delete(accountLogin);
		addMessage(redirectAttributes, "删除登录成功");
		return "redirect:"+Global.getAdminPath()+"/account/accountLogin/?repage";
	}

}