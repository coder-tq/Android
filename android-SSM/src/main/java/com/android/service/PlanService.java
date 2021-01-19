package com.android.service;

import com.android.pojo.Plan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

@Service
public interface PlanService {
    Plan queryPlanByPlanId(Integer id);
    Map<String ,Object> queryPlanByUserId(Integer id);
    Map<String, Object> submitPlan(String submit_content,Integer user_id,Integer plan_id);
    void createPieChart3D(HttpServletResponse response,Integer user_id);
}
