package com.haiyang.sca.generator.planb;


import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自动生成代码类，支持多表生成，支持指定层（service，dao，model...）生成
 */
public class GeneratorSourceCode {
    private String dbUrl = "";
    private String dbName = "";
    private String dbPassword = "";
    private String basePackage = "";
    private String moduleUnitName = "";
    private String authorName = "";
    private List<Map<String, String>> lstTableClasses;
    private final String modelPackageName = ".model";
    private final String daoPackageName = ".dao";
    private final String servicePackageName = ".service";
    private final String businessImplPackageName = ".web.business";
    private final String serviceInterfacePackageName = ".api";
    private final String businessInterfacePackageName = ".web.business";
    private final String controllerPackageName = ".web.controller";
    private final String voPackageName = ".vo";
    private final String dtoPackageName = ".dto";

    /**
     * 创建源代码，使用该方法之前，请设置所需的参数
     */
    public void create() {
        System.out.println("开始创建源代码文件...");
        List<String> daoClasses = new ArrayList<>();
        List<String> serviceClasses = new ArrayList<>();
        for (Map map : lstTableClasses) {
            String tableName = map.get("tableName").toString();
            String charset = map.get("charset") == null ? "" : map.get("charset").toString();
            // 读取所有的字段
            List<Columns> lstCols = null;
            if (this.dbUrl.indexOf(":oracle:") >= 0) {
                lstCols = this.getColumnsByOracle(tableName);
            }
            if (lstCols.size() <= 0) {
                return;
            }

            // 生成实体类
            String modelPackageName = this.basePackage + this.modelPackageName + "." + this.moduleUnitName;
            String modelClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Model");
            String modelClass = modelPackageName + "." + modelClassName;
            System.out.println("");
            System.out.println("开始创建实体类" + modelClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateModel") + "") || "true".equals(map.get("isCreateModel"))) {
                if (EntityCreater.createEntity(lstCols, modelPackageName, tableName, modelClassName, authorName,
                        charset)) {
                    System.out.println("实体类" + modelClassName + "创建成功！");
                } else {
                    System.out.println("实体类" + modelClassName + "创建失败！！！");
                }
            }

            //生成dto 及mapper
            String dtoPackageName = this.basePackage + this.dtoPackageName + "." + this.moduleUnitName;
            String dtoMapperPackageName = dtoPackageName;
            String dtoClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Dto");
            String dtoMapperClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Model2DTOMapper");
            String dtoClass = dtoPackageName + "." + dtoClassName;
            System.out.println("");
            System.out.println("开始创建实体类" + dtoClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateDto") + "") || "true".equals(map.get("isCreateDto"))) {
                if (DtoEntityCreater.createDtoEntity(lstCols, dtoPackageName, dtoClassName, authorName,
                        charset)) {
                    System.out.println("实体类" + dtoClassName + "创建成功！");
                } else {
                    System.out.println("实体类" + dtoClassName + "创建失败！！！");
                }

                if (DtoEntityCreater.createDtoMapper(dtoMapperPackageName,
                        dtoMapperClassName, dtoClass, dtoClassName,
                        modelClass, modelClassName, authorName,
                        charset)) {
                    System.out.println("mapstruct映射类" + dtoMapperClassName + "创建成功！");
                } else {
                    System.out.println("mapstruct映射类" + dtoMapperClassName + "创建失败！！！");
                }
            }

            //生成vo 及mapper
            String voPackageName = this.basePackage + this.voPackageName + "." + this.moduleUnitName;
            String voMapperPackageName = voPackageName;
            String voClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "VO");
            String voMapperClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "DTO2VOMapper");
            String voClass = voPackageName + "." + voClassName;
            System.out.println("");
            System.out.println("开始创建实体类" + modelClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateVo") + "") || "true".equals(map.get("isCreateVo"))) {
                if (VoEntityCreater.createVoEntity(lstCols, voPackageName, voClassName, authorName,
                        charset)) {
                    System.out.println("VO类" + voClassName + "创建成功！");
                } else {
                    System.out.println("VO类" + voClassName + "创建失败！！！");
                }

                if (VoEntityCreater.createVoMapper(voMapperPackageName,
                        voMapperClassName, voClass, voClassName,
                        dtoClass, dtoClassName, authorName,
                        charset)) {
                    System.out.println("mapstruct映射类" + voMapperClassName + "创建成功！");
                } else {
                    System.out.println("mapstruct映射类" + voMapperClassName + "创建失败！！！");
                }
            }

