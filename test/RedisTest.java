import com.company.config.RootConfig;
import com.company.pojo.User;
import com.company.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisListCommands;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void testRedisString(){
        redisTemplate.delete(Arrays.asList("key1","key2"));
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("key2","value2");
        String value1=(String)redisTemplate.opsForValue().get("key1");
        System.out.println(value1);
        redisTemplate.delete("key1");
        Long length=redisTemplate.opsForValue().size("key2");
        System.out.println(length);
        String oldValue=(String)redisTemplate.opsForValue().getAndSet("key2","new_value2");
        System.out.println(oldValue);
        String value2=(String)redisTemplate.opsForValue().get("key2");
        System.out.println(value2);
        String strValue=redisTemplate.opsForValue().get("key2",0,3);
        System.out.println(strValue);
        int newLen=redisTemplate.opsForValue().append("key2","_app");
        System.out.println(newLen);
        String appendValue2=(String)redisTemplate.opsForValue().get("key2");
        System.out.println(appendValue2);
    }

    @Test
    public void testRedisHash(){
        String key="hash";
        redisTemplate.delete(key);
        Map<String,String> map=new HashMap<>();
        map.put("f1","val1");
        map.put("f2","val2");
        redisTemplate.opsForHash().putAll(key,map);
        redisTemplate.opsForHash().put(key,"f3","6");
        printValueForHash(redisTemplate,key,"f3");
        boolean exist=redisTemplate.opsForHash().hasKey(key,"f3");
        System.out.println(exist);
        Map keyValueMap=redisTemplate.opsForHash().entries(key);
        redisTemplate.opsForHash().increment(key,"f3",2);
        printValueForHash(redisTemplate,key,"f3");
        redisTemplate.opsForHash().increment(key,"f3",0.88);
        printValueForHash(redisTemplate,key,"f3");
        List vals=redisTemplate.opsForHash().values(key);
        Set fields=redisTemplate.opsForHash().keys(key);
        List filedList=new ArrayList();
        filedList.add("f1");
        filedList.add("f2");
        List val2List=redisTemplate.opsForHash().multiGet(key,filedList);
        boolean success=redisTemplate.opsForHash().putIfAbsent(key,"f4","val4");
        System.out.println(success);
        Long result=redisTemplate.opsForHash().delete(key,"f1","f2");
        System.out.println(result);
    }

    private static void printValueForHash(RedisTemplate redisTemplate,String key,String field){
        Object value=redisTemplate.opsForHash().get(key,field);
        System.out.println(value);
    }

    @Test
    public void testRedisList()throws UnsupportedEncodingException {
        redisTemplate.delete("list");
        redisTemplate.opsForList().leftPush("list","node3");
        List<String>nodeList=new ArrayList<>();
        for(int i=2;i>=1;i--){
            nodeList.add("node"+i);
        }
        redisTemplate.opsForList().leftPushAll("list",nodeList);
        redisTemplate.opsForList().rightPush("list","node4");
        String node1=(String)redisTemplate.opsForList().index("list",0);
        long size=redisTemplate.opsForList().size("list");
        String lpop=(String)redisTemplate.opsForList().leftPop("list");
        String rpop=(String)redisTemplate.opsForList().rightPop("list");
        redisTemplate.getConnectionFactory().getConnection().lInsert("list".getBytes("UTF-8"),
                RedisListCommands.Position.BEFORE,"node2".getBytes("UTF-8"),
                "before_node".getBytes("UTF-8"));
        redisTemplate.getConnectionFactory().getConnection().lInsert("list".getBytes("utf-8"),
                RedisListCommands.Position.AFTER,"node2".getBytes("utf-8"),
                "after_node".getBytes("utf-8"));
        redisTemplate.opsForList().leftPushIfPresent("list","head");
        redisTemplate.opsForList().rightPushIfPresent("list","end");
        List valueList=redisTemplate.opsForList().range("list",0,10);
        nodeList.clear();
        for(int i=1;i<=3;i++){
            nodeList.add("node");
        }
        redisTemplate.opsForList().leftPushAll("list",nodeList);
        redisTemplate.opsForList().remove("list",3,"node");
        redisTemplate.opsForList().set("list",0,"new_head_value");
        printList(redisTemplate,"list");
    }

    private static void printList(RedisTemplate redisTemplate,String key){
        Long size=redisTemplate.opsForList().size(key);
        List valueList=redisTemplate.opsForList().range(key,0,size);
        System.out.println(valueList);
    }

    @Test
    public void testBlockList(){
        redisTemplate.delete("list1");
        redisTemplate.delete("list2");
        List nodeList=new ArrayList();
        for(int i=1;i<=5;i++){
            nodeList.add("node"+i);
        }
        redisTemplate.opsForList().leftPushAll("list1",nodeList);
        redisTemplate.opsForList().leftPop("list1",1,TimeUnit.SECONDS);
        redisTemplate.opsForList().rightPop("list1",1,TimeUnit.SECONDS);
        nodeList.clear();
        for(int i=0;i<=3;i++){
            nodeList.add("node"+i);
        }
        redisTemplate.opsForList().leftPushAll("list2",nodeList);
        redisTemplate.opsForList().rightPopAndLeftPush("list1","list2");
        redisTemplate.opsForList().rightPopAndLeftPush("list1","list2",1,TimeUnit.SECONDS);
        printList(redisTemplate,"list1");
        printList(redisTemplate,"list2");
    }

    @Test
    public void testRedisSet(){
        redisTemplate.delete(Arrays.asList("set1","set2","inter_set","union_set","diff_set"));
        Set set=null;
        redisTemplate.boundSetOps("set1").add("v1","v2","v3","v4","v5","v6");
        redisTemplate.boundSetOps("set2").add("v0","v2","v4","v6","v8");
        redisTemplate.opsForSet().size("set1");
        set=redisTemplate.opsForSet().difference("set1","set2");
        set=redisTemplate.opsForSet().intersect("set1","set2");
        boolean exist=redisTemplate.opsForSet().isMember("set1","v1");
        set=redisTemplate.opsForSet().members("set1");
        String val=(String)redisTemplate.opsForSet().pop("set1");
        val=(String)redisTemplate.opsForSet().randomMember("set1");
        List valList=redisTemplate.opsForSet().randomMembers("set1",2);
        redisTemplate.opsForSet().remove("set1","v1");
        redisTemplate.opsForSet().union("set1","set2");
        redisTemplate.opsForSet().differenceAndStore("set1","set2","diff_set");
        redisTemplate.opsForSet().intersectAndStore("set1","set2","inter_set");
        redisTemplate.opsForSet().unionAndStore("set1","set2","union_set");
        printSet(redisTemplate,"set1");
        printSet(redisTemplate,"set2");
        printSet(redisTemplate,"diff_set");
        printSet(redisTemplate,"inter_set");
        printSet(redisTemplate,"union_set");
    }

    private static void printSet(RedisTemplate redisTemplate,String key){
        Set set=redisTemplate.opsForSet().members(key);
        System.out.println(set);
    }

    @Test
    public void testRedisZSet(){
        redisTemplate.delete(Arrays.asList("zset1","zset2"));
        Set<ZSetOperations.TypedTuple>set1=new HashSet<>();
        Set<ZSetOperations.TypedTuple>set2=new HashSet<>();
        int j=9;
        for(int i=1;i<=9;i++){
            j--;
            Double score1=Double.valueOf(i);
            String value1="x"+i;
            Double score2=Double.valueOf(j);
            String value2=j%2==1?"y"+j:"x"+j;
            ZSetOperations.TypedTuple typedTuple1=new DefaultTypedTuple(value1,score1);
            ZSetOperations.TypedTuple typedTuple2=new DefaultTypedTuple(value2,score2);
            set1.add(typedTuple1);
            set2.add(typedTuple2);
            redisTemplate.opsForZSet().add("zset1",set1);
            redisTemplate.opsForZSet().add("zset2",set2);
            Long size=null;
            size=redisTemplate.opsForZSet().size("zset1");
            size=redisTemplate.opsForZSet().count("zset1",3,6);
            Set set=null;
            set=redisTemplate.opsForZSet().range("zset1",1,5);
            printSet(set);
            set=redisTemplate.opsForZSet().rangeWithScores("zset1",1,5);
            printTypedTuple(set);
            size=redisTemplate.opsForZSet().intersectAndStore("zset1","zset2","inter_set");
            RedisZSetCommands.Range range=RedisZSetCommands.Range.range();
            range.lt("x8");
            range.gt("x1");
            set=redisTemplate.opsForZSet().rangeByLex("zset1",range);
            printSet(set);
            range.lte("x8");
            range.gte("x1");
            set=redisTemplate.opsForZSet().rangeByLex("zset1",range);
            printSet(set);
            RedisZSetCommands.Limit limit=RedisZSetCommands.Limit.limit();
            limit.count(5);
            limit.offset(5);
            set=redisTemplate.opsForZSet().rangeByLex("zset1",range,limit);
            printSet(set);
            Long rank=redisTemplate.opsForZSet().rank("zset1","x4");
            System.err.println("rank="+rank);
            size=redisTemplate.opsForZSet().remove("zset1","x5","x6");
            System.err.println("delete="+size);
            size=redisTemplate.opsForZSet().removeRange("zset2",1,2);
            set=redisTemplate.opsForZSet().rangeWithScores("zset2",0,-1);
            printTypedTuple(set);
            size=redisTemplate.opsForZSet().remove("zset2","y5","y3");
            System.err.println(size);
            Double db1=redisTemplate.opsForZSet().incrementScore("xset1","x1",11);
            redisTemplate.opsForZSet().removeRangeByScore("zsset1",1,2);
            set=redisTemplate.opsForZSet().reverseRangeWithScores("zset2",1,10);
            printTypedTuple(set);
        }
    }

    private static void printTypedTuple(Set<ZSetOperations.TypedTuple>set){
        if(set!=null&&set.isEmpty())return;
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            ZSetOperations.TypedTuple typedTuple=(ZSetOperations.TypedTuple) iterator.next();
            System.err.print("{value="+typedTuple.getValue()+",score="+typedTuple.getScore()+"}\n");
        }
    }
    private static void printSet(Set set){
        if(set!=null&&set.isEmpty())return;
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            Object val=iterator.next();
            System.out.print(val+"\t");
        }
        System.out.println();
    }

    @Test
    public void testRedisTransaction(){
        redisTemplate.delete("key1");
        SessionCallback sessionCallback=new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                ops.boundValueOps("key1").set("value1");
                String value=(String)ops.boundValueOps("key1").get();
                System.out.println("事务在执行过程中，命令入队列，并没有执行，所以value为空。value="+value);
                ops.exec();
                value=(String)redisTemplate.opsForValue().get("key1");
                return value;
            }
        };
        String value=(String)redisTemplate.execute(sessionCallback);
        System.out.println("value="+value);
    }

