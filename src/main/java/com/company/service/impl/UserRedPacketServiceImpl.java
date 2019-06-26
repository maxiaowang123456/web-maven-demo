package com.company.service.impl;

import com.company.mapper.RedPacketMapper;
import com.company.mapper.UserRedPacketMapper;
import com.company.pojo.RedPacket;
import com.company.pojo.UserRedPacket;
import com.company.service.RedisRedPacketService;
import com.company.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private RedisRedPacketService redisRedPacketService;
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private UserRedPacketMapper userRedPacketMapper;

    private static final int FAILD=0;//更新库存失败
    private static final int STOCK_ZERO=-1;//库存为0
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int grabRedPacket(Long redPacketId, Long userId) {
        return grabRedPacketForRetry(redPacketId,userId);
    }

    /**
     * 基于时间戳的方式重试机制
     */
    private int grabRedPacketForTime(Long redPacketId, Long userId){
        long start=System.currentTimeMillis();
        while(true){
            long end=System.currentTimeMillis();
            if(end-start>100){
                return FAILD;
            }
            int update=grabRedPacketCommon(redPacketId,userId);
            if(update==FAILD){
                continue;
            }else{
                return update;
            }

        }
    }

    /**
     * 基于重试次数对机制
     * @param redPacketId
     * @param userId
     * @return
     */
    private int grabRedPacketForRetry(Long redPacketId, Long userId){
        for(int i=1;i<=3;i++){
            int update=grabRedPacketCommon(redPacketId,userId);
            if(update==FAILD){
                continue;
            }else{
                return update;
            }
        }
        return FAILD;
    }

    private int grabRedPacketCommon(Long redPacketId, Long userId){
        RedPacket redPacket=redPacketMapper.getRedPacket(redPacketId);
        if(redPacket.getStock()>0){
            int update=redPacketMapper.decreaseRedPacket(redPacketId,redPacket.getVersion());
            //更新库存失败
            if(update==0){
                return FAILD;
            }
            UserRedPacket userRedPacket=new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包"+redPacketId);
            int result=userRedPacketMapper.grabRedPacket(userRedPacket);
            return result;
        }else{
            return STOCK_ZERO;//库存为0
        }
    }

    @Override
    public long grabRedPacketByRedis(Long redPacketId, Long userId) {
//        String script="local listKey='red_packet_list_'..KEYS[1] \n"
//        +"local redPacket='red_packet_'..KEYS[1] \n"
//        +"local stock=tonumber(redis.call('hget',redPacket,'stock')) \n"
//        +"if stock<=0 then return 0 end \n"
//                +"stock=stock-1 \n"
//        +"redis.call('hset',redPacket,'stock',tostring(stock)) \n"
//        +"redis.call('rpush',listKey,ARGV[1]) \n"
//        +"if stock==0 then return 2 end \n"
//        +"return 1 \n";
//        String sha1=null;
//        String args=userId+"-"+System.currentTimeMillis();
//        Long result=null;
//        Jedis jedis=(Jedis)redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
//        try{
//            if(sha1==null){
//                sha1=jedis.scriptLoad(script);
//            }
//            Object res=jedis.evalsha(sha1,1,redPacketId+"",args);
//            result=(Long)res;
//            if(result==2){
//                String unitAmountStr=jedis.hget("red_packet_"+redPacketId,"unit_amount");
//                Double unitAmount=Double.parseDouble(unitAmountStr);
//                System.out.println("thread_name =  "+Thread.currentThread().getName());
//                redisRedPacketService.saveUserRedPacketByRedis(redPacketId,unitAmount);
//            }
//        }finally {
//            if(jedis!=null&&jedis.isConnected()){
//                jedis.close();
//            }
//        }
//        return result;
        return 0;
    }
}
