package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает сервис банка - добавление, удаление пользователей;
 * добавить счет пользователю, перевести деньги между счетами
 * @author Aleksandr Pryhodzich
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение задания осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет его в карту
     * каждому пользователю присваивается пустая коллекция для хранения счетов
     * @param user пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход номер паспорта и удаляет его из карты
     * для поиска удаляемого пользователя мы используем метод поиска по паспорту
     * @param passport паспорт
     */
    public void deleteUser(String passport) {
        users.remove(findByPassport(passport));
    }

    /**
     * Метод добавляет существующему пользователю новый счет
     * если такой пользователь не найден или у него уже есть такой счет - ничего не произойдет
     * @param passport паспорт
     * @param account счет
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод ищет пользователя в карте по паспорту
     * @param passport паспорт
     * @return пользователя с искомым паспортом, если такого нет - null
     */
    public User findByPassport(String passport) {
        User user = null;
        for (User key : users.keySet()) {
            if (key.getPassport().equals(passport)) {
                user = key;
                break;
            }
        }
        return user;
    }

    /**
     * Метод ищет щет по паспорту пользователя и реквизитам счета
     * @param passport паспорт
     * @param requisite реквизиты счета
     * @return счет с искомыми реквизитами или null если такого нет
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account account = null;
        if (user != null) {
            for (Account bill : users.get(user)) {
                if (bill.getRequisite().equals(requisite)) {
                    account = bill;
                }
            }
        }
        return account;
    }

    /**
     * Метод списывает сумму со счета отправителя, записывает на счет получателя
     * если сумма недостаточна, или реквизиты указаны неправильно - перевод не будет совершен
     * @param sourcePassport паспорт отправителя
     * @param sourceRequisite реквизиты отправителя
     * @param destinationPassport паспорт получателя
     * @param destinationRequisite реквизиты получателя
     * @param amount сумма перевода
     * @return переведена ли сумма
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite, String destinationPassport, String destinationRequisite, double amount) {
        boolean result = false;
        Account sourceAccount = findByRequisite(sourcePassport, sourceRequisite);
        Account destinationAccount = findByRequisite(destinationPassport, destinationRequisite);
        if (sourceAccount != null && destinationAccount != null && sourceAccount.getBalance() >= amount) {
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод находит все счета пользователя
     * @param user пользователь
     * @return список счетов пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
