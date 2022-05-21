package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу с банковскими счетами
 * @author PRIGODICH ALEKSANDR
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных о пользователе и его аккаунтах осуществляется в колллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет в коллекцию пользователя
     * @param user пользователь, которого нужно добавить в коллекцию
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляет пользователю аккаунт
     * @param passport пасспорт пользователя для проверки нахождения в коллекции
     * @param account аккаунт который необходимо добавить пользователю
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent() && !users.get(user.get()).contains(account)) {
            users.get(user.get()).add(account);
        }
    }

    /**
     * Метод находит пользователя по паспорту
     * @param passport пасспорт пользователя для проверки нахождения в коллекции
     * @return возвращает найденного пользователя либо null
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод ищет счет пользователя по реквезитам
     * @param passport пасспорт пользователя для проверки нахождения в коллекции
     * @param requisite реквизиты пользователя необходимые для поиска счета пользователя
     * @return возвращает счет пользователя либо null
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        return user.flatMap(value -> users.get(value).stream()
                .filter(u -> u.getRequisite().equals(requisite))
                .findFirst());
    }

    /**
     * Метод предназначен для перечисления денег с одного счёта на другой счёт
     * @param srcPassport пасспорт пользователя который отправляет сумму
     * @param srcRequisite реквизиты пользователя отправляющего сумму
     * @param destPassport пасспорт пользователя который принимает сумму
     * @param destRequisite реквизиты пользователя принимающего сумму
     * @param amount сумма для перечисления
     * @return возвращает успех операции перевода средств (в случае неуспеха true, в случае неудачи false)
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Optional<Account> srcUser = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destUser = findByRequisite(destPassport, destRequisite);
        if (srcUser.isPresent() && destUser.isPresent() && srcUser.get().getBalance() >= amount) {
            destUser.get().setBalance(destUser.get().getBalance() + amount);
            srcUser.get().setBalance(srcUser.get().getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}