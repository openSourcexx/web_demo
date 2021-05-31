package chainOfResponsibility;

/**
 * 首次购买满100减20
 *
 * @author taqo
 * @date 2021/5/31
 */
public class FirstPurchaseDiscountFilter extends DiscountFilter {
    @Override
    public int calculateBySourcePrice(int price) {
        if (price >= 100) {
            System.out.println("首次购买满100减20");
            price -= 20;
        }
        if (this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(price);
        }
        return price;
    }
}
