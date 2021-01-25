package com.haiyang.sca.generator.planb;


import java.time.LocalDateTime;
import java.util.List;


public class VoEntityCreater {

    protected static boolean createVoEntity(List<Columns> lstCols, String voPackageName,
                                            String voClassName, String authorName, String encodingStr) {
        StringBuffer sb = new StringBuffer();
        String packageStr = "package " + voPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        sb.append("import java.io.Serializable;\r\n");

        sb.append("import lombok.Data;\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + voClassName + "实体类\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        // 实体部分
        sb.append("@Data\r\n");
        sb.append("public class " + voClassName + " implements Serializable {\r\n");
        sb.append("\tprivate String id;\r\n");
        sb.append("\r\n");
        for (Columns col : lstCols) {
            if (col.getColName().toLowerCase().equals("id")) {
                continue;
            }

            String attribName = GenUtil.formatAttrName(col.getColName());
            String attribType = GenUtil.sqlType2JavaType(col.getColType(), col.getDataScale());
            String strCommentNotes = "";
            if (col.getColComments() != null && !col.getColComments().equals("")) {
                strCommentNotes = "\r\n\t/**";
                strCommentNotes = strCommentNotes + "\r\n\t * " + col.getColComments();
                strCommentNotes = strCommentNotes + "\r\n\t */\r\n";
                sb.append(strCommentNotes);
                sb.append("\tprivate " + attribType + " " + attribName + ";");
            } else {
                sb.append("\tprivate " + attribType + " " + attribName + ";");
            }
            sb.append("\r\n");
        }
        sb.append("}");

        if (sb.indexOf("private Date ") >= 0) {
            int start = sb.indexOf(packageStr) + packageStr.length();
            sb.insert(start, "import java.util.Date;\r\n");
        }

        // 生成实体类文件
        String outputPath = "/src/main/java/" + voPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, voClassName + ".java", sb.toString(), encodingStr);
    }

    protected static boolean createVoMapper(String voMapperPackageName,
                                            String voMapperClassName, String voClass, String voClassName,
                                            String dtoClass, String dtoClassName, String authorName,
                                            String encodingStr) {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbGetSet = new StringBuffer();
        String packageStr = "package " + voMapperPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        //sb.append("import com.***.BaseDTO2VOMapper;\r\n");
        sb.append("import org.mapstruct.Mapper;\r\n");
        sb.append("import " + voClass + ";\r\n");
        sb.append("import " + dtoClass + ";\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + voMapperClassName + "映射文件\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        //实体部分
        sb.append("@Mapper(componentModel = \"spring\")\r\n");
        sb.append("public interface " + voMapperClassName + " extends BaseDTO2VOMapper<" + dtoClassName + ", " +  voClassName + "> " +
                "{\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        // 生成实体类文件
        String outputPath = "/src/main/java/" + voMapperPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, voMapperClassName + ".java", sb.toString(), encodingStr);
    }
}
