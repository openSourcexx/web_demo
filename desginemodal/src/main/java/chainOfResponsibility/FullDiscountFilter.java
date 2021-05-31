package chainOfResponsibility;

/**
 * 满200减20优惠券
 *
 * @author taqo
 * @date 2021/5/31
 */
public class FullDiscountFilter extends DiscountFilter {

    @Override
    public int calculateBySourcePrice(int price) {
        if (price >= 200) {
            System.out.println("使用优惠券满200减20");
            price -= 20;
        }
        if (this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(price);
        }
        return price;
    }
}
