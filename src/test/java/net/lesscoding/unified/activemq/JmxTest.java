package net.lesscoding.unified.activemq;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.lesscoding.unified.core.model.dto.activemq.ActiveMqJolokiaDto;
import net.lesscoding.unified.core.model.vo.activemq.ActiveMqServerInfo;
import net.lesscoding.unified.utils.activemq.RmiUtil;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author eleven
 * @date 2025/4/23 17:31
 * @apiNote
 */
public class JmxTest {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    @Deprecated
    public void p2pTest() throws JMSException {
        // 1. 创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 2. 创建连接
        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        // 3.打开连接
        connection.start();
        // 4. 创建session 参数1 是否开启事务  2. 消息确认模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5. 创建目标地址 Queue 点对点消息 Topic 发布订阅消息
        session.createQueue("testQueue");
    }

    @Test
    @Deprecated
    public void rmiTest() {
        ActiveMqServerInfo activeMqServerInfo = RmiUtil.serverInfo();
        System.out.println(gson.toJson(activeMqServerInfo));
    }

    /**
     * 获取Broker的基础信息：
     */
    @Test
    public void mbeanTest() {
        System.out.println("获取Broker的基础信息：");
        jolokiaTest(new ActiveMqJolokiaDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker,brokerName=localhost")
        );
    }

    /**
     * 搜索mbean信息：
     */
    @Test
    public void searchMbeanTest() {
        System.out.println("搜索mbean信息：");
        jolokiaTest(new ActiveMqJolokiaDto()
                .setType("search")
                .setMbean("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,*")
        );

    }

    /**
     * 获取Queue列表数据：
     */
    @Test
    public void queueTest() {
        System.out.println("获取Queue列表数据：");
        jolokiaTest(new ActiveMqJolokiaDto()
                .setType("read")
                .setMbean("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=*")
        );
    }

    @Test
    public void messageListTest() {
        System.out.println("获取Queue的信息列表：");
        jolokiaTest(new ActiveMqJolokiaDto()
                        .setType("exec")
                        .setMbean("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,destinationName=TestQueue")
                        .setOperation("browse()")
                //.setArguments(new ArrayList<>())
        );
    }


    private void jolokiaTest(ActiveMqJolokiaDto dto) {
        // ActiveMQ Jolokia REST API 端点
        String url = "http://127.0.0.1:8161/api/jolokia/";
        // 发送 POST 请求
        HttpResponse response = HttpRequest.post(url)
                .header("Origin", "*")
                .basicAuth("admin", "admin")
                .body(gson.toJson(dto))
                .execute();

        // 检查响应状态码
        if (response.isOk()) {
            // 打印响应结果
            System.out.println(response.body());
        } else {
            System.err.println("请求失败，状态码: " + response.getStatus());
        }
    }

    @Test
    public void htmlTest() {
        String body = HttpRequest.get("http://localhost:8161/admin/")
                .basicAuth("admin", "admin")
                .execute()
                .body();
        Document document = new Document(body);
        Elements tables = document.getElementsByTag("table");
        for (Element table : tables) {
            table.getElementsByTag("tr");
        }

    }


}
