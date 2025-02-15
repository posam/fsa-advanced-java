package sk.posam.fsa;

import sk.posam.fsa.order.Order;
import sk.posam.fsa.order.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);

        // TODO: strem ktory zoberie iba parne cisla, vynasobi ich 3 a spocita
        Optional<Integer> sum = integers.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 3)
//                 .collect(Collectors.toList())
//                 .toList();
                .reduce(Integer::sum);

        Integer sumInt = sum
                .orElseThrow(() -> new IllegalStateException("neexistuje"));

//        if (sum.isPresent()) {
            System.out.println(sum.get());
//        }


        List<Order> orders = List.of(new Order(OrderStatus.PROCESSED, BigDecimal.ONE), new Order(OrderStatus.UNPROCESSED, BigDecimal.TWO), new Order(OrderStatus.PROCESSED, BigDecimal.TEN));
        // TODO: iba nevybavene objednavky
        // TODO: pocet nevybavenych objednavok
        // TODO: suma cien vsetkych objednavok
    }

}
