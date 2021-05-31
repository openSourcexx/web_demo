package chainOfResponsibility;

/**
 * 第二件打9折
 *
 * @author taqo
 * @date 2021/5/31
 */
public class SecondPurchaseDiscountFilter extends DiscountFilter {
    @Override
    public int calculateBySourcePrice(int price) {
        System.out.println("第二件打9折");
        Double balance = price * 0.9;
        if (this.nextDiscountFilter != null) {
            return super.nextDiscountFilter.calculateBySourcePrice(balance.intValue());
        }
        return balance.intValue();
    }
}
