package com.haiyang.sca.generator.planb;



import java.time.LocalDateTime;


public class ControllerCreater {
    protected static boolean createController(String controllerPackageName, String controllerClassName,
                                              String requestMappingUrl, String serviceInterfaceClass,
                                              String serviceInterfaceClassName,
                                              String authorName,
                                              String encodingStr) {
        StringBuffer sb = new StringBuffer();

        sb.append("package " + controllerPackageName + ";\r\n\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMethod;\r\n");
        sb.append("import org.springframework.context.annotation.Scope;\r\n");

        sb.append("import org.apache.dubbo.config.annotation.DubboReference;\r\n");

        sb.append("import " + serviceInterfaceClass + ";\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + controllerClassName + "控制器\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        sb.append("@RestController\r\n");
        sb.append("@Scope(\"prototype\")\r\n");
        sb.append("@RequestMapping(\"/" + requestMappingUrl + "\")\r\n");
        sb.append("public class " + controllerClassName + " extends WebController {\r\n");
        sb.append("\r\n");
        sb.append("\t@DubboReference(version = \"1.0.0\", check = false, timeout = 60000)\r\n");
        sb.append("\tprivate " + serviceInterfaceClassName + " " + GenUtil.initialsLower(serviceInterfaceClassName)
                + ";\r\n");



        sb.append("\r\n");
        sb.append("}");

        // 生成实体类文件
        String outputPath = "/src/main/java/" + controllerPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, controllerClassName + ".java", sb.toString(), encodingStr);
    }
}
