package org.cuinfo.rmiclienttest;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.cuinfo.pojo.User;
import org.cuinfo.service.BaseInformationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        Date startTime=new Date();
        ApplicationContext context=null;
        try{
         context = new ClassPathXmlApplicationContext(new String[]{
                    "rmi-client.xml"
                });
        }catch(Exception e){
            e.printStackTrace();
            LOG.severe("初始化远程服务出错！");
            return;
        }
        BaseInformationService bis = (BaseInformationService) context.getBean("baseInformationService");
  
            

        List<User> us = bis.getAll();



        if (us.isEmpty()) {
            User u = new User("細細", "123");
            bis.save(u);
            us = bis.getAll();
        }

        LOG.info("获得数据条数为：" + us.size());
        for (User u : us) {
            print(u);
        }
        LOG.info("用时�? +(new Date().getTime()-startTime.getTime()));
    }

    private static void print(User u) {
        System.out.println(u);
    }
}
