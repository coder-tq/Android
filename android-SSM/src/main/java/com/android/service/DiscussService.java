package com.android.service;

import com.android.pojo.Discuss;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public interface DiscussService {

    public Map<String, Object> queryAllDiscuss ();
    public Map<String, Object> queryDiscussById (Integer id);
    public Map<String, Object> queryDiscussByTitle (String title);
    public Map<String, Object> insertDiscuss(Discuss discuss);
}
