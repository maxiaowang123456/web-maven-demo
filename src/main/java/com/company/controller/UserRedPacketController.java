package com.company.controller;

import com.company.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping("/grabRedPacket")
    @ResponseBody
    public Map<String,Object> grabRedPacket(Long redPacketId, Long userId){
        int result=userRedPacketService.grabRedPacket(redPacketId,userId);
        Map<String,Object>map=new HashMap<>();
        boolean flag=result>0;
        map.put("success",flag);
        map.put("properties/message",flag?"抢红包成功":"抢红包失败");
        return map;
    }

    @RequestMapping("/grabRedPacketByRedis")
    @ResponseBody
    public Map<String,Object> grabRedPacketByRedis(Long redPacketId, Long userId){
        Long result=userRedPacketService.grabRedPacketByRedis(redPacketId,userId);
        Map<String,Object>map=new HashMap<>();
        boolean flag=result>0;
        map.put("success",flag);
        map.put("properties/message",flag?"抢红包成功":"抢红包失败");
        return map;
    }
}
