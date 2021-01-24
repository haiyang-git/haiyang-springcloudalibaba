package com.haiyang.sca.provider.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
    //数据库类型
    private static final DbType dbType = DbType.MYSQL;
    //数据库连结信息
    private static final String dbUrl = "jdbc:mysql://113.31.126.180:3306/sca?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "root";
    private static final String password = "root";

    //项目名
    private static final String projectName = "spring-boot";
    //指定包名
    private static final String packageName = "com.hywang.sca.provider";
    //controller基础类
    private static final String superControllerClass = packageName + ".common.BaseController";
    //entity基础类
    private static final String superEntityClass = packageName + ".common.BaseEntity";
    //模块名 如果有模块名，则需在模块名前加. 例：.log
    private static final String moduleName = "";
    //作者名
    private static final String author = "hywang";
    //指定生成的表名
    private static final String[] tableNames = new String[]{"t_user"};


    public static void main(String[] args) {

        //serviceNameStartWithI：user -> UserService, 设置成true: user -> IUserService
        generateByTables(false, packageName, tableNames);
    }

    /**
     * 根据表自动生成
     *
     * @param serviceNameStartWithI 默认为false
     * @param packageName           包名
     * @param tableNames            表名
     * @author Terry
     */
    private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        //配置数据源
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        //全局变量配置
        GlobalConfig globalConfig = getGlobalConfig(serviceNameStartWithI);
        //包名配置
        PackageConfig packageConfig = getPackageConfig(packageName);
        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig);
    }

    /**
     * 集成
     *
     * @param dataSourceConfig 配置数据源
     * @param strategyConfig   策略配置
     * @param config           全局变量配置
     * @param packageConfig    包名配置
     * @author Terry
     */
    private static void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig config, PackageConfig packageConfig) {
        new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 设置包名
     *
     * @param packageName 父路径包名
     * @param packageName 模块名
     * @return PackageConfig 包名配置
     * @author Terry
     */
    private static PackageConfig getPackageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setXml("mapper.xml")
                .setMapper("mapper")
                .setController("controller")
                .setEntity("entity");
    }

    /**
     * 全局配置
     *
     * @param serviceNameStartWithI false
     * @return GlobalConfig
     * @author Terry
     */
    private static GlobalConfig getGlobalConfig(boolean serviceNameStartWithI) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setActiveRecord(false)
                //作者
                .setAuthor(author)
                //设置输出路径
                .setOutputDir(getOutputDir())
                .setFileOverride(true);
        if (!serviceNameStartWithI) {
            //设置service名
            globalConfig.setServiceName("%sService");
        }
        return globalConfig;
    }

    /**
     * 返回项目路径
     *
     *
     * @return 项目路径
     * @author Terry
     */
    private static String getOutputDir() {
        //String path = CodeGenerator.class.getClassLoader().getResource("/").getPath();
        String path = "D:/generator/";
        return path;
    }

    /**
     * 策略配置
     *
     * @param tableNames 表名
     * @return StrategyConfig
     * @author Terry
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                //表的前缀
                .setTablePrefix("t_")
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //需要生成的的表名，多个表名传数组
                .setInclude(tableNames)
                //公共父类
                //.setSuperControllerClass(superControllerClass)
                //.setSuperEntityClass(superEntityClass)
                // 写于父类中的公共字段
                //.setSuperEntityColumns("id")
                //使用lombok
                .setEntityLombokModel(true)
                //rest风格
                .setRestControllerStyle(true);
    }

    /**
     * 配置数据源
     *
     * @return 数据源配置 DataSourceConfig
     * @author Terry
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(userName)
                .setPassword(password)
                .setDriverName(driver);
    }

    /**
     * 根据表自动生成
     *
     * @param packageName 包名
     * @param tableNames  表名
     * @author Terry
     */
    @SuppressWarnings("unused")
    private static void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}