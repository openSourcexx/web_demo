package observer;

/**
 * @author safe
 * @date 2021/3/13
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个主题
        DemoSubject ds = new DemoSubject();
        // 定义观察者
        Observer osA = new DemoAObserver();
        Observer osB = new DemoBObserver();
        ds.addObserver(osA);
        ds.addObserver(osB);
        // 执行业务
        ds.doBusiness();
    }
}
