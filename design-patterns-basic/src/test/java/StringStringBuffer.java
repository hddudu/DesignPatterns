/**
 * Created by hongdu on 2019/10/30.
 */
public class StringStringBuffer {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a, b);
        System.out.println(a + "," + b);
    }

    public static void operator(StringBuffer a, StringBuffer b) {
        a.append(b); //a.append() : AB
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        b = a;//
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
}
