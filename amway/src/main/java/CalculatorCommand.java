import java.math.BigDecimal;

/**
 * @author lijiaming on 2024/10/10 12:49
 */
public class CalculatorCommand implements Command {
    /**
     * 操作符
     */
    private final char operator;
    /**
     * 执行运算前的值
     */
    private final BigDecimal undoValue;
    /**
     * 当前值
     */
    private final BigDecimal num;
    /**
     * 计算器
     */
    private final CoreCalculator coreCalculator;

    public CalculatorCommand(CoreCalculator coreCalculator, char operator, BigDecimal num) {
        this.undoValue = coreCalculator.getTotal();
        this.coreCalculator = coreCalculator;
        this.operator = operator;
        this.num = num;
    }

    public char getOperator() {
        return this.operator;
    }

    public BigDecimal getUndoValue() {
        return this.undoValue;
    }

    public void execute() {
        coreCalculator.calc(this.operator, this.num);
    }

    public void undo() {
        coreCalculator.undo(this.undoValue);
    }
}

