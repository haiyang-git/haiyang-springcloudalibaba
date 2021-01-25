package com.haiyang.sca.generator.planb;

import java.time.LocalDateTime;
import java.util.List;


public class EntityCreater {

    protected static boolean createEntity(List<Columns> lstCols, String modelPackageName, String tableName,
                                          String modelClassName, String authorName, String encodingStr) {
        StringBuffer sb = new StringBuffer();
//        StringBuffer sbGetSet = new StringBuffer();
        String packageStr = "package " + modelPackageName + ";\r\n\r\n";
        sb.append(packageStr);
        sb.append("import com.baomidou.mybatisplus.annotation.TableField;\r\n");
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("\r\n");
        sb.append("/**\r\n");
        sb.append(" * " + modelClassName + "实体类\r\n");
        sb.append(" * @author " + authorName + "\r\n");
        sb.append(" * @version 1.0 \r\n");
        sb.append(" * @date " + LocalDateTime.now() + "\r\n");
        sb.append(" */\r\n");
        // 实体部分
        sb.append("@Data\r\n");
        sb.append("@TableName(value=\"" + tableName + "\")\r\n");
        sb.append("public class " + modelClassName + " extends BaseModel {\r\n");
//        sb.append("{\r\n");
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
                sb.append("\t@TableField(value=\"" + col.getColName() + "\")\r\n");
                sb.append("\tprivate " + attribType + " " + attribName + ";");
            } else {
                sb.append("\t@TableField(value=\"" + col.getColName() + "\")\r\n");
                sb.append("\tprivate " + attribType + " " + attribName + ";");
            }
            sb.append("\r\n");

            // 生成get set
//            sbGetSet.append(strCommentNotes);
//            sbGetSet.append("\r\n\tpublic " + attribType + " get" + GenUtil.initialsUpper(attribName) + "() {\r\n");
//            sbGetSet.append("\t\treturn " + attribName + ";\r\n");
//            sbGetSet.append("\t}\r\n");
//            sbGetSet.append(strCommentNotes);
//            sbGetSet.append("\r\n\tpublic void set" + GenUtil.initialsUpper(attribName) + "(" + attribType + " "
//                    + attribName + ") {\r\n");
//            sbGetSet.append("\t\tthis." + attribName + " = " + attribName + ";\r\n");
//            sbGetSet.append("\t}\r\n");
        }
//        sb.append(sbGetSet);
        sb.append("}");

        if (sb.indexOf("private Date ") >= 0) {
            int start = sb.indexOf(packageStr) + packageStr.length();
            sb.insert(start, "import java.util.Date;\r\n");
        }

        // 生成实体类文件
        String outputPath = "/src/main/java/" + modelPackageName.replace(".", "/");
        return GenUtil.outpubFile(outputPath, modelClassName + ".java", sb.toString(), encodingStr);
    }
}
