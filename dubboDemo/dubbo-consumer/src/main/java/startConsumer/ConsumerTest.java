package startConsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.HelloDemo;

import java.io.IOException;

public class ConsumerTest {
    public static void main(String[] args) throws IOException {
        System.out.println("consumer启动成功开始向注册中心拿到数据。。。");
        ClassPathXmlApplicationContext context =new
                ClassPathXmlApplicationContext("consumer.xml");
        HelloDemo helloDemo= (HelloDemo) context.getBean("providerDemo");
        String hello=helloDemo.sayHello("Dubbo测试成功");
        System.out.println(hello);
        System.in.read();
    }


}
