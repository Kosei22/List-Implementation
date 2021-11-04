package collections.list;

import java.util.LinkedList;

public class Demo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add("Hello");
        list.add(2);
        list.add("Chocolate");
        list.add("10");

        System.out.println(list);
    }
}
