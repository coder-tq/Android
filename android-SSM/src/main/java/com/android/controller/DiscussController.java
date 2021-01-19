package com.android.controller;

import com.android.dao.DiscussMapper;
import com.android.pojo.Discuss;
import com.android.service.DiscussService;
import com.android.service.DiscussServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiscussController {

    @Autowired
    DiscussServiceImpl discussService;

    @RequestMapping("/android/json")
    public Map<String, Object> queryDiscussById (@RequestBody Map<String, Object> map)
    {
        return discussService.queryDiscussById((int)map.get("id"));
    }

    @RequestMapping("/android/queryAllDiscuss")
    public Map<String, Object> queryAllDiscuss ()
    {
        return discussService.queryAllDiscuss();
    }

    @RequestMapping("/android/queryDiscussById")
    public Map<String, Object> queryDiscussById (Integer id)
    {
        return discussService.queryDiscussById(id);
    }

    @RequestMapping("/android/queryDiscussByTitle")
    public Map<String, Object> queryDiscussByTitle (String title)
    {
        return discussService.queryDiscussByTitle(title);
    }

    @RequestMapping("/android/insertDiscuss")
    public Map<String, Object> insertDiscuss (Discuss discuss)
    {
        return discussService.insertDiscuss(discuss);
    }

}
