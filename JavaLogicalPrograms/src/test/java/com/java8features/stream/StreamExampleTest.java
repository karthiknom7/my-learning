package com.java8features.stream;

import com.modle.Employee;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamExampleTest {

    private List<String> someString = Arrays.asList("abc", "xzy", "mno");

    private List<Employee> employeeList;

    @Before
    public void setUp() throws Exception {
        employeeList = new ArrayList<Employee>() {{
            add(new Employee(1, "John", BigDecimal.valueOf(20000)));
            add(new Employee(2, "Merry", BigDecimal.valueOf(23000)));
            add(new Employee(3, "Sam", BigDecimal.valueOf(26000)));
            add(new Employee(4, "Mike", BigDecimal.valueOf(30000)));
        }};
    }

    @Test
    public void checkPeekMethodOfStream() {

        List<String> strLength = someString.stream().peek(str -> str.length()).collect(Collectors.toList());
        System.out.println(strLength);
    }

    @Test
    public void checkMappingWithGroupBy() {
        Map<Character, List<Integer>> empIdsGroupedByFirstCharOfName = employeeList.stream()
                .collect(Collectors.groupingBy(emp -> emp.getName().charAt(0), Collectors.mapping(Employee::getId, Collectors.toList())));

        Map<Character, BigDecimal> collect = employeeList.stream()
                .collect(Collectors.groupingBy(emp -> emp.getName().charAt(0),
                        Collectors.mapping(Employee::getSalary,
                                Collectors.mapping(salary -> salary.add(BigDecimal.TEN),
                                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)))));

        assertEquals(3,collect.size());
        assertEquals(BigDecimal.valueOf(53020) ,collect.get('M'));
    }
}