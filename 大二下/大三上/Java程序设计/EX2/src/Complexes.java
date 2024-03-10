class Complex {
    private int realPart;
    private int imagePart;

    public Complex() {
        this.imagePart = 0;
        this.realPart = 0;
    }

    public Complex(int r, int i) {
        this.imagePart = i;
        this.realPart = r;
    }

    public Complex ComplexADD(Complex c) {
        return new Complex(realPart + c.realPart,imagePart + c.imagePart);
    }

    public Complex ComplexSUB(Complex c) {
        return new Complex(realPart - c.realPart,imagePart - c.imagePart);
    }
    @Override
    public String toString() {
        if (imagePart > 0)
            return realPart + "+" + imagePart + "i";
        else if (imagePart == 0)
            return realPart + "";
        else return realPart + "" + imagePart + "i";
    }
}

public class Complexes {
    public static void main(String[] args) {
        Complex a=new Complex(3,5);
        Complex b=new Complex(2,3);
        System.out.println(a +" + "+ b +" = "+a.ComplexADD(b).toString());
        System.out.println(a +" - "+ b +" = "+a.ComplexSUB(b).toString());
    }
}
