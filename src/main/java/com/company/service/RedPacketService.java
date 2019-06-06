package com.company.service;

import com.company.pojo.RedPacket;

public interface RedPacketService {
    RedPacket getRedPacket(Long id);
    int decreaseRedPacket(Long id,Integer version);
}
