import com.company.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisString(){
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
}
