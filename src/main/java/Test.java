import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * @author by woyuan  2023/11/24
 */
public class Test {
    public static void main(String[] args) throws Exception {
        PooledObjectFactory<MyProcess> factory = new MyProcessFactory();
        GenericObjectPool<MyProcess> pool = new GenericObjectPool(factory);

        // 获取进程实例
        MyProcess process = pool.borrowObject();

        // 归还实例
        pool.returnObject(process);
    }

    private GenericObjectPoolConfig<MyProcess> genericObjectPoolConfig() {
        final GenericObjectPoolConfig<MyProcess> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(20); // 池的最大容量
        config.setMaxIdle(4); // 最大空闲连接数
        config.setMinIdle(0); // 最小空闲连接数
        config.setMaxWait(Duration.ofSeconds(5)); // 获取对象时最大等待时间
        config.setTimeBetweenEvictionRuns(Duration.ofMinutes(1)); // 空闲对象检查间隔
        config.setMinEvictableIdleTime(Duration.ofMinutes(10)); // 空闲对象被移除的最小空闲时间
        config.setTestOnBorrow(true);
        config.setLifo(false);
        return config;
    }

}
