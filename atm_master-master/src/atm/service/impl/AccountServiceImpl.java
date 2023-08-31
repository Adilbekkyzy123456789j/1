package atm.service.impl;

import atm.dao.AccountDao;
import atm.model.UserAccount;
import atm.service.AccountService;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.util.Arrays.fill;

public class AccountServiceImpl implements AccountService {
    private  final AccountDao accountDao =new AccountDao();
    private   final Random random=  new Random();
    private  final Scanner scanner=new Scanner(System.in);


    @Override
    public void singUp(String name, String lastName) {
        Random random = new Random();
        UserAccount userAccount = new UserAccount();
        userAccount.setName(name);
        userAccount.setLastName(lastName);
        int cardNumber = random.nextInt(10_000_000, 99_999_999);
        int pinCode = random.nextInt(1_000, 9_999);
        userAccount.setCardNumber(String.valueOf(cardNumber));
        userAccount.setPinCode(String.valueOf(pinCode));
        System.out.println(userAccount);
        accountDao.getUserAccounts().add(userAccount);
    }
   @Override
   public void singIn(String cardNumber, String pinCode) {
       for (UserAccount account : accountDao.getUserAccounts()) {
           if (cardNumber.equals(account.getCardNumber()) && pinCode.equals(account.getPinCode())) {
               System.out.println("1.Чтобы увидеть баланс:\n" +
                       "2.Чтобы попольнить баланс:\n" +
                       "3.Чтобы отправить деньги другу:\n" +
                       "4.Снять наличные:\n");
               System.out.println("Выберите дальнейшие действие");
              int menu= scanner.nextInt();
               while (true)
                   if (menu==1) {
                       System.out.println("Ваш баланс: ");
                       balance(cardNumber, pinCode);

                   }else if (menu==2) {
                       deposit(cardNumber, pinCode);
                   }else if (menu==3) {
                       System.out.print("Введите номер карты друга: ");
                       String friendCode = scanner.nextLine();
                       sendToFriend(friendCode);
                   }else if (menu==0) {
                   System.out.println("Спасибо, что воспользовались нашим банкоматам");
               }
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
    }
    @Override
    public void sendToFriend(String friendCardNumber) {
        System.out.println("Перевод другу:=>");
        System.out.println("Выберите сумму перевода:=>");
        int getBalance= scanner.nextInt();
        if(getBalance>0){
            int balance = 0;
            if (getBalance<=balance );
            if (getBalance%100==0){
                System.out.println("Пожалуйста, подтвердите перевод "+(balance-getBalance));
            }else {
                System.out.println("Извините баланс недоступен");

            }
        }
    }

    @Override
    public void withdrawMoney(int amount) {

        System.out.println("Введите сумму перевода:=>");
        int summa;
            System.out.println("Выберите сумму ");
        System.out.println("1.5000  2.4000 3.3000  4.2000 5.1000" );
            summa= scanner.nextInt();
            switch (summa){
                case 1:
                    System.out.println(5000);
                    break;
                case 2:
                    System.out.println(4000);
                    break;
                case 3:
                    System.out.println(3000);
                    break;
                case 4:
                    System.out.println(2000);
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println(1000);
                    break;
                case 0:
                    System.out.println("Другая сумма");
                    String summa1= scanner.next();
                    System.out.println("Введите другую сумму");
                default:
                    System.out.println("Купюр больше нет");
        }

            System.out.println("Перевод успешно отправлен,остаток"+amount);
    }

}
