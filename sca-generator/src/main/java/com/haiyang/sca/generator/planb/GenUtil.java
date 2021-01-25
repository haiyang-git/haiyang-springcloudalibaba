package com.haiyang.sca.generator.planb;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GenUtil {
    private static final String ENCODING_STRING = "UTF-8";

    private static final Pattern pattern = Pattern.compile("_");

    protected static boolean outpubFile(String outputPath, String fileName, String content, String encoding) {
        if (outputPath == null || outputPath.equals("")) {
            return false;
        }
        if (fileName == null || fileName.equals("")) {
            return false;
        }
        if (content == null || content.equals("")) {
            return false;
        }
        BufferedWriter writer = null;
        try {
            File directory = new File("");
            String filePath = directory.getAbsolutePath() + outputPath + "/" + fileName;
            File dir = new File(directory.getAbsolutePath() + outputPath);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    return false;
                }
            }
            File file = new File(filePath);
            if (file.exists()) {
                return false;
            }
            file.createNewFile();
//	        FileWriter fw = new FileWriter(file, true);
//	        pw = new PrintWriter(fw);
//	        pw.println(content);
//	        pw.flush();
//	        pw.close();
            if (StringUtils.isNotBlank(encoding)) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
            } else {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), ENCODING_STRING));
            }
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    protected static String sqlType2JavaType(String colType, int dataScale) {
        if (colType.equalsIgnoreCase("binary_double")) {
            return "double";
        } else if (colType.equalsIgnoreCase("binary_float")) {
            return "float";
        } else if (colType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (colType.equalsIgnoreCase("blob")) {
            return "byte[]";
        } else if (colType.equalsIgnoreCase("char") || colType.equalsIgnoreCase("nvarchar2")
                || colType.equalsIgnoreCase("varchar2")) {
            return "String";
        } else if (colType.equalsIgnoreCase("date") || colType.equalsIgnoreCase("timestamp")
                || colType.equalsIgnoreCase("timestamp with local time zone")
                || colType.equalsIgnoreCase("timestamp with time zone")) {
            return "Date";
        } else if (colType.equalsIgnoreCase("number")) {
            if (dataScale > 0) {
                return "double";
            } else {
                return "long";
            }
        }

        return "String";
    }

    /**
     * 列名转化为驼峰命名
     *
     * @param colName
     * @return
     */
    public static String formatAttrName(String colName) {
        if (colName == null || colName.equals("")) {
            return colName;
        }
        if (!colName.contains("_")) {
            return colName.toLowerCase();
        }
        StringBuilder sb = new StringBuilder(colName.toLowerCase());
        Matcher mc = pattern.matcher(colName);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 驼峰转化为列名或表名
     *
     * @param name
     * @return
     */
    public static String formatTCName(String name) {
        StringBuilder sb = new StringBuilder(name);
        // 定位
        int temp = 0;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isUpperCase(name.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }

    protected static String initialsUpper(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    protected static String initialsLower(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }
}
