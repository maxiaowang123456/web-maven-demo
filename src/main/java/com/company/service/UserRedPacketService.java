package com.company.service;

public interface UserRedPacketService {
    int grabRedPacket(Long redPacketId,Long userId);
    long grabRedPacketByRedis(Long redPacketId,Long userId);
}