//    @Test
    public void testRedisPipedLine(){
        SessionCallback callback=new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                for(int i=0;i<100000;i++){
                    ops.boundValueOps("pipeline_"+i).set("pipeline_value_"+i);
                    ops.boundValueOps("pipeline_"+i).get();
                }
                return null;
            }
        };
        Long start=System.currentTimeMillis();
        redisTemplate.execute(callback);
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void testRedisMessageListener(){
        redisTemplate.convertAndSend("chat","I am Lazy");
    }

    @Test
    public void testRedisExpire(){
        redisTemplate.delete("key1");
       SessionCallback callback=new SessionCallback() {
           @Override
           public Object execute(RedisOperations ops) throws DataAccessException {
               ops.boundValueOps("key1").set("value1");
               ops.boundValueOps("key1").get();
               Long expSecond=ops.getExpire("key1");
               System.out.println(expSecond);
               boolean b=false;
               b=ops.expire("key1",120,TimeUnit.SECONDS);
               b=ops.persist("key1");
               Long l=0l;
               l=ops.getExpire("key1");
               Long now=System.currentTimeMillis();
               Date date=new Date();
               date.setTime(now+120000);
                ops.expireAt("key1",date);
                return null;
           }
       };
       redisTemplate.execute(callback);
    }

    /**
     * 测试在java中使用LUA脚本语言
     */
    @Test
    public void testRedisLua(){
        redisTemplate.delete("lua-key");
        redisTemplate.delete("sha-key");
        Jedis jedis=(Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        String  helloJava=(String)jedis.eval("return 'hello java'");
        System.out.println(helloJava);
        jedis.eval("redis.call('set',KEYS[1],ARGV[1])",1,"lua-key","lua-value");
        String luaKey=jedis.get("lua-key");
        System.out.println(luaKey);
        String sha1=jedis.scriptLoad("redis.call('set',KEYS[1],ARGV[1])");
        jedis.evalsha(sha1,1,new String[]{"sha-key","sha-value"});
        String shaKey=jedis.get("sha-key");
        System.out.println(shaKey);
        jedis.close();
    }

    @Test
    public void testLuaFile(){
        byte[]bytes=getBytes("redis.lua");
        System.out.println(new String(bytes));
        Jedis jedis=(Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        byte[]sha1=jedis.scriptLoad(bytes);
        Object obj=jedis.evalsha(sha1,2,"key1".getBytes(),"key2".getBytes(),"2".getBytes(),"4".getBytes());
        System.out.println(obj);
    }

    private static byte[] getBytes(String fileName){
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        try{
            Resource resource=new ClassPathResource("redis.lua");
            InputStream io=resource.getInputStream();
            byte[]bb=new byte[2048];
            int ch;
            ch=io.read(bb);
            while(ch!=-1){
                bo.write(bb,0,ch);
                ch=io.read(bb);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return bo.toByteArray();
    }

    @Test
    public void testSentinel(){
        SessionCallback callback=new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.delete("key");
                ops.boundValueOps("key").set("value");
                String value=(String)ops.boundValueOps("key").get();
                return value;
            }
        };
        String value=(String)redisTemplate.execute(callback);
        System.out.println(value);
    }

    @Test
    public void testRedisCache(){
        User user=userService.findUserById(1l);
        System.out.println(user.getUsername());
    }
    @Test
    public void testRedisCachePut(){
        User user=new User();
        user.setUsername("ww");
        user.setPassword("123456");
        userService.saveUser(user);
    }
}
