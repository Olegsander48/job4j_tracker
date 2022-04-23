package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод находит пользователя по паспорту
     * @param passport пасспорт пользователя для проверки нахождения в коллекции
     * @return возвращает найденного пользователя либо null
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод ищет счет пользователя по реквезитам
     * @param passport пасспорт пользователя для проверки нахождения в коллекции
     * @param requisite реквизиты пользователя необходимые для поиска счета пользователя
     * @return возвращает счет пользователя либо null
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        List<Account> userAccounts = users.get(user);
        if (user != null) {
            for (Account userAccount : userAccounts) {
                if (userAccount.getRequisite().equals(requisite)) {
                    return userAccount;
                }
            }
        }
        return null;
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
        Account srcUser = findByRequisite(srcPassport, srcRequisite);
        Account destUser = findByRequisite(destPassport, destRequisite);
        if (srcUser != null && destUser != null && srcUser.getBalance() >= amount) {
            destUser.setBalance(destUser.getBalance() + amount);
            srcUser.setBalance(srcUser.getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}