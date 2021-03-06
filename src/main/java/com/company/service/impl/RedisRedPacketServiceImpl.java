package com.company.service.impl;

import com.company.pojo.UserRedPacket;
import com.company.service.RedisRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final String PREFIX="red_packet_list_";
    private static final int TIME_SIZE=1000;
//    @Autowired
//    private RedisTemplate redisTemplate;
    @Autowired
    private DataSource dataSource;
    @Override
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
//        Long start=System.currentTimeMillis();
//        BoundListOperations ops=redisTemplate.boundListOps(PREFIX+redPacketId);
//        Long size=ops.size();
//        Long times=size%TIME_SIZE==0?size/TIME_SIZE:size/TIME_SIZE+1;
//        int count=0;
//        List<UserRedPacket>userRedPacketList=new ArrayList<>(TIME_SIZE);
//        for(int i=0;i<times;i++){
//            List userIdList=null;
//            if(i==0){
//                userIdList=ops.range(i*TIME_SIZE,(i+1)*TIME_SIZE);
//            }else{
//                userIdList=ops.range(i*TIME_SIZE+1,(i+1)*TIME_SIZE);
//            }
//            userRedPacketList.clear();
//            for(int j=0;j<userIdList.size();j++){
//                String args=userIdList.get(j).toString();
//                String[]arr=args.split("-");
//                String userIdStr=arr[0];
//                String timeStr=arr[1];
//                Long userId=Long.parseLong(userIdStr);
//                Long time=Long.parseLong(timeStr);
//                UserRedPacket userRedPacket=new UserRedPacket();
//                userRedPacket.setRedPacketId(redPacketId);
//                userRedPacket.setUserId(userId);
//                userRedPacket.setAmount(unitAmount);
//                userRedPacket.setGrabTime(new Timestamp(time));
//                userRedPacket.setNote("抢红包"+redPacketId);
//                userRedPacketList.add(userRedPacket);
//            }
//            count+=executeBatch(userRedPacketList);
//        }
//        //删除Redis中红包列表
//        redisTemplate.delete(PREFIX+redPacketId);
//        Long end=System.currentTimeMillis();
//        System.out.println("保存数据结束，耗时"+(end-start)+"毫秒，共"+count+"条记录保存。");
    }
    private int executeBatch(List<UserRedPacket>userRedPacketList){
//        Connection conn=null;
//        Statement stmt=null;
//        int[] count=null;
//        try{
//            conn=dataSource.getConnection();
//            conn.setAutoCommit(false);
//            stmt=conn.createStatement();
//            for(UserRedPacket userRedPacket:userRedPacketList){
//                String sql="update t_red_packet set stock=stock-1 where id="+userRedPacket.getRedPacketId();
//                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String sql2="insert into t_user_red_packet(red_packet_id,user_id,amount,grab_time,note)"+
//                        "values("+userRedPacket.getRedPacketId()+","+userRedPacket.getUserId()+","+
//                        userRedPacket.getAmount()+",'"+userRedPacket.getGrabTime()+"','"+userRedPacket.getNote()+"')";
//                stmt.addBatch(sql);
//                stmt.addBatch(sql2);
//            }
//            count=stmt.executeBatch();
//            conn.commit();
//        }catch(SQLException e){
//            throw new RuntimeException("抢红包批量执行过程错误");
//        }finally {
//            try{
//                if(conn!=null&&!conn.isClosed()){
//                    conn.close();
//                }
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//        //返回插入抢红包数据记录
//        return count.length/2;
        return 0;
    }
}
