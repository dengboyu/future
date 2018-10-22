package by.future.servicebiz.jstorm.word2.bolt;


import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author：by@Deng
 * @Date：2018/10/22 15:03
 */
public class CountBolt implements IBasicBolt {

    //生成一个serialVersionUID，因为这些class都是要经过序列化的
    private static final long serialVersionUID = 8740926838799779884L;


    Map<String,Integer> map = new HashMap<>();
    private FileWriter writer;

    public CountBolt() {
        System.out.println("***********************CountBolt创建完成");
    }


    /**
     * 仅仅启动一个文件写的定时线程，每2s将结果写到文件中，并清空map
     *
     * @Author：by@Deng
     * @Date：2018/10/22 15:06
     */
    @Override
    public void prepare(Map stormConf, TopologyContext topologyContext) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        try {
            //以文件追加的方式打开文件
            writer = new FileWriter("d://my_log.txt",true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //开启定时线程
        pool.scheduleAtFixedRate(()->{
            try {
                writer.write("\r\n");
                writer.write("************************");

                for(Map.Entry<String, Integer> entry:map.entrySet()) {
                    writer.write(entry.getKey()+" : "+entry.getValue());
                    writer.write("\r\n");
                    System.out.println(entry.getKey()+" : "+entry.getValue());
                }
                writer.flush();
                map.clear();


            } catch (IOException e) {
                e.printStackTrace();
            }
        },2000L,2000L, TimeUnit.MILLISECONDS);


    }


    /**
     * 收到spout发送来的word进行统计
     *
     * @Author：by@Deng
     * @Date：2018/10/22 15:15
     */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {

        String word = tuple.getString(0);
        if(map.get(word)==null){
            map.put("word",1);
        }else {
            map.put(word,map.get(word)+1);
        }

    }


    /**
     * Topology 被 shutdown时会被触发的动作，我们可以用来做一些清理工作
     * 
     * @Author：by@Deng
     * @Date：2018/10/22 15:22
     */
    @Override
    public void cleanup() {
        System.out.println("*******************public void cleanup()");
        for(Map.Entry<String, Integer> entry:map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        map.clear();

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
