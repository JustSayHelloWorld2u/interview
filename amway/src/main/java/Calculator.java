import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiaming on 2024/10/10 12:30
 */
public class Calculator {
    /**
     * 执行器
     */
    private final CoreCalculator coreCalculator = new CoreCalculator();

    /**
     * 记录历史命令
     */
    private final List<CalculatorCommand> commands = new ArrayList<CalculatorCommand>();

    /**
     * 记录当前回退或重做之后 所处位置
     */
    private int currentIndex = 0;

    public void calc(char curOperator, BigDecimal num) {
        CalculatorCommand command = new CalculatorCommand(this.coreCalculator, curOperator, num);
        command.execute();
        this.commands.add(command);
        //重新标记当前索引，记录到最新层数
        this.currentIndex = commands.size();
    }

    /**
     * 重做，触发回退后可执行重做
     *
     * @param levels 重做次数
     */
    public void redo(int levels) {
        if (levels < 1) {
            System.out.println("levels需大于0，请重新输入！");
            return;
        }
        int maxRedoCount = this.commands.size() - this.currentIndex;
        if (maxRedoCount == 0) {
            System.out.println("无redo数据!");
            return;
        }

        if (levels > maxRedoCount) {
            System.out.println("redo 开始，请求redo层数：" + levels + "，最大可redo层数：" + maxRedoCount);
        } else {
            System.out.println("redo 开始，请求redo层数：" + levels);
        }

        for (int i = 0; i < Math.min(maxRedoCount, levels); i++) {
            commands.get(this.currentIndex++).execute();
        }
        System.out.println("redo 结束");
    }

    /**
     * 撤销
     *
     * @param levels 撤销层数
     */
    public void undo(int levels) {
        if (levels < 1) {
            System.out.println("levels需大于0，请重新输入！");
            return;
        }
        if (this.currentIndex == 0) {
            System.out.println("无undo数据!");
            return;
        }

        if (levels > this.currentIndex) {
            System.out.println("undo 开始，请求undo层数：" + levels + "，最大可undo层数：" + this.currentIndex);
        } else {
            System.out.println("undo 开始，请求undo层数：" + levels);
        }
        int i = Math.min(this.currentIndex, levels);
        for (; i > 0; i--) {
            CalculatorCommand calculatorCommand = commands.get(--this.currentIndex);
            System.out.println("undo操作:" + calculatorCommand.getOperator() + ",当前值:" + coreCalculator.getTotal() + ",undo操作后值为:" + calculatorCommand.getUndoValue());
            calculatorCommand.undo();
        }
        System.out.println("undo 结束");
    }

    public String getResult() {
        return this.coreCalculator.getTotal().toString();
    }
}