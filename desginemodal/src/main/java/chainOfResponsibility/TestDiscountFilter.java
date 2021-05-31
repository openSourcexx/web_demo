package chainOfResponsibility;

/**
 * @author taqo
 * @date 2021/5/31
 */
public class TestDiscountFilter {
    public static void main(String[] args) {
        int price = 220;
        // 声明责任链上所有节点
        FullDiscountFilter fulldf = new FullDiscountFilter();
        FirstPurchaseDiscountFilter firstpd = new FirstPurchaseDiscountFilter();
        SecondPurchaseDiscountFilter secpdf = new SecondPurchaseDiscountFilter();
        HolidayDiscountFilter holidaydf = new HolidayDiscountFilter();

        // 设置链中的顺序:满减>首购减>第二件减>假日减
        fulldf.nextDiscountFilter = firstpd;
        firstpd.nextDiscountFilter = secpdf;
        secpdf.nextDiscountFilter = holidaydf;
        holidaydf.nextDiscountFilter = null;
        int total = fulldf.calculateBySourcePrice(price);
        System.out.println("所有商品优惠价后金额为:" + total);
    }
}
