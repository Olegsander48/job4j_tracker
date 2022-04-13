package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("prigodic1996@gmail.com", "Пригодич А. Н.");
        map.put("andreychenkoVasiliy@gmail.com", "Андрейченко В. П.");
        for (String key: map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
    }
}
