package sk.posam.fsa.workshop;

import java.util.ArrayList;
import java.util.List;

public class ComparableExample {

    record AgendaItem(String name, int order) implements java.lang.Comparable<AgendaItem> {
        @Override
        public int compareTo(AgendaItem o) {
            if (order == o.order) {
                return 0;
            }
            return order < o.order
                    ? -1
                    : 1;
        }

    }

    public static void main(String[] args) {
        List<AgendaItem> agendaItems = new ArrayList<>();
        agendaItems.add(new AgendaItem("a", 1));
        agendaItems.add(new AgendaItem("b", 3));
        agendaItems.add(new AgendaItem("c", 2));

        System.out.println(agendaItems);
        agendaItems.sort(AgendaItem::compareTo);
        System.out.println(agendaItems);
    }


}
