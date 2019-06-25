package by.future.servicebiz.flink.wordcount;

import by.future.common.utils.StringUtil;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Service;

/**
 * 创建flink
 *
 * @author by@Deng
 * @create 2019-06-02 21:45
 */
@Service
public class FlinkInstance {

    /**
     * 批处理
     *
     * @Author: by@Deng
     * @Date: 2019-06-03 00:51
     */
    public static ExecutionEnvironment getFlinkBatchInstance(String filePath) {

        try {

            //创建上下文
            ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();

            //读数据
            DataSource<String> text = executionEnvironment.readTextFile(filePath);

            //对数据进行转换
            text.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
                @Override
                public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                    String[] tokenStr  = s.split("\t");
                    for(String token:tokenStr){
                        collector.collect(new Tuple2<>(token,1));
                    }
                }
            }).groupBy(0).sum(1).print();

            //执行program


            return executionEnvironment;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return null;
    }


    /**
     * 处理实时数据
     *
     * @Author: by@Deng
     * @Date: 2019-06-03 00:52
     */
    public static StreamExecutionEnvironment getStreamInstance() throws Exception {

        //获取流处理上下文
        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> text = executionEnvironment.socketTextStream("localhost",9999);

        text.flatMap((String value,Collector<String> out)->{
            String[] tokenStr  = value.split(",");
            for(String token:tokenStr){
                if(StringUtil.isNotEmpty(token)){
                    out.collect(token);
                }
            }
        }).returns(Types.STRING).print().setParallelism(1);

        executionEnvironment.execute("FlinkInstance");

        return executionEnvironment;
    }
}
