package by.future.servicebiz.jstorm.word.spout;


import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

/**
 * 读取一个文本文件并把它的每行内容发送给bolt
 *
 * @Author：by@Deng
 * @Date：2018/10/18 13:48
 */
public class WordReader implements IRichSpout {

    private SpoutOutputCollector collector;
    private FileReader fileReader;
    private boolean completed = false;


    /**
     * open是task起来后执行的初始化动作
     *
     * 接收了三个参数，第一个是创建Topology时的配置，
     * 第二个是所有的Topology数据，第三个是用来把Spout的数据发射给bolt
     **/
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        try {

            //获取创建Topology时指定的要读取的文件路径
            fileReader = new FileReader(map.get("wordsFile").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化发射器
        collector = spoutOutputCollector;
    }


    /**
     * 这是Spout最主要的方法，在这里读取文本文件，并把它的每一行发射出去（给bolt）
     * 这个方法会不断被调用，为了降低它对CPU的消耗，当任务完成时让它sleep一下
     **/
    @Override
    public void nextTuple() {

        try{

            if(completed){
                Thread.sleep(1000);
            }

            String str;
            BufferedReader reader = new BufferedReader(fileReader);

            while ((str = reader.readLine()) != null){
                // 发射每一行，values是一个ArrayList实现
                collector.emit(new Values(str),str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            completed = true;
        }
    }


    /**
     * 当task被shutdown后执行
     * 
     * @Author：by@Deng
     * @Date：2018/10/19 9:52
     */
    @Override
    public void close() {

    }


    /**
     * 当task被激活时，触发的动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:52
     */
    @Override
    public void activate() {

    }


    /**
     * 是task被deactive时，触发的动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:53
     */
    @Override
    public void deactivate() {

    }


    /**
     * 当spout收到一条ack消息时，触发的动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:53
     */
    @Override
    public void ack(Object o) {
        System.out.println("OK:" + o);
    }


    /**
     * 当spout收到一条fail消息时，触发的动作
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:53
     */
    @Override
    public void fail(Object o) {
        System.out.println("FAIL:" + o);
    }


    /**
     *  定义spout发送数据，每个字段的含义
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:51
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }


    /**
     * 获取本spout的component 配置
     *
     * @Author：by@Deng
     * @Date：2018/10/19 9:54
     */
    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

}
