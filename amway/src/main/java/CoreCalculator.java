import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author lijiaming on 2024/10/10 12:51
 */
public class CoreCalculator {

    private BigDecimal total = new BigDecimal("0");

    /**
     * 默认精度2位小数
     */
    private final static int SCALE = 2;

    public void calc(char operator, BigDecimal num) {
        BigDecimal before = total;
        switch (operator) {
            case '+':
                total = total.add(num).setScale(SCALE, RoundingMode.HALF_UP);
                break;
            case '-':
                total = total.subtract(num).setScale(SCALE, RoundingMode.HALF_UP);
                break;
            case '*':
                total = total.multiply(num).setScale(SCALE, RoundingMode.HALF_UP);
                break;
            case '/':
                total = total.divide(num, SCALE, RoundingMode.HALF_UP);
                break;
            default:
                throw new CalculatorException("计算失败,非法操作符:" + operator);
        }
        System.out.println(before + "" + operator + num + "=" + total);
    }

    public void undo(BigDecimal num) {
        this.total = num;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
