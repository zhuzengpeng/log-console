package com.cmbc;

import com.roowoo.log.LogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzengpeng
 * @date 2018/10/13 17:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogApplication.class)
@EnableAutoConfiguration
public class DataDealTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {
        int basic_opt_number = 50;
        deal333("0000078358", basic_opt_number);
        deal333("0000009162", basic_opt_number);
    }

    /**
     * 查询单个用户某时间段操作的最大次数
     * @param username
     */
    public void deal1(String username) {
        String sql = "SELECT DISTINCT username,src_ip,opt_type,opt_time,fail_status,cxyy,opt_time1 FROM zx_input1 WHERE username=? AND opt_type=1 ORDER BY opt_time ASC";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, username);
        int max_opt_number = 0;
        for(int i=0; i<list.size()-1; i++) {
            Long opt_time_start = Long.parseLong(list.get(i).get("opt_time").toString());
            Long opt_time_end = Long.parseLong(list.get(i+1).get("opt_time").toString());
            System.out.println(opt_time_start + "-------" + opt_time_end);
            String sql_1 = "select * from zx_input1 where  username=? and opt_time>=? and opt_time<?";
            List list1 = jdbcTemplate.queryForList(sql_1, username, opt_time_start, opt_time_end);
            System.out.println(list1.size());
            if(list1.size() > max_opt_number) {
                max_opt_number = list1.size();
            }
        }
        System.out.println(max_opt_number);
    }

    @Test
    public void deal2() {
        deal1("0000009162");
        deal1("0000078358");
    }

    /**
     * 按时间段查询操作数据,并补齐数据
     * @param username
     * @param basic_opt_number
     */
    public void deal333(String username, int basic_opt_number) {
        String sql = "SELECT DISTINCT username,src_ip,opt_type,opt_time,fail_status,cxyy,opt_time1 FROM zx_input1 WHERE username=? AND opt_type=1 ORDER BY opt_time ASC";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, username);
        for(int i=0; i<list.size()-1; i++) {
            Long opt_time_start = Long.parseLong(list.get(i).get("opt_time").toString());
            Long opt_time_end = Long.parseLong(list.get(i+1).get("opt_time").toString());
            String sql_1 = "select * from zx_input1 where  username=? and opt_time>=? and opt_time<? order by opt_time asc";
            List<Map<String, Object>> list1 = jdbcTemplate.queryForList(sql_1, username, opt_time_start, opt_time_end);
            if(!list1.isEmpty()) {
                Date basic_date = (Date)list1.get(0).get("opt_time1");
                int first_opt_hour = getHours(basic_date);
                long max_interval_second = gettwotimetiff(basic_date, (Date)list1.get(list1.size()-1).get("opt_time1"));
                for(int j=0; j<list1.size(); j++) {
                    Map<String, Object> map1 = list1.get(j);
                    Date d11 = (Date)map1.get("opt_time1");
                    int isWorkday = isWorkDay(d11);
                    Object cxyy = map1.get("cxyy");
                    Object opt_type = map1.get("opt_type");
                    Object fail_status = map1.get("fail_status");
                    Object src_ip = map1.get("src_ip");
                    Object usernameinsert = map1.get("username");
                    long last_interval_second = gettwotimetiff(basic_date, d11);
                    String insertsql = "insert into zx_input2(opt_sequence, opt_type, fail_status, src_ip, last_interval_second, first_opt_hour, is_workday, cxyy, username, label, src_ip1, last_interval_second1)" +
                            " values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    BigDecimal max_interval_second1 = new BigDecimal(0);
                    if(max_interval_second != 0) {
                        max_interval_second1 = new BigDecimal(last_interval_second).divide(new BigDecimal(max_interval_second), 9, RoundingMode.HALF_UP);
                    }
                    jdbcTemplate.update(insertsql, i+1, opt_type, fail_status, src_ip, last_interval_second, first_opt_hour, isWorkday, cxyy, username,trans_label(username), src_ip1(src_ip.toString()), max_interval_second1);
                }
                //补足数据
                if(list1.size() < basic_opt_number) {
                    insertdata(basic_opt_number - list1.size(), username);
                }
            }

        }
    }

    /**
     * src_ip转成数字
     * @param src_ip
     * @return
     */
    private String src_ip1(String src_ip) {
        String s = "0";
        if("198.25.13.132".equals(src_ip)) {
            s = "1";
        } else if("198.25.13.135".equals(src_ip)) {
            s = "2";
        } else if("198.25.13.136".equals(src_ip)) {
            s = "3";
        } else if("198.25.13.139".equals(src_ip)) {
            s = "4";
        } else if("198.71.128.69".equals(src_ip)) {
            s = "5";
        } else if("198.71.128.76".equals(src_ip)) {
            s = "6";
        }
        return s;
    }

    /**
     * username转成数字型
     * @param username
     * @return
     */
    public String trans_label(String username) {
        String label = "0";
        if(("0000078358").equals(username)) {
            label = "0";
        } else if(("0000009162").equals(username)) {
            label = "1";
        }
        return label;
    }

    @Test
    public void test66() {
        insertdata(10, "0");
    }

    /**
     * 插入补充的数据
     * @param number
     */
    private void insertdata(int number, String username) {
        for(int i=0;i<number; i++) {
            String insertsql = "insert into zx_input2(opt_sequence, opt_type, fail_status, src_ip, last_interval_second, first_opt_hour, is_workday, cxyy, username, label, src_ip1, last_interval_second1)" +
                    " values(?,?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(insertsql, "99", "99", "99", "99", "-1", "99", "99", "99", username, trans_label(username), "99", "-1");
        }
    }

    @Test
    public void formatetime() {
        String sql = "SELECT * FROM zx_input1 ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list.forEach(map -> {
            Long id = Long.parseLong(map.get("id").toString());
            String opttime = map.get("opt_time").toString();
            Date date = timeStamp2Date(opttime, null);
            String update_sql = "update zx_input1 set opt_time1 =? where id=?";
            Object[] args = {date, id};
            jdbcTemplate.update(update_sql, args);
        });
    }

    public static Date timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return null;
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return new Date(Long.valueOf(seconds));
    }

    /**
     * 判断是否为工作日   1：是  0：否
     * @param bDate
     * @return
     */
    public int isWorkDay(Date bDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(bDate);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return 0;
        } else{
            return 1;
        }
    }

    public int getHours(Date bDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(bDate);
        int hour=cal.get(Calendar.HOUR);
        return hour;
    }

    /**
     * 取得2个时间的秒差
     * @param date1
     * @param date2
     * @return
     */
    public long gettwotimetiff(Date date1, Date date2) {
        long interval = (date2.getTime() - date1.getTime())/1000;
        return interval;
    }

    @Test
    public void test44() {
        System.out.println(isWorkDay(new Date()));
    }
}
