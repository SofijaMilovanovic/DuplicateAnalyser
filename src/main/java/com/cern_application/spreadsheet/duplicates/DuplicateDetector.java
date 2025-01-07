package com.cern_application.spreadsheet.duplicates;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateDetector {

    public static <T> List<T> getDuplicates (List<T> inputList){

        Map<T, Long> occurrenceMap = inputList.stream()
                .collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()));

        return occurrenceMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        List<Object> input = Arrays.asList("b", "a", "c", "c", "e", "a", "c", "d", "c", "d");
        List<Object> result = getDuplicates(input);

        String jsonOutput = result.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(jsonOutput);
    }
}
