package observer;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 主题
 *
 * @author safe
 * @date 2021/3/13
 */
public class Subject {
    private CopyOnWriteArrayList<Observer> list = new CopyOnWriteArrayList<>();

    // 添加观察者
    public void addObserver(Observer observer) {
        this.list.add(observer);
    }

    // 删除观察者
    public void removeObserver(Observer observer) {
        this.list.remove(observer);
    }

    // 通知所有观察者
    public void notifyObserver() {
        list.forEach(Observer::update);
    }
}