            // 生成dao
            String daoPackageName = this.basePackage + this.daoPackageName + "." + this.moduleUnitName;
            String daoClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Dao");
            String daoClass = daoPackageName + "." + daoClassName;
            if (StringUtils.isEmpty(map.get("isCreateDao") + "") || "true".equals(map.get("isCreateDao"))) {
                System.out.println("");
                System.out.println("开始创建" + daoClassName + "...");
                if (DaoMapperCreater.createDao(daoPackageName, daoClassName, modelClass, authorName, charset)) {
                    System.out.println(daoClassName + "创建成功！");
                } else {
                    System.out.println(daoClassName + "创建失败！！！");
                }
                daoClasses.add(daoClass);

                // 生成mapper
                String mapperPackageName = this.basePackage + this.daoPackageName + "." + this.moduleUnitName
                        + ".mapper";
                System.out.println("");
                System.out.println("开始创建" + daoClassName + "Mapper...");
                if (DaoMapperCreater.createMapper(mapperPackageName, daoClassName, daoClass, tableName, modelClassName,
                        charset)) {
                    System.out.println(daoClassName + "Mapper创建成功！");
                } else {
                    System.out.println(daoClassName + "Mapper创建失败！！！");
                }
            }

            // 生成service
            String serviceInterfacePackageName = this.basePackage + this.serviceInterfacePackageName + "." + this.moduleUnitName;
            String serviceInterfaceClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Service");
            String serviceInterfaceClass = serviceInterfacePackageName + "." + serviceInterfaceClassName;
            System.out.println("");
            System.out.println("开始创建" + serviceInterfaceClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateService") + "") || "true".equals(map.get("isCreateService"))) {
                if (ServiceCreater.createServiceInterface(serviceInterfacePackageName, serviceInterfaceClassName,
                        authorName, charset)) {
                    System.out.println(serviceInterfaceClassName + "创建成功！");
                } else {
                    System.out.println(serviceInterfaceClassName + "创建失败！！！");
                }
                serviceClasses.add(serviceInterfaceClass);
            }

            // 生成serviceImpl
            String serviceImplPackageName = this.basePackage + this.servicePackageName + "." + this.moduleUnitName;
            String serviceImplClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "ServiceImpl");
            System.out.println("");
            System.out.println("开始创建" + serviceImplClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateServiceImpl") + "")
                    || "true".equals(map.get("isCreateServiceImpl"))) {
                if (ServiceCreater.createServiceImpl(serviceImplPackageName, serviceImplClassName,
                        serviceInterfaceClass, serviceInterfaceClassName, daoClasses, authorName, charset)) {
                    System.out.println(serviceImplClassName + "创建成功！");
                } else {
                    System.out.println(serviceImplClassName + "创建失败！！！");
                }
            }


            // 生成controller
            String controllerPackageName = this.basePackage + this.controllerPackageName + "." + this.moduleUnitName;
            String controllerClassName = GenUtil.initialsUpper(map.get("classNamePrefix").toString() + "Controller");
            System.out.println("开始创建" + controllerClassName + "...");
            if (StringUtils.isEmpty(map.get("isCreateController") + "") || "true".equals(map.get("isCreateController"))) {
                if (ControllerCreater.createController(controllerPackageName, controllerClassName, moduleUnitName,
                        serviceInterfaceClass, serviceInterfaceClassName, authorName,
                        charset)) {
                    System.out.println(controllerClassName + "创建成功！");
                } else {
                    System.out.println(controllerClassName + "创建失败！！！");
                }
            }
        }

        System.out.println("");
        System.out.println("源代码文件创建结束！");
    }

    private List<Columns> getColumnsByOracle(String tableName) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        List<Columns> lstCols = new ArrayList<Columns>();
        Connection con;
        // 查要生成实体类的表
        StringBuilder sb = new StringBuilder();
        sb.append("select t.COLUMN_NAME as colName, t.DATA_TYPE as colType");
        sb.append(", t.DATA_LENGTH as dataLength, t.DATA_PRECISION as dataPre");
        sb.append(", t.DATA_SCALE as dataScale, c.comments as colComments" + "  from user_tab_columns t");
        sb.append("  left join user_col_comments c");
        sb.append("    on c.table_name = t.TABLE_NAME and c.column_name = t.COLUMN_NAME" + " where t.TABLE_NAME = '");
        sb.append(tableName);
        sb.append("'");
        Statement pStemt = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);
            pStemt = (Statement) con.createStatement();
            ResultSet rs = pStemt.executeQuery(sb.toString());
            while (rs.next()) {
                Columns col = new Columns();
                col.setColName(rs.getString("colName"));
                col.setColType(rs.getString("colType"));
                col.setDataLength(rs.getInt("dataLength"));
                col.setDataPre(rs.getInt("dataPre"));
                col.setDataScale(rs.getInt("dataScale"));
                col.setColComments(rs.getString("colComments"));
                lstCols.add(col);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCols;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getModuleUnitName() {
        return moduleUnitName;
    }

    public void setModuleUnitName(String moduleUnitName) {
        this.moduleUnitName = moduleUnitName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Map<String, String>> getLstTableClasses() {
        return lstTableClasses;
    }

    public void setLstTableClasses(List<Map<String, String>> lstTableClasses) {
        this.lstTableClasses = lstTableClasses;
    }
}
