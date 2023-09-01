package atm.service.impl;

import atm.dao.AccountDao;
import atm.model.UserAccount;
import atm.service.AccountService;
import java.util.Random;
import java.util.Scanner;


public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = new AccountDao();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);


    public enum Color {

        ANSI_RESET("\u001B[0m"),
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private final String color;

        Color(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return color;
        }
    }

    @Override
    public void singUp(String name, String lastName) {
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setName(name);
            userAccount.setLastName(lastName);
            int cardNumber = random.nextInt(10_000_000, 99_999_999);
            int pinCode = random.nextInt(1_000, 9_999);
            userAccount.setCardNumber(String.valueOf(cardNumber));
            userAccount.setPinCode(String.valueOf(pinCode));
            accountDao.getUserAccounts().add(userAccount);
            System.out.println(userAccount);

        } catch (IllegalArgumentException e) {
            System.out.println(Color.ANSI_RED + "Введите правильно");
        }

    }

    @Override
    public void singIn(String cardNumber, String pinCode) {
        while (true) {
            System.out.println(Color.ANSI_PURPLE + "Введите имя польователя:");
            String nameFirst = scanner.next();
            System.out.println(Color.ANSI_PURPLE + "Введите фамилию пользователя");
            String lastNameFirst = scanner.next();
            System.out.println(Color.ANSI_GREEN + "Добрый день Буейнеп!");
            System.out.println(Color.ANSI_GREEN + "Выберите дальнейшие действие\n" +
                    "1.Чтобы увидеть баланс:\n" +
                    "2.Чтобы попольнить:\n " +
                    "3.Чтобы отрпавить деньги другому человеку:\n" +
                    "4.Внести сумму  наличными:\n" +
                    "0.Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(Color.ANSI_YELLOW + "Ваш баланс");
                    balance(cardNumber, pinCode);
                    this.singIn(cardNumber, pinCode);
                    break;
                case 2:
                    System.out.println(Color.ANSI_YELLOW + "Введите номер карты чтобы попольнить баланс");
                    this.deposit(cardNumber, pinCode);
                    break;
                case 3:
                    System.out.println(Color.ANSI_RESET + "Введите номер карты друга");
                    String cardNumberFriends = scanner.next();
                    this.sendToFriend(cardNumberFriends);
                    break;
                case 4:
                    System.out.println(Color.ANSI_RESET + "Напишите сумму которую хотите вывести");
                    int amount = scanner.nextInt();
                    this.withdrawMoney(amount);
                case 0:
                    System.out.println(Color.ANSI_RESET + "Спасибо что воспользовались нашим банкоматам");
                    break;
            }

        }
    }

    @Override
    public void balance(String cardNumber, String pinCode) {
        for (UserAccount userAccount : accountDao.getUserAccounts()) {
            if (cardNumber.equals(userAccount.getCardNumber()) && pinCode.equals(userAccount.getPinCode())) {
                System.out.println(userAccount.getBalance());
            }
        }
    }

    @Override
    public void deposit(String cardNumber, String pinCode) {
        for (UserAccount userAccount : accountDao.getUserAccounts()) {
            if (cardNumber.equals(userAccount.getCardNumber()) && pinCode.equals(userAccount.getPinCode())) {
                System.out.println(userAccount.getBalance());
                System.out.println(Color.ANSI_GREEN + "Сумма депозита который хотите внести : ");
                int deposit = scanner.nextInt();
                System.out.println(Color.ANSI_WHITE+"Счёт на который хотите внести депозит: ");
                String cash = scanner.nextLine();
                if (deposit > 0 && deposit <= 10000) {
                    if (deposit % 100 == 0) {
                        deposit += deposit;
                        System.out.println("Депозит успешен внесен! Баланс " + deposit);
                    }
                }
            }
        }
    }

    @Override
    public void sendToFriend(String friendCardNumber) {
        System.out.println(Color.ANSI_BLUE+"Перевод другу:=>");
        System.out.println(Color.ANSI_CYAN+"Выберите сумму перевода:=>");
        int getBalance = scanner.nextInt();
        if (getBalance > 0) {
            int balance = 0;
            if (getBalance <= balance) ;
            if (getBalance % 100 == 0) {
                System.out.println("Пожалуйста, подтвердите перевод " + (balance - getBalance));
            } else {
                System.out.println("Извините баланс недоступен");
            }
        }
    }

    @Override
    public void withdrawMoney(int amount) {
        System.out.println("Введите  номер карты :=>");
        String number = scanner.next();
        System.out.println("Введите пин код");
        String pinCode = scanner.next();
        System.out.println(Color.ANSI_GREEN + "Какими купюрами хотите снять\n" +
                "1.Получить по 1000 $ купюрами\"" +
                "2.Получить по 500 $ купюрами\"" +
                "3.Получить по 200 $ купюрами\"" +
                "4.Получить по 100 $ купюрами\"" +
                "5.Получить по 50 $ купюрами\"");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                int pieces = amount / 1000;
                System.out.println("Получили по 1000 $");
                break;
            case 2:
                int pieces1 = amount / 500;
                System.out.println("Получили по 500 $ купюрами");
                break;
            case 3:
                int pieces2 = amount / 200;
                System.out.println("Получили по 200 $ купюрами");
                break;
            case 4:
                int pieces3 = amount / 100;
                System.out.println("Получили по 100 $ купюрами");
            case 5:
                int pieces4 = amount / 50;
                System.out.println("Получили 50 $ купюрами");
                break;


        }
    }
}




