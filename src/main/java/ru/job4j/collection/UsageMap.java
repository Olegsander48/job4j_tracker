package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("prig@gmail.com", "Пригодич Александр Николаевич");
        hashMap.put("aliev@gmail.com", "Алиев Владимир Павлович");
        hashMap.put("petrov@mail.ru", "Петров Андрей Васильевич");
        for (Map.Entry<String, String> key : hashMap.entrySet()) {
            System.out.println(key.getKey() + "-" + key.getValue());
        }
    }
}
