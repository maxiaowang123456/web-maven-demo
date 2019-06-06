--缓存抢红包列表信息列表Key
local listKey='red_packet_list_'..KEYS[1]
--当前被抢红包key
local redPacket='red_packet_'..KEYS[1]
--获取当前红包库存
local stock=tonumber(redis.call('hget',redPacket,'stock'))
--没有库存，返回0
if stock<=0 then return 0 end
--库存减一
stock=stock-1
--保存当前库存
redis.call('hset',redPacket,'stock',tostring(stock))
--添加红包信息
redis.call('rpush',listKey,ARGV[1])
--最后一个红包，返回2，表示抢红包结束
if stock==0 then return 2 end
--抢红包成功，返回1
return 1