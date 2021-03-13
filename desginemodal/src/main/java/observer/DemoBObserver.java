package observer;

/**
 * @author safe
 * @date 2021/3/13
 */
public class DemoBObserver implements Observer {
    @Override
    public void update() {
        System.out.println("B收到消息");
    }
}
