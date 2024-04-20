package test;

import java.util.LinkedList;
import java.util.ListIterator;

public class TestLinkedList {
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        ListIterator<Integer> iter = list.listIterator(0);
        iter.add(0);

        return;
    }
}
