package com.haiyang.sca.generator.planb;



import java.time.LocalDateTime;

public class DaoMapperCreater {
    protected static boolean createDao(String daoPackageName, String daoClassName, String modelClass, String authorName,
                                       String encodingStr) {
        StringBuffer sb = new StringBuffer();

        String modelClassName = modelClass.substring(modelClass.lastIndexOf(".") + 1, modelClass.length());
        String packageStr = "package " + daoPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        sb.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\r\n");
        sb.append("import " + modelClass + ";\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + daoClassName + "接口\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        // 实体部分
        sb.append("public interface " + daoClassName + " extends BaseMapper<" + modelClassName + "> {\r\n");
        sb.append("}");

        // 生成实体类文件
        String outputPath = "/src/main/java/" + daoPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, daoClassName + ".java", sb.toString(), encodingStr);
    }

    protected static boolean createMapper(String mapperPackageName, String mapperClassName, String daoClass,
                                          String tableName, String modelClassName, String encodingStr) {
        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        sb.append(
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("<mapper namespace=\"" + daoClass + "\">\r\n");
        sb.append("\r\n");
        sb.append("</mapper>");

        // 生成实体类文件
        String outputPath = "/src/main/java/" + mapperPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, mapperClassName + ".xml", sb.toString(), encodingStr);
    }
}
