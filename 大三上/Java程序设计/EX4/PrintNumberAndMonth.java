class Print {
    private static final String[] months = {"OneJanuary", "TwoFebruary", "ThreeMarch", "FourApril", "FiveMay", "SixJune", "SevenJuly",
            "EightAugust", "NineSeptember", "TenOctober", "ElevenNovember", "TwelveDecember"};

    public synchronized void printMonth() {
        for (int i = 0; i < 12; i++) {
            System.out.print(months[i]);
            notify();
            if (i < 11) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized void printNumber() {
        for (int i = 1; i <= 12; i++) {
            System.out.print(i);
            notify();
            if (i < 12) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

class PrintMonth implements Runnable {
    Print print;

    PrintMonth(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        print.printMonth();
    }
}

class PrintNumber implements Runnable {
    Print print;

    PrintNumber(Print print) {
        this.print = print;
    }

    @Override
    public void run() {
        print.printNumber();
    }
}

public class PrintNumberAndMonth {

    public static void main(String[] args) {
        Print print = new Print();
        Thread number = new Thread(new PrintNumber(print));
        Thread month = new Thread(new PrintMonth(print));
        month.start();
        number.start();
    }
}