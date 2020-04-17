package cn.jast.mr.nline;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NLineMapper extends Mapper<LongWritable,Text,Text,LongWritable>{

    private Text k = new Text();
    private LongWritable v = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
            throws IOException, InterruptedException {

        // 1.获取一行
        String line = value.toString();

        // 2.切割字符
        String[] splited = line.split(" ");

        //3. 将每个字符写出
        for (int i = 0; i < splited.length; i++) {
            k.set(splited[i]);
            context.write(k, v);
        }
    }

}