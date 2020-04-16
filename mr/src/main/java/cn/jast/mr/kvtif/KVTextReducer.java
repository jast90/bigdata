package cn.jast.mr.kvtif;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KVTextReducer extends Reducer<Text,LongWritable,Text,LongWritable>{

    LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values,
            Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable value : values) {
            sum += value.get();
        }
        v.set(sum);
        context.write(key, v);
    }
}