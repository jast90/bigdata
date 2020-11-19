## 版本
Elasticsearch 版本：7.10
## 索引文档操作
### 创建映射
```shell script
curl -X PUT 'localhost:9200/group?pretty' -H "content-type:application/json" -d '{
  "mappings":{
    "properties":{
      "host":{
        "type":"text"
      }
    }
  }
}'
```

### 获取映射
```shell script
curl 'localhost:9200/group/_mapping?pretty'
```

### 获取映射的指定字段
```shell script
curl 'localhost:9200/group/_mapping/field/group-id,host?pretty'
```

### 添加一个字段到已经存在的映射
```shell script
curl -XPUT 'localhost:9200/group/_mapping?pretty' -H "Content-type:application/json" -d '
{
  "properties":{
    "group-id":{
      "type":"keyword"
    }
  }
}
'
```


### 添加文档
```shell script
curl -XPUT 'localhost:9200/group/_doc/1?pretty' -H "Content-type:application/json" -d '
{
  "host":"jast.cn"
}
'
```


### 根据id获取文档
```shell script
curl 'localhost:9200/group/_doc/1?pretty'
```
