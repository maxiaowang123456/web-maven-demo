package com.company.mapper;

import com.company.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;

public interface RedPacketMapper {
    RedPacket getRedPacket(Long id);
    int decreaseRedPacket(@Param("id") Long id, @Param("version") Integer version);
}
