import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author by woyuan  2023/11/24
 */
public class MyProcessFactory implements PooledObjectFactory<MyProcess> {
    @Override
    public void activateObject(PooledObject<MyProcess> pooledObject) {

    }

    @Override
    public void destroyObject(PooledObject<MyProcess> p) {
        final MyProcess process = p.getObject();
        if (null != process) {
            // 销毁进程
            process.stop();
        }
    }

    @Override
    public void destroyObject(PooledObject<MyProcess> p, DestroyMode destroyMode) {

    }

    @Override
    public PooledObject<MyProcess> makeObject() {
        // 这里就是去创建一个进程
        MyProcess process = new MyProcess();
        process.start();
        return new DefaultPooledObject<>(process);
    }

    @Override
    public void passivateObject(PooledObject<MyProcess> pooledObject) {

    }

    @Override
    public boolean validateObject(PooledObject<MyProcess> pooledObject) {
        return false;
    }

    // 剩下几个方法也可以按需实现
}
