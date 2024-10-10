/**
 * @author lijiaming on 2024/10/10 13:10
 */
public class CalculatorException extends RuntimeException {

    public CalculatorException() {
        super();
    }

    public CalculatorException(String s) {
        super(s);
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculatorException(Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1848914673093119416L;
}
