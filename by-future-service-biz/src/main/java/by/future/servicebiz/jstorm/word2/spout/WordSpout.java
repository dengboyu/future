package by.future.servicebiz.jstorm.word2.spout;


import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.apache.commons.lang3.RandomUtils;

import java.util.Map;

/**
 * @Author：by@Deng
 * @Date：2018/10/22 14:08
 */
public class WordSpout implements IRichSpout {


    //生成一个serialVersionUID，因为这些class 都是要经过序列化的
    private static final long serialVersionUID = -4515102038086645770L;

    private String[] strs = {"one","two","three","four","five","six"};
    private SpoutOutputCollector spoutOutputCollector;


    public WordSpout() {
        System.out.println("*******************************wordSpout()创建完成");
    }


    /**
     * 打开stream资源，只会执行一次
     *
     * @Author：by@Deng
     * @Date：2018/10/22 14:34
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        System.out.println("*****************open(Map conf, TopologyContext context, SpoutOutputCollector collector)");
        this.spoutOutputCollector=spoutOutputCollector;
    }


    @Override
    public void close() {

    }


    @Override
    public void activate() {

    }


    @Override
    public void deactivate() {

    }


    /**
     * 执行循环，向外发送Tuple
     *
     * @Author：by@Deng
     * @Date：2018/10/22 14:45
     */
    @Override
    public void nextTuple() {
        int index = RandomUtils.nextInt(6,199);
        spoutOutputCollector.emit(new Values(strs[index]));
        System.out.println("**************nextTuple():"+strs[index]);
    }


    @Override
    public void ack(Object o) {

    }


    @Override
    public void fail(Object o) {

    }


    /**
     * 定义发射的字段类型，是第一个要是执行的方法
     *
     * @Author：by@Deng
     * @Date：2018/10/22 14:33
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }



    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }


}
