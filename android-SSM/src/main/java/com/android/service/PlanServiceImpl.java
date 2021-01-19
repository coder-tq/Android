package com.android.service;
import com.android.dao.PlanMapper;
import com.android.dao.UserMapper;
import com.android.pojo.Plan;
import com.android.pojo.User;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service("PlanServiceImpl")
public class PlanServiceImpl implements PlanService{

    @Autowired
    PlanMapper planMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Plan queryPlanByPlanId(Integer id) {
        return planMapper.queryPlanByPlanId(id);
    }

    public Map<String ,Object> queryPlanByUserId(Integer id)
    {
        Map<String,Object> mp = new TreeMap<>();
        mp.put("plan",planMapper.queryPlanByUserId(id));
        return mp;
    }

    public Map<String, Object> submitPlan(String submit_content, Integer user_id ,Integer plan_id){
        Map<String, Object> mp = new TreeMap<>();
        try {
            mp.put("state",1);
            mp.put("effectRows",planMapper.submitPlan(new Date(new java.util.Date().getTime()),submit_content,user_id,plan_id));
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }

    public void createPieChart3D(HttpServletResponse response, Integer user_id) {
        List<Map<String,Object>> list= planMapper.queryPlanByUserId(user_id);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] strings = {"语文","数学","英语","生物","化学","物理","政治","地理","历史"};
        Long[] data = new Long[10];
        for (Map<String,Object> mp: list) {
            data[(int)mp.get("type")] = (((Date)mp.get("end_date")).getTime()-((Date)mp.get("start_date")).getTime())/(1000*60*60*24);
        }
        User user = userMapper.selectUserById(user_id);
        String name = user.getName();

        for(int i = 0; i < 9; i++)
        {
            dataset.addValue(data[i],name,strings[i]);
        }
        JFreeChart chart = ChartFactory.createBarChart("学习统计图", "科目", "时间(天)",
                dataset, PlotOrientation.VERTICAL, true, true, true);
        processChart(chart);
        CategoryPlot cp = chart.getCategoryPlot();
        cp.setBackgroundPaint(ChartColor.WHITE); // 背景色设置
        cp.setRangeGridlinePaint(ChartColor.GRAY); // 网格线色设置
        ServletOutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
            ChartUtils.writeChartAsPNG(outStream,chart,1600,1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void processChart(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        ValueAxis rAxis = plot.getRangeAxis();
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体", Font.PLAIN, 20));
        domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        rAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
    }
}
