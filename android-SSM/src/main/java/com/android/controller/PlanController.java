package com.android.controller;

import com.android.dao.PlanMapper;
import com.android.pojo.Plan;
import com.android.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class PlanController {
    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PlanService planService;
    @RequestMapping("/android/queryPlanByUserId")
    public Map<String ,Object> queryPlanByUserId(Integer id)
    {
        return planService.queryPlanByUserId(id);
    }

    @RequestMapping("/android/submitPlan")
    public Map<String, Object> submitPlan(String submit_content, Integer user_id, Integer plan_id)
    {
        return planService.submitPlan(submit_content,user_id,plan_id);
    }

    @RequestMapping("/android/chart/get")
    public void imgRsp(HttpServletResponse response, Integer user_id) throws IOException {
        planService.createPieChart3D(response,user_id);
    }

    @RequestMapping("/android/queryPlanByPlanId")
    public Plan queryPlanByPlanId(Integer id)
    {
        return planService.queryPlanByPlanId(id);
    }

}
