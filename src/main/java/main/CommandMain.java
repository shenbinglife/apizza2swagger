package main;

import apizza2swagger.ApizzaHelper;
import apizza2swagger.domain.Converter;
import apizza2swagger.domain.apizza.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/3/21
 * @since since
 */
public class CommandMain {

    private static final String DEFAULT_EMAIL = "shenbinglife@163.com";
    private static final String DEFAULT_PASSWORD = "!@#$%";

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws Exception {
        Scanner sb = new Scanner(System.in);
        boolean loop = true;

       while (loop) {
           System.out.println("---------------Apizza导出为Swagger工具------------------");
           System.out.print("启用默认？回车启用默认用户，输入任意值则不启用默认。");
           String enable = sb.nextLine();
           boolean enableDefault  = StringUtils.isBlank(enable);

           String email,password,exportPath;

           if(enableDefault) {
               email = DEFAULT_EMAIL;
               password = DEFAULT_PASSWORD;
               exportPath = "C:\\Users\\shenb\\Desktop\\swagger";
           } else {
               System.out.print("输入你的邮箱：");
               email = sb.nextLine();

               while (StringUtils.isBlank(email)) {
                   System.out.print("输入你的邮箱：");
                   email = sb.nextLine();
               }

               System.out.print("输入你的密码：");
               password = sb.nextLine();

               while (StringUtils.isBlank(password)) {
                   System.out.print("输入你的密码：");
                   password = sb.nextLine();
               }

               System.out.print("输入导出文件夹路径：");
               exportPath = sb.nextLine();

               while (StringUtils.isBlank(exportPath)) {
                   System.out.print("输入导出文件夹路径：");
                   exportPath = sb.nextLine();
               }
           }

           ApizzaHelper apizzaHelper = new ApizzaHelper(email, DigestUtils.md5Hex(password.getBytes()), exportPath);
//           List<Project> project = apizzaHelper.getProject();
//           System.out.println(gson.toJson(project));

           Converter converter = new Converter();
           converter.convert(apizzaHelper, exportPath);

           System.out.print("退出吗？输入任意值退出, 回车重新开始。");
           String exit = sb.nextLine();
           loop = StringUtils.isBlank(exit);
           System.out.println("\n");
       }
    }
}
