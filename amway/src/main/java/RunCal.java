import java.math.BigDecimal;

/**
 * @author lijiaming on 2024/10/10 12:52
 */
public class RunCal {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //撤销
        calculator.undo(4);
        //重做
        calculator.redo(4);

        calculator.calc('+', new BigDecimal("100.1"));
        calculator.calc('*', new BigDecimal("10.2"));
        calculator.calc('/', new BigDecimal("2.2212"));
        calculator.calc('-', new BigDecimal("50.22"));

        //撤销
        calculator.undo(4);
        //重做
        calculator.redo(4);

        calculator.calc('+', new BigDecimal("2"));

        calculator.redo(1);

        calculator.undo(2);
        //获取运行结果
        System.out.println("最终结果：" + calculator.getResult());
    }
}
