package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0D;
        int count = 0;
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                sum += subject.score();
                count++;
            }
        }
        return sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        double sum = 0D;
        int count = 0;
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                sum += subject.score();
                count++;
            }
            labels.add(new Label(student.name(), sum / count));
            sum = 0;
            count = 0;
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        int count = 0;
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                map.merge(subject.name(), subject.score(), Integer::sum);
            }
            count++;
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(new Label(entry.getKey(), entry.getValue() / count));
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        double sum = 0D;
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                sum += subject.score();
            }
            labels.add(new Label(student.name(), sum));
            sum = 0;
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        for (Pupil student : pupils) {
            for (Subject subject : student.subjects()) {
                map.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(new Label(entry.getKey(), entry.getValue()));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}
