package cn.jast.mr.serialize;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 输入：
 *      7	13265642333	120.196.100.99	1116	954	200
 * 
 * 输出：
 * 
 * 为什么 KEYIN 用 LongWritable？
 */
public class FlowCountMapper extends Mapper<LongWritable,Text,Text,FlowBean>{
    
    FlowBean v = new FlowBean();
    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");
        String phoneNum = fields[1];
        long upFlow = Integer.parseInt(fields[2]);
        long downFlow = Integer.parseInt(fields[3]);
        k.set(phoneNum);
        v.setUpFlow(upFlow);
        v.setDownFlow(downFlow);
        context.write(k, v);
    }
}