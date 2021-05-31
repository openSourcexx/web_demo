package chainOfResponsibility;

/**
 * 折扣优惠接口
 *
 * @author taqo
 * @date 2021/5/31
 */
public abstract class DiscountFilter {
    // 下一个责任链成员
    protected DiscountFilter nextDiscountFilter;

    public abstract int calculateBySourcePrice(int price);
}
