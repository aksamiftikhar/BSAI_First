public class Quiz1 {
    public static void main(String[] args) {
        int p = 10;
        int q = p;
        q++;
        System.out.println("A) p=" + p + ", q=" + q);

        NumberBox nb1 = new NumberBox(10);
        NumberBox nb2 = nb1;
        nb2.increment(1);
        System.out.println("B) nb1=" + nb1.read() + ", nb2=" + nb2.read());

        NumberBox nb3 = new NumberBox(10);
        NumberBox nb4 = new NumberBox(10);
        nb4.increment(5);
        System.out.println("C) nb3=" + nb3.read() + ", nb4=" + nb4.read());

        System.out.println("D) (nb3 == nb4) = " + (nb3 == nb4));

        tweak(p, nb3);
        System.out.println("F) after tweak -> p=" + p + ", nb3=" + nb3.read());

        swapBoxesWrong(nb3, nb4);
        System.out.println("G) after swapBoxesWrong -> nb3=" + nb3.read() + ", nb4=" + nb4.read());

        swapBoxValues(nb3, nb4);
        System.out.println("H) after swapBoxValues -> nb3=" + nb3.read() + ", nb4=" + nb4.read());
    }

    static void tweak(int a, NumberBox b) {
        a += 5;
        b.increment(3);
        b = new NumberBox(999);
    }

    static void swapBoxesWrong(NumberBox x, NumberBox y) {
        NumberBox tmp = x; x = y; y = tmp;
    }

    static void swapBoxValues(NumberBox x, NumberBox y) {
        int tmp = x.read();
        x.setValue(y.read());
        y.setValue(tmp);
    }
}

class NumberBox {
    private int value;

    public NumberBox(int value) { this.value = value; }
    public void setValue(int v) { this.value = v; }
    public void increment(int delta) { if (delta > 0) { value += delta; } }
    public int read() { return value; }
}
