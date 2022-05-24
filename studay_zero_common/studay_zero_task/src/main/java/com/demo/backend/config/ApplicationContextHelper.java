package com.demo.backend.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class ApplicationContextHelper  implements ApplicationContextAware {
    private static String XMLName = "applicationContext.xml";
    private static ApplicationContext applicationContext = null;

    private static class ApplicationContextHolder {

        // 初始化 Context, 尝试搜索常用的存放 ApplicationContext.xml 的位置
        public static ApplicationContext Init() {
            ApplicationContext context = null;
            try {
                context = new ClassPathXmlApplicationContext(XMLName);
            } catch (Exception e) {}
            // 在当前项目中搜索配置文件
            if (context == null) {
                try {
                    String fileName = findFile(System.getProperty("user.dir"), XMLName);
                    if (fileName != null)
                        context = new FileSystemXmlApplicationContext(fileName);
                } catch (Exception e) {}
            }
            return context;
        }

    }

    // 私有化的构造方法，保证外部的类不能通过构造器来实例化。
    private ApplicationContextHelper() {}

    /** 设定 XML 名称 */
    synchronized public static void setXmlName(String name) {
        XMLName = name;
    }

    // 获取单例对象实例
    synchronized public static ApplicationContext getInstance() {
        if (applicationContext != null)
            return applicationContext;
        applicationContext = ApplicationContextHolder.Init();
        return applicationContext;
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return getInstance().containsBean(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     */
    public static Class<?> getType(String name) {
        try {
            return getInstance().getType(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     */
    public static String[] getAliases(String name)  {
        try {
            return getInstance().getAliases(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，也会返回 false
     * @param name
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        try {
            return getInstance().isSingleton(name);
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContextHelper setApplicationContext OK.");
    }

    /**
     * 查找文件
     * @param baseDirName  查找的文件夹路径
     * @param targetFileName  需要查找的文件名
     */
    public static String findFile(String baseDirName, String targetFileName) {
        List<File> files = new ArrayList<File>();
        findFiles(baseDirName, targetFileName, files, true);
        if (files.isEmpty())
            return null;
        return files.get(0).getPath();
    }

    /**
     * 递归查找文件
     * @param baseDirName  查找的文件夹路径
     * @param targetFileName  需要查找的文件名
     * @param fileList  查找到的文件集合
     * @param onlyFirst  是否是查找第一个
     */
    public static void findFiles(String baseDirName, String targetFileName, List<File> fileList, Boolean onlyFirst) {

        File baseDir = new File(baseDirName);       // 创建一个File对象
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // 判断目录是否存在
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
        }
        String tempName = null;
        //判断目录是否存在
        File tempFile;
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            if(tempFile.isDirectory()){
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList, onlyFirst);
            }else if(tempFile.isFile()){
                tempName = tempFile.getName();
                if (tempName != null && tempName.equalsIgnoreCase(targetFileName)) {
                    // 匹配成功，将文件名添加到结果集
                    fileList.add(tempFile.getAbsoluteFile());
                    if (onlyFirst)
                        return;
                }
            }
        }
    }

    /**
     * 获取 Bean
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getBean(String beanName) {
        try {
            return (T)getInstance().getBean(beanName);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取 Bean
     * @param beanName
     * @param clazz
     * @return
     */
    public static <T extends Object> T getBean(String beanName , Class<T>clazz) {
        return getInstance().getBean(beanName , clazz);
    }

    /**
     * @param clazz 通过类模板获取该类
     * @return 该类的实例，默认单例
     */
    public static <T extends Object> T getBean(Class<T> clazz){
        try {
            return getInstance().getBean(clazz);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }
}
