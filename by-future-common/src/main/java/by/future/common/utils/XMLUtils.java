package by.future.common.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.*;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * xml工具类
 *
 * @author by@Deng
 * @create 2017-11-08 10:20
 */
public class XMLUtils {

    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                //增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    /**
     * 对象转为xml
     *
     * @author by@Deng
     * @date 2017/11/9 下午2:23
     */
    public static String objectToXML(Object object) {
        xstream.alias("xml", object.getClass());
        String xmlTemp = xstream.toXML(object);
        String xml = xmlTemp.replace("__", "_").replace("<![CDATA[", "").replace("]]>", "");
        return xml;
    }


    /**
     * 字符串xml转成map
     *
     * @param xml
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(String xml) throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();

        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }


    /**
     * 将字符串XML转为map
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 上午10:38
     */
    public static Map<Object, Object> getXML(String requestXml) {
        Map<Object, Object> map = new HashMap<>();

        Document doc;
        try {
            doc = DocumentHelper.parseText(requestXml);
            // 获取根节点
            Element rootElm = doc.getRootElement();//从root根节点获取请求报文

            map = parseXML(rootElm, new HashMap<>());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 将xml解析成map键值对
     *
     * @param ele 需要解析的xml对象
     * @param map 入参为空，用于内部迭代循环使用
     */
    private static Map<Object, Object> parseXML(Element ele, Map<Object, Object> map) {
        for (Iterator<?> i = ele.elementIterator(); i.hasNext(); ) {
            Element node = (Element) i.next();
            if (node.attributes() != null && node.attributes().size() > 0) {
                for (Iterator<?> j = node.attributeIterator(); j.hasNext(); ) {
                    Attribute item = (Attribute) j.next();

                    map.put(item.getName(), item.getValue());
                }
            }
            if (node.getText().length() > 0) {
                map.put(node.getName(), node.getText());
            }
            if (node.elementIterator().hasNext()) {
                parseXML(node, map);
            }
        }
        return map;
    }


}
