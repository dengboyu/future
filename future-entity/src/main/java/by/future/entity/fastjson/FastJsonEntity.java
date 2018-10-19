package by.future.entity.fastjson;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * FastJSON测试
 *
 * @Author：by@Deng
 * @Date：2018/10/11 14:28
 */
public class FastJsonEntity implements Serializable {

    private static final long serialVersionUID = -5182532647273106745L;

    @JSONField(name="ID")
    private int id; //序列化名字


    @JSONField(name = "nowDate",format = "yyyy-MM-dd HH:mm")
    private Date date; //序列出日期格式


    @JSONField(serialize = false)
    private Date notSerialize;  //不序列化该字段

    @JSONField(deserialize = false)
    private String notDeserialize; //不反序列化该字段


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getNotSerialize() {
        return notSerialize;
    }

    public void setNotSerialize(Date notSerialize) {
        this.notSerialize = notSerialize;
    }

    public String getNotDeserialize() {
        return notDeserialize;
    }

    public void setNotDeserialize(String notDeserialize) {
        this.notDeserialize = notDeserialize;
    }
}
