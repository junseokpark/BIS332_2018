package bis332.objects;

public class CalculatorObject {
    private int firstNo;
    private int secondNo;
    private int sum;
    private String text;

    public CalculatorObject(int firstNo, int secondNo, int sum, String text) {
        super();
        this.firstNo = firstNo;
        this.secondNo = secondNo;
        this.sum = sum;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
