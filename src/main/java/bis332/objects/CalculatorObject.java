package bis332.objects;

public class CalculatorObject {
    private int firstNo;
    private int secondNo;
    private int sum;

    public CalculatorObject(int firstNo, int secondNo, int sum) {
        super();
        this.firstNo = firstNo;
        this.secondNo = secondNo;
        this.sum = sum;
    }

    public int getFirstNo() {
        return firstNo;
    }

    public void setFirstNo(int firstNo) {
        this.firstNo = firstNo;
    }

    public int getSecondNo() {
        return secondNo;
    }

    public void setSecondNo(int secondNo) {
        this.secondNo = secondNo;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
