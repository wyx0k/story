package indi.wyx0k.story.service.image.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {
    private static String[] GEN_TABLES = {"story_image","story_image_info"};
    private static String MODULE_NAME = "/story-service-image";

    private static boolean disableController = true;
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + MODULE_NAME +"/src/main/java")
                .setAuthor("wyx0k")
                .setOpen(false)
                .setActiveRecord(true)
                .setIdType(IdType.AUTO)
        //生成基本的resultMap
                .setBaseResultMap(true)
            //生成基本的SQL片段
                .setBaseColumnList(true)
        //二级缓存
                .setEnableCache(false)
                .setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/story?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC")
        // dsc.setSchemaName("public");
            .setDriverName("com.mysql.jdbc.Driver")
            .setUsername("root")
            .setPassword("root")
            .setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("indi.wyx0k.story.service.image");

        mpg.setPackageInfo(pc);

//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//
//        // 如果模板引擎是 velocity
//         String templatePath = "templates/codegen/mapper.xml.vm";
//       // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + MODULE_NAME + "/src/main/resources/mapper/"
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

//         配置自定义输出模板
//        指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        if(disableController){
            templateConfig.setController("templates/codegen/controller.java.vm");
        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix("story_")
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(GEN_TABLES)
                .setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}