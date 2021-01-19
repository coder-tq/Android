package com.android.service;

import com.android.dao.DiscussMapper;
import com.android.pojo.Discuss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Component
@Service
public class DiscussServiceImpl implements DiscussService{

    @Autowired
    DiscussMapper discussMapper;

    @Override
    public Map<String, Object> queryAllDiscuss ()
    {
        Map<String, Object> mp = new TreeMap<>();
        List<Discuss> list = discussMapper.queryAllDiscuss();
        mp.put("list", list);
        //mp.put("msg","wmydsb");
        return mp;
    }
    @Override
    public Map<String, Object> queryDiscussById (Integer id)
    {
        //if(id == null) return queryAllDiscuss();
        Map<String, Object> mp = new TreeMap<>();
        List<Discuss> list;
        try {
            list = discussMapper.queryDiscussByDiscuss_id(id);
            mp.put("list", list);
            mp.put("state",1);
        }
        catch (Exception e)
        {
            mp.put("state",0);
        }
        //mp.put("msg","wmydsb");
        return mp;
    }
    @Override
    public Map<String, Object> queryDiscussByTitle (String title) {
        Map<String, Object> mp = new TreeMap<>();
        List<Discuss> list = discussMapper.queryDiscussByTitle(title);
        mp.put("list", list);
        //mp.put("msg","wmydsb");
        return mp;
    }

    @Override
    public Map<String, Object> insertDiscuss(Discuss discuss) {
        Map<String, Object> mp = new TreeMap<>();
        discuss.setDate(new Date(new java.util.Date().getTime()));
        try {
            mp.put("state",discussMapper.insertDiscuss(discuss));
        } catch (Exception e)
        {
            mp.put("state",0);
        }
        return mp;
    }
}
