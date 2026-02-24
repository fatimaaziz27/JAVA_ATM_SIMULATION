import java.util.*;
import java.time.*;
class main{
    public static void main(String[] args){
        bank b1 = new bank();
        while (true) {
            System.out.println("1.Signup\n2.Login");
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose an option");
            Integer op = sc.nextInt();
            switch(op){
                case 1:
                    b1.sign_up();
                    break;
                case 2:
                    b1.login();
                    break;
            }
            Scanner choice = new Scanner(System.in);
            System.out.println("Do you want to continue (yes/no)?");
            String o = choice.nextLine();
            if (o.equals("No")){
                break;
            }
        }
    }
}
class bank{
    HashMap<String,account> details = new HashMap<>();
    void sign_up(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String name = sc.nextLine();

        System.out.println("Enter Password: ");
        String passkey = sc.nextLine();

        System.out.println("Enter Initial Balance: ");
        int balance = sc.nextInt();

        System.out.println("Set a 4-digit PIN:");
        int pin = sc.nextInt();
        
        if (pin >= 0000 && pin <= 9999 ){
            System.out.println("---------");
        }

        System.out.println("ACCOUNT NUMBER: ");
        int acc_num = sc.nextInt();

        details.put(name,new account(acc_num,name,pin,passkey,balance));
    }
    void login(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String name = sc.nextLine();

        System.out.println("Enter PIN: ");
        String pin = sc.nextLine();

        account acc = details.get(name);
        if (acc.details.containsKey(name) == true){
            System.out.println("Login Successful!");
        }

        while (true){
            System.out.println("Banking Menu:\n" +
                    "1. Deposit Money\n" +
                    "2. Withdraw Money\n" +
                    "3. Check Balance\n" +
                    "4. Transaction History !\n" +
                    "5. Transfer Money");

            System.out.println("Enter your choice: ");
            Integer choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter deposit amount:");
                    int am = sc.nextInt();
                    acc.deposit(am);
                    break;
                case 2:
                    System.out.println("Enter the amount you want to withdraw:");
                    int am1 = sc.nextInt();
                    acc.withdraw(am1);
                    break;
                case 3:
                    acc.display_info();
                    break;
                case 4:
                    System.out.println("Transfer History");
                    break;
                case 5:
                    sc.nextLine();
                        System.out.println("Enter account name: ");
                    String acc_name = sc.nextLine();
                    account acc_n = details.get(acc_name);
                    if (acc_name != null){
                        System.out.println("Enter the amount you want to transfer: ");
                        int transfer_amount = sc.nextInt();
                        acc.transfer_money(acc_n,transfer_amount);
                    }
                    break;
            }
            sc.nextLine();
                System.out.println("Do you want to Logout (yes/no)?");
            String exit = sc.nextLine();

            if (exit.equals("yes")){
                System.out.println("Logout successful!");
                break;
            }
        }
    }
}

class account extends bank{
    Integer account_number;
    String user_name;
    Integer PIN;
    String password;
    Integer balance;

    account(Integer account_number,String user_name,Integer PIN,String password,Integer balance){
        this.balance = balance;
        this.account_number = account_number;
        this.PIN = PIN;
        this.password = password;
        this.user_name = user_name;
    }

    void display_info(){
        System.out.println("\nName: "+this.user_name+
                "Balance: "+this.balance+
                "\nAccount Number: "+this.account_number);
    }

    void deposit(int amm){
        int am = amm;
        this.balance+=am;
    }

    void withdraw(int amm){
        int am = amm;

        if (am<=balance){
            this.balance-=am;
            System.out.println("Withdraw complete");
        }
    }

    void transfer_money(account acc_reciever,int amount){
        if (amount<=balance){
            this.withdraw(amount);
            acc_reciever.deposit(amount);
            System.out.println("Transfered: "+amount+"to "+acc_reciever.getuser_name());
        }
        else{
            System.out.println("Invalid transfer or Insufficient Funds");
        }
    }
    public String getuser_name(){
        return user_name;
    }
    public String getPassword(){
        return password;
    }
    public Integer getPIN(){
        return PIN;
    }
}