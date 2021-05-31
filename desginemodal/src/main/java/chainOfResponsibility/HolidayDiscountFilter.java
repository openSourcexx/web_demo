package chainOfResponsibility;

/**
 * 节假日一律减5
 *
 * @author taqo
 * @date 2021/5/31
 */
public class HolidayDiscountFilter extends DiscountFilter {
    @Override
    public int calculateBySourcePrice(int price) {
        if (price >= 20) {
            System.out.println("节假日一律减5元");
            price -= 5;
        }
        if (this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(price);
        }
        return price;
    }
}
