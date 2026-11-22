package ua.opnu;

import java.util.*;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int i = 0;
        while (i + 1 < list.size()) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
            i++;
        }
    }

    public void stutter(List<String> list) {
        if (list == null) {
            return;
        }

        int i = 0;
        while (i < list.size()) {
            list.add(i + 1, list.get(i));
            i += 2;
        }
    }

    public void switchPairs(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        for (int i = 0; i + 1 < list.size(); i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    public void removeDuplicates(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int i = 1;
        while (i < list.size()) {
            if (Objects.equals(list.get(i), list.get(i - 1))) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public void markLength4(List<String> list) {
        if (list == null) {
            return;
        }

        int i = 0;
        while (i < list.size()) {
            String current = list.get(i);
            if (current != null && current.length() == 4) {
                list.add(i, "****");
                i += 2;
            } else {
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        if (queue == null) {
            return false;
        }

        int size = queue.size();
        if (size <= 1) {
            return true;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            Integer val = queue.remove();
            stack.push(val);
            queue.add(val);
        }

        boolean palindrome = true;
        for (int i = 0; i < size; i++) {
            Integer fromFront = queue.remove();
            Integer fromBack = stack.pop();
            if (!Objects.equals(fromFront, fromBack)) {
                palindrome = false;
            }
            queue.add(fromFront);
        }

        return palindrome;
    }

    public void reorder(Queue<Integer> queue) {
        if (queue == null || queue.size() < 2) {
            return;
        }

        int size = queue.size();
        int nonNegCount = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            int val = queue.remove();
            if (val < 0) {
                stack.push(val);
            } else {
                queue.add(val);
                nonNegCount++;
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        for (int i = 0; i < nonNegCount; i++) {
            queue.add(queue.remove());
        }
    }

    public void rearrange(Queue<Integer> queue) {
        if (queue == null || queue.size() < 2) {
            return;
        }

        int size = queue.size();
        int evenCount = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            int val = queue.remove();
            if (val % 2 == 0) {
                queue.add(val);
                evenCount++;
            } else {
                stack.push(val);
            }
        }

        int oddCount = size - evenCount;

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        for (int i = 0; i < evenCount; i++) {
            queue.add(queue.remove());
        }

        for (int i = 0; i < oddCount; i++) {
            stack.push(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public int maxLength(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }

        int max = 0;
        for (String s : set) {
            if (s != null && s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        if (set == null) {
            return;
        }

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s != null && s.length() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null || list1.isEmpty() || list2.isEmpty()) {
            return 0;
        }

        Set<Integer> firstSet = new HashSet<>(list1);
        Set<Integer> common = new HashSet<>();

        for (Integer num : list2) {
            if (firstSet.contains(num)) {
                common.add(num);
            }
        }

        return common.size();
    }

    public boolean isUnique(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        Set<String> seenValues = new HashSet<>();
        for (String value : map.values()) {
            if (!seenValues.add(value)) {
                return false;
            }
        }

        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();
        if (map1 == null || map2 == null || map1.isEmpty() || map2.isEmpty()) {
            return result;
        }

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (map2.containsKey(key) && Objects.equals(map2.get(key), value)) {
                result.put(key, value);
            }
        }

        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> reversed = new HashMap<>();
        if (map == null || map.isEmpty()) {
            return reversed;
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }

        return reversed;
    }

    public int rarest(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Map must not be empty");
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer value : map.values()) {
            counts.merge(value, 1, Integer::sum);
        }

        int rarestValue = 0;
        int minCount = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int candidate = entry.getKey();
            int count = entry.getValue();
            if (count < minCount || (count == minCount && candidate < rarestValue)) {
                minCount = count;
                rarestValue = candidate;
            }
        }

        return rarestValue;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> counts = new HashMap<>();
        int maxFreq = 0;

        for (Integer num : list) {
            int freq = counts.merge(num, 1, Integer::sum);
            if (freq > maxFreq) {
                maxFreq = freq;
            }
        }

        return maxFreq;
    }}