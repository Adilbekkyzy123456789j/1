import atm.service.AccountService;
import atm.service.impl.AccountServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        accountService.singUp("Zeynep", "Adilbek kyzy");
        accountService.singUp("Iskander", "Askarov");

        while (true){
            accountService.singIn("Zeynep","Adilbek kyzy");
        }
    }
}
