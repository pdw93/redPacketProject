-- 缓存抢红包列表信息
local listkey = 'red_packet_list_'..KEYS[1]
-- 当前被抢的红包
local redPacket = 'red_packet_'..KEYS[1]
-- 获取当前红包库存
local stock = tonumber(redis.call('hget',redPacket,'stock'))
-- 没有库存返回0
if stock <= 0 then
    return 0
end
-- 库存减1
stock = stock -1
-- 保存当前库存
redis.call('hset',redPacket,'stock',tostring(stock))
-- 往链表中加入当前红包信息
redis.call('rpush',listkey,ARGS[1])
-- 如果是最后一个红包返回2表示需要将数据保存到数据库
if stock == 0 then
    return 2
else
    return 1
end