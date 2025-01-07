package com.cern_application.duplicates;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A utility class for detecting duplicate elements in a list.
 * It returns a list containing only the elements that are duplicated,
 * in the order of their first appearance in the input list.
 *
 * <p>The duplicates are formatted as a JSON-like string for output purposes.</p>
 *
 * @param <T> the type of elements in the list
 */
public class DuplicateDetector {

    /**
     * Finds all duplicate elements in the given list.
     *
     * <p>The method maintains the order of the first occurrence of each duplicate
     * and returns a list containing only those elements that appear more than once
     * in the input list.</p>
     *
     * @param inputList the input list of elements
     * @param <T> the type of elements in the input list
     * @return a list of duplicate elements in the order of their first occurrence
     */
    public static <T> List<T> getDuplicates (List<T> inputList){

        Map<T, Long> occurrenceMap = inputList.stream()
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()));

        return occurrenceMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Main method to demonstrate the functionality of DuplicateAnalyser.
     *
     * <p>It creates a sample list, finds duplicate elements using {@link #getDuplicates(List)},
     * and prints the results in a JSON-like format.</p>
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Object> input = Arrays.asList("b", "a", "c", "c", "e", "a", "c", "d", "c", "d");
        List<Object> result = getDuplicates(input);

        String jsonOutput = result.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(jsonOutput);
    }
}
