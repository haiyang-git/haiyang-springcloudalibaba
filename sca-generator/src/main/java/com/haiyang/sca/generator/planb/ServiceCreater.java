package com.haiyang.sca.generator.planb;


import java.time.LocalDateTime;
import java.util.List;

public class ServiceCreater {
    protected static boolean createServiceInterface(String serviceInterfacePackageName,
                                                    String serviceInterfaceClassName, String authorName, String encodingStr) {
        StringBuffer sb = new StringBuffer();

        sb.append("package " + serviceInterfacePackageName + ";\r\n\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + serviceInterfaceClassName + "接口\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        // 实体部分
        sb.append("public interface " + serviceInterfaceClassName + " {\r\n");
        sb.append("\t\r\n");
        sb.append("}");

        // 生成实体类文件
        String outputPath = "/src/main/java/" + serviceInterfacePackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, serviceInterfaceClassName + ".java", sb.toString(), encodingStr);
    }

    protected static boolean createServiceImpl(String serviceImplPackageName, String serviceImplClassName,
                                               String serviceInterfaceClass, String serviceInterfaceClassName, List<String> daoClasses, String authorName,
                                               String encodingStr) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbTemp = new StringBuffer();
        sb.append("package " + serviceImplPackageName + ";\r\n\r\n");
        sb.append("import javax.annotation.Resource;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import " + serviceInterfaceClass + ";\r\n");
        for (String daoClass : daoClasses) {
            sb.append("import " + daoClass + ";\r\n");
            String daoClassName = daoClass.substring(daoClass.lastIndexOf(".") + 1, daoClass.length());
            sbTemp.append("\t@Resource\r\n");
            sbTemp.append("\tprivate " + daoClassName + " " + GenUtil.initialsLower(daoClassName) + ";\r\n\r\n");
        }
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + serviceImplClassName + "实现类\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        sb.append("@Service\r\n");
        sb.append("public class " + serviceImplClassName + " implements " + serviceInterfaceClassName + " {\r\n");
        sb.append("\r\n");
        sb.append(sbTemp);
        sb.append("}\r\n");

        // 生成实体类文件
        String outputPath = "/src/main/java/" + serviceImplPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, serviceImplClassName + ".java", sb.toString(), encodingStr);
    }
}
