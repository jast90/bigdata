## logstash案例

### 同步mysql中的行政区划到ES：66万条数据，从日志执行时间可以看出花了2分钟左右时间

```shell
bin/logstash -f ~/gitlab/bigdata/logstash/logstash-mysql-china-city-to-es.conf
Using JAVA_HOME defined java: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
WARNING, using JAVA_HOME while Logstash distribution comes with a bundled JDK
Sending Logstash logs to /Users/zhangzhiwen/software/logstash-7.10.0/logs which is now configured via log4j2.properties
[2020-11-26T10:15:08,805][INFO ][logstash.runner          ] Starting Logstash {"logstash.version"=>"7.10.0", "jruby.version"=>"jruby 9.2.13.0 (2.5.7) 2020-08-03 9a89c94bcc OpenJDK 64-Bit Server VM 25.252-b09 on 1.8.0_252-b09 +indy +jit [darwin-x86_64]"}
[2020-11-26T10:15:09,367][WARN ][logstash.config.source.multilocal] Ignoring the 'pipelines.yml' file because modules or command line options are specified
[2020-11-26T10:15:11,686][INFO ][org.reflections.Reflections] Reflections took 44 ms to scan 1 urls, producing 23 keys and 47 values
[2020-11-26T10:15:12,805][INFO ][logstash.outputs.elasticsearch][main] Elasticsearch pool URLs updated {:changes=>{:removed=>[], :added=>[http://localhost:9200/]}}
[2020-11-26T10:15:13,120][WARN ][logstash.outputs.elasticsearch][main] Restored connection to ES instance {:url=>"http://localhost:9200/"}
[2020-11-26T10:15:13,226][INFO ][logstash.outputs.elasticsearch][main] ES Output version determined {:es_version=>7}
[2020-11-26T10:15:13,235][WARN ][logstash.outputs.elasticsearch][main] Detected a 6.x and above cluster: the `type` event field won't be used to determine the document _type {:es_version=>7}
[2020-11-26T10:15:13,376][INFO ][logstash.outputs.elasticsearch][main] New Elasticsearch output {:class=>"LogStash::Outputs::ElasticSearch", :hosts=>["//localhost:9200"]}
[2020-11-26T10:15:13,414][INFO ][logstash.outputs.elasticsearch][main] Using a default mapping template {:es_version=>7, :ecs_compatibility=>:disabled}
[2020-11-26T10:15:13,559][INFO ][logstash.outputs.elasticsearch][main] Attempting to install template {:manage_template=>{"index_patterns"=>"logstash-*", "version"=>60001, "settings"=>{"index.refresh_interval"=>"5s", "number_of_shards"=>1}, "mappings"=>{"dynamic_templates"=>[{"message_field"=>{"path_match"=>"message", "match_mapping_type"=>"string", "mapping"=>{"type"=>"text", "norms"=>false}}}, {"string_fields"=>{"match"=>"*", "match_mapping_type"=>"string", "mapping"=>{"type"=>"text", "norms"=>false, "fields"=>{"keyword"=>{"type"=>"keyword", "ignore_above"=>256}}}}}], "properties"=>{"@timestamp"=>{"type"=>"date"}, "@version"=>{"type"=>"keyword"}, "geoip"=>{"dynamic"=>true, "properties"=>{"ip"=>{"type"=>"ip"}, "location"=>{"type"=>"geo_point"}, "latitude"=>{"type"=>"half_float"}, "longitude"=>{"type"=>"half_float"}}}}}}}
[2020-11-26T10:15:13,589][INFO ][logstash.javapipeline    ][main] Starting pipeline {:pipeline_id=>"main", "pipeline.workers"=>4, "pipeline.batch.size"=>125, "pipeline.batch.delay"=>50, "pipeline.max_inflight"=>500, "pipeline.sources"=>["/Users/zhangzhiwen/gitlab/bigdata/logstash/logstash-mysql-china-city-to-es.conf"], :thread=>"#<Thread:0x32545496 run>"}
[2020-11-26T10:15:14,517][INFO ][logstash.javapipeline    ][main] Pipeline Java execution initialization time {"seconds"=>0.92}
[2020-11-26T10:15:14,829][INFO ][logstash.javapipeline    ][main] Pipeline started {"pipeline.id"=>"main"}
[2020-11-26T10:15:14,923][INFO ][logstash.agent           ] Pipelines running {:count=>1, :running_pipelines=>[:main], :non_running_pipelines=>[]}
[2020-11-26T10:15:15,331][INFO ][logstash.agent           ] Successfully started Logstash API endpoint {:port=>9600}
Thu Nov 26 10:15:16 CST 2020 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
[2020-11-26T10:15:24,933][INFO ][logstash.inputs.jdbc     ][main][822c6377c47657d32b66b5427a48e6201393f854946aef48d960594d120b7a74] (8.128913s) SELECT * FROM node
[2020-11-26T10:17:24,854][INFO ][logstash.javapipeline    ][main] Pipeline terminated {"pipeline.id"=>"main"}
[2020-11-26T10:17:25,173][INFO ][logstash.runner          ] Logstash shut down.
```