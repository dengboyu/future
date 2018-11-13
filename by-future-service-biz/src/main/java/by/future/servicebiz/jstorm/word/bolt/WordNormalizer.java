package by.future.servicebiz.jstorm.word.bolt;


import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author：by@Deng
 * @Date：2018/10/18 14:14
 */
public class WordNormalizer implements IRichBolt {


    private OutputCollector outputCollector;


    /**
     * bolt最重要的方法，每当接收到一个tuple时，此方法便被调用
     * 该方法作用就是把文本中的每一行切分成一个个单词，并把这些单词发射出去(给下一个bolt处理)
     *
     * @Author：by@Deng
     * @Date：2018/10/18 14:15
     */
    @Override
    public void execute(Tuple tuple) {

        String sentence = tuple.getString(0);
        String[] words = sentence.split(" ");
        for(String word :words){
            word = word.trim();
            if(!word.isEmpty()){
                word = word.toLowerCase();

                //emit the word
                List wordList = new ArrayList();
                wordList.add(tuple);
                outputCollector.emit(wordList,new Values(word));
            }
        }

        //确认成功处理一个tuple
        outputCollector.ack(tuple);

    }


    /**
     * 当task起来后执行的初始化动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:59
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }


    /**
     * 当task被shutdown后执行的动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 10:04
     */
    @Override
    public void cleanup() {

    }


    /**
     *  定义bolt发送数据，每个字段的含义
     *
     * @Author：by@Deng
     * @Date：2018/10/19 10:19
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }


    /**
     * 获取本bolt的component 配置
     *
     * @Author：by@Deng
     * @Date：2018/10/19 10:19
     */
    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
