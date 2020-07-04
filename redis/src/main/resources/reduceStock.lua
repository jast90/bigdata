-- 库存key
local key = KEYS[1]
-- 扣减库存
local reduceBy = tonumber(ARGV[1])

-- 获取当前库存
local stock = tonumber(redis.call('get',key) or "0")

-- 库存不够直接返回
if stock < reduceBy then
    return 0;
else
    local remain = tonumber(redis.call("DECRBY",key,reduceBy))
    return remain
end