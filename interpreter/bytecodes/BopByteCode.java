package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

/**
 * 6/24/24 @ 11:42
 *
 * @ Author : Guiran LIU
 * Description:
 */
public class BopByteCode implements ByteCode {

    private String operator;

    @Override
    public void execute(VirtualMachine vm) {
        int result;
        int value2 = vm.pop();// ??just compare from left to right?
        int value1 = vm.pop();
        result = switch (operator) {
            case "+" -> value1 + value2;
            case "-" -> value1 - value2;
            case "*" -> value1 * value2;
            case "/" -> value1 / value2;
            case "==" -> (value1 == value2) ? 1 : 0;
            case "!=" -> (value1 != value2) ? 1 : 0;
            case "<=" -> (value1 <= value2) ? 1 : 0;
            case "<" -> (value1 < value2) ? 1 : 0;
            case ">=" -> (value1 >= value2) ? 1 : 0;
            case ">" -> (value1 > value2) ? 1 : 0;
            case "&" -> (value1 != 0 && value2 != 0) ? 1 : 0;
            case "|" -> (value1 != 0 || value2 != 0) ? 1 : 0;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };

        vm.push(result);

    }

    @Override
    public void init(List<String> args) {
        this.operator = args.getLast();
    }

    @Override
    public boolean modifiesProgramCounter() {
        return false;
    }

    @Override
    public String toString() {
        return "Bop " + operator;
    }
}
