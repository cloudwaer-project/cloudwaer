/*
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
*/

/**
 * @ClassName Test
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 11:16
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
       /* //创建autoGenerator
        AutoGenerator autoGenerator = new AutoGenerator();
        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //选用数据库
        dataSourceConfig.setDbType(DbType.MYSQL);
        //设置数据库连接
        dataSourceConfig.setUrl("jdbc:mysql://8.140.135.147:3306/cloudwear?useUnicode=true&characterEncoding=UTF-8");
        //设置用户名
        dataSourceConfig.setUsername("root");
        //设置密码
        dataSourceConfig.setPassword("123");
        //设置数据库驱动
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        //装填数据源
        autoGenerator.setDataSource(dataSourceConfig);
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //获取工程绝对路径,最好去项目文件夹里面找你的子模块的路径复制过来
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //关闭文件夹
        globalConfig.setOpen(false);
        //设置作者
        globalConfig.setAuthor("jiushiboy");

        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);
        //包信息
        PackageConfig packageConfig = new PackageConfig();
        //存放路径
        packageConfig.setParent("com.cloudwaer");

        //设置controller
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity");
        autoGenerator.setPackageInfo(packageConfig);
        //配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("blog_article"); //要生成的表
        //自动添加lombokmodel注解
        strategyConfig.setEntityLombokModel(true);
        //设置驼峰名命
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
*/
    }
}
