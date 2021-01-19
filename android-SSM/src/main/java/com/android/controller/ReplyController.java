package com.android.controller;

import com.android.dao.ReplyMapper;
import com.android.pojo.Reply;
import com.android.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReplyController {

    @Autowired
    ReplyService replyService;
    @RequestMapping("/android/queryReplyByDiscussId")
    public Map<String,Object> queryReplyByDiscussId(Integer discuss_id)
    {
        return replyService.queryReplyByDiscussId(discuss_id);
    }

    @RequestMapping("/android/insertReply")
    public Map<String,Object> insertReply(Reply reply)
    {
        return replyService.insertReply(reply);
    }
}
