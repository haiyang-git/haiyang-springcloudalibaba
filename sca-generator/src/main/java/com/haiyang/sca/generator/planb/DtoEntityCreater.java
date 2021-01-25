package com.haiyang.sca.generator.planb;
import java.time.LocalDateTime;
import java.util.List;


public class DtoEntityCreater {

    protected static boolean createDtoEntity(List<Columns> lstCols, String dtoPackageName,
                                             String dtoClassName, String authorName, String encodingStr) {
        StringBuffer sb = new StringBuffer();
        String packageStr = "package " + dtoPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + dtoClassName + "实体类\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        // 实体部分
        sb.append("@Data\r\n");
        sb.append("public class " + dtoClassName + " implements Serializable {\r\n");
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
        String outputPath = "/src/main/java/" + dtoPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, dtoClassName + ".java", sb.toString(), encodingStr);
    }

    protected static boolean createDtoMapper(String dtoMapperPackageName,
                                             String dtoMapperClassName, String dtoClass, String dtoClassName,
                                             String modelClass, String modelClassName, String authorName,
                                             String encodingStr) {
        StringBuffer sb = new StringBuffer();
        String packageStr = "package " + dtoMapperPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        sb.append("import org.mapstruct.Mapper;\r\n");
        sb.append("import " + dtoClass + ";\r\n");
        sb.append("import " + modelClass + ";\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + dtoMapperClassName + "映射文件\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        //实体部分
        sb.append("@Mapper(componentModel = \"spring\")\r\n");
        sb.append("public interface " + dtoMapperClassName + " extends BaseModel2DTOMapper<" + modelClassName + ", " + dtoClassName + ">" +
                " " +
                "{\r\n");
        sb.append("\r\n");
        sb.append("}\r\n");
        // 生成实体类文件
        String outputPath = "/src/main/java/" + dtoMapperPackageName.replace(".", "/");
        return  GenUtil.outpubFile(outputPath, dtoMapperClassName + ".java", sb.toString(), encodingStr);
    }
}
