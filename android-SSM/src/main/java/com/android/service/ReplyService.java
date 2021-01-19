package com.android.service;

import com.android.pojo.Reply;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ReplyService {
    Map<String, Object> queryReplyByDiscussId(Integer id);

    Map<String,Object> insertReply(Reply reply);
}
