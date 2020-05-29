package startProvider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class providerTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context=new
                ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("启动provider向Zookeeper注册服务");
        System.in.read();
    }
}
