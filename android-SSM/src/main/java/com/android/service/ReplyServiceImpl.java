package com.android.service;

import com.android.dao.ReplyMapper;
import com.android.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Map;
import java.util.TreeMap;

@Service("ReplyServiceImpl")
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyMapper replyMapper;
    @Override
    public Map<String, Object> queryReplyByDiscussId(Integer id) {

        Map<String ,Object> mp = new TreeMap<>();
        try {
            mp.put("reply",replyMapper.queryReplyByDiscussId(id));
            mp.put("state",1);
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }

    @Override
    public Map<String, Object> insertReply(Reply reply) {

        Map<String ,Object> mp = new TreeMap<>();
        reply.setDate(new Date(new java.util.Date().getTime()));
        try {
            mp.put("effectRows",replyMapper.insertReply(reply));
            mp.put("state",1);
        }
        catch (Exception e)
        {
            mp.put("state",0);
            mp.put("error",e.toString());
        }
        return mp;
    }
}
