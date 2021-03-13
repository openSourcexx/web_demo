package observer;

/**
 * @author safe
 * @date 2021/3/13
 */
public class DemoAObserver implements Observer {
    @Override
    public void update() {
        System.out.println("A收到消息");
    }
}
