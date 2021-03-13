package observer;

/**
 * @author safe
 * @date 2021/3/13
 */
public class DemoSubject extends Subject {

    // 执行业务
    public void doBusiness() {
        super.notifyObserver();
    }
}
