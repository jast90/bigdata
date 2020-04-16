package cn.jast.mr.kvtif;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KVTextMapper extends Mapper<Text, Text, Text, LongWritable> {

    LongWritable v = new LongWritable(1);

    @Override
    protected void map(Text key, Text value, Mapper<Text, Text, Text, LongWritable>.Context context)
            throws IOException, InterruptedException {
        context.write(key, v);
    }
}