package springboot.config.db;

public class DataSourceSwitcher {


    private static ThreadLocal<DataSourceType> dataSourceType = new InheritableThreadLocal<>();
    public  static void swtich(DataSourceType type){
        dataSourceType.set(type);
    }

    public static DataSourceType getCurrent(){
        return dataSourceType.get();
    }
}
