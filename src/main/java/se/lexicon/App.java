package se.lexicon;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;

/**
 * Java.util.function
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        List<String> stringList = new ArrayList<>(
                Arrays.asList(
                "Karmand Aziz", "Alexis Capot", "Alaa Adeen", "Federico Sanders"
                )
        );

        //stringList.forEach(str -> System.out.println(str.toUpperCase()));
        //stringList.forEach(str -> System.out.println(str));

        //Predicate<String> startsWithA = s -> s.startsWith("A");

        //stringList.removeIf(startsWithA);

        stringList.forEach(System.out::println);
        Collection<Integer> integers = filterAndConvert(stringList, s -> s.endsWith("Aziz"), String::length, HashSet::new);
        System.out.println(integers);

        UnaryOperator<String> toLowercase = String::toLowerCase;
        Function<String, String> toLowercase2 = String::toLowerCase;
        UnaryOperator<String> reverse = App::reverse;
        String s = reverse.apply("Hello");

        System.out.println(s);

        LocalDateToAgeConverter converter = new LocalDateToAgeConverter();
        Function<LocalDate, Integer> yearsBetween = converter::getAge;
        int years = yearsBetween.apply(LocalDate.parse("2000-01-01"));
        System.out.println(years);
        /*
            From here to lite 53 is crap code
         */
        Collection<Object> result = filterAndConvert(
                stringList,
                str -> str.length() > 10,
                String::getBytes,
                HashSet::new
        );
        result.forEach(o -> System.out.println(Arrays.toString((byte[])o)));
    }

    public static <T,R> Collection<R> filterAndConvert(Collection<T> source, Predicate<T> filter, Function<T, R> mapper, Supplier<Collection<R>> collectionSupplier){
        Collection<R> result = collectionSupplier.get();
        for(T obj : source){
            if(filter.test(obj)){
                result.add(mapper.apply(obj));
            }
        }
        return result;
    }

    public static String reverse(String string){
        return new StringBuilder(string).reverse().toString();
    }
}
