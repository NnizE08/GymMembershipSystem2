package com.app.util;

import com.app.constant.Const;
import com.app.controller.PackageController;
import com.app.controller.PaymentController;
import com.app.controller.UserController;
import com.app.dao.PackageDAO;
import com.app.dao.PaymentDAO;
import com.app.dao.UserDAO;
import com.app.dao.impl.PackageDAOIMPL;
import com.app.dao.impl.PaymentDAOImpl;
import com.app.dao.impl.UserDAOIMPL;
import com.app.model.User;
import com.app.view.PackageView;
import com.app.view.PaymentView;
import com.app.view.UserView;

import java.util.Scanner;

public class PromptManager {
    
    Scanner sc = new Scanner(System.in);
    TextHelper th = new TextHelper();
    
    User loggedUser;
        
    UserDAO userDAO = new UserDAOIMPL();
    UserView userView = new UserView();
    UserController userController = new UserController( userDAO, userView );
    
    PackageDAO packageDAO = new PackageDAOIMPL();
    PackageView packageView = new PackageView();
    PackageController packageController = new PackageController( packageDAO, packageView );
    
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    PaymentView paymentView = new PaymentView();
    PaymentController paymentController = new PaymentController(paymentDAO, paymentView);
    
    public void showLogo(){
        System.out.println(th.COLOR_BLUE + "    ________                __________");
        System.out.println(th.COLOR_BLUE + "   /  _____/ ___.__.  _____ \\______   \\_______   ____  ");
        System.out.println(th.COLOR_BLUE + "  /   \\  ___<   |  | /     \\ |    |  _/\\_  __ \\ /  _ \\");                 
        System.out.println(th.COLOR_BLUE + "  \\    \\_\\  \\\\___  ||  Y Y  \\|    |   \\ |  | \\/(  <_> )"); 
        System.out.println(th.COLOR_BLUE + "   \\______  // ____||__|_|  /|______  / |__|    \\____/"); 
        System.out.println(th.COLOR_BLUE + "          \\/ \\/           \\/        \\/                " + th.COLOR_RESET);
        System.out.println(th.COLOR_GREEN + "         -- Gym Membership Management System --" + th.COLOR_RESET);
        
    }
    
    public void showMainDashBoard(){
        
        boolean running = true; 
        
        while(running){    
            showLogo();
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tMAIN MENU" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);
            System.out.println("[1] Login");
            System.out.println("[2] Exit");   
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt(); 

            switch(choice){
                case 1:                    
                    loggedUser = userController.loginUser();
                                     
                    switch(loggedUser.getRole()){
                        case "admin":
                            // Go to Admin Dashboard
                            System.out.println(th.COLOR_BLUE + "Login Successful. Welcome " + loggedUser.getUsername() + "!" + th.COLOR_RESET);
                            showAdminDashboard();
                        break;
                        case "member":
                            // Go to Member Dashboard
                            System.out.println(th.COLOR_BLUE + "Login Successful. Welcome " + loggedUser.getUsername() + "!" + th.COLOR_RESET);
                            showMemberDashboard();
                        break;
                        default:System.out.println("Login Failed!");
                    }
                    break;
                case 2:
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.out.println(th.COLOR_BLUE + "Thank you using GymBro. Ang Gym Na May Extra Service!" +  th.COLOR_RESET);
                        System.exit(0);
                    }
                break;
                default: System.out.println(th.COLOR_RED + "Invalid Input. Try Again." + th.COLOR_RESET);
            }
        }
    }
    
    public void showAdminDashboard(){
        
        boolean running = true;
        
        while(running){
        
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tADMIN DASHBOARD" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);
            
            System.out.println("[1] Users");
            System.out.println("[2] Packages");
            System.out.println("[3] Payments");
            System.out.println("[4] Logout");
            System.out.println("[5] Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    showUserMenu();
                break;
                case 2:
                    showPackageMenu();
                break;
                case 3:
                    showPaymentMenu();
                break;
                case 4:
                    if(showConfirmMessage("logout")){
                        
                        System.out.println(th.COLOR_BLUE + "You have successfully logged out!" + th.COLOR_RESET);
                        showMainDashBoard();
                    }  
                break;
                case 5:
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.out.println(th.COLOR_BLUE + "Thank you using GymBro. May Gym Ka Na, May Papa Ka Pa!" +  th.COLOR_RESET);
                        System.exit(0);
                    }
                break;
                default:
                    System.out.println(th.COLOR_RED + "Invalid Output. Try Again." + th.COLOR_RESET);
                break;
            }
        }
    }
    
    public void showUserMenu(){
        boolean running = true;
        
        userController.displayUsers();
        
        while(running){
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tUSER MENU" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);

            System.out.println("[1] Register User");
            System.out.println("[2] View All Users");
            System.out.println("[3] View User");
            System.out.println("[4] Search User");
            System.out.println("[5] Update User");
            System.out.println("[6] Archive User");
            System.out.println("[7] Restore User");
            System.out.println("[8] Permanently Delete User");
            System.out.println("[9] Logout");
            System.out.println("[10] Back");
            System.out.println("[11] Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    userController.registerUsers();
                break;
                case 2:
                    userController.displayUsers();
                break;
                case 3:
                    userController.displayUser();
                break;
                case 4:
                    userController.searchUsers();
                break;
                case 5:
                    userController.updateUser();
                break;
                case 6:
                    userController.softDeleteUser();
                break;
                case 7:
                    userController.restoreUser();
                break;
                case 8:
                    userController.permaDeleteUser();
                break;
                case 9:
                    if(showConfirmMessage("logout")){
                        
                        System.out.println(th.COLOR_BLUE + "You have successfully logged out!" + th.COLOR_RESET);
                        showMainDashBoard();
                    }
                break;
                case 10:
                    showAdminDashboard();
                break;
                case 11:
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.exit(0);
                    }
                break;
                default:
                    System.out.println(th.COLOR_RED + "Invalid Input. Try Again" + th.COLOR_RESET);
                break;
            }
        }
    }
    
    public void showPackageMenu(){
        boolean running = true;
        
        packageController.displayPackages();
        
        while(running){
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tPACKAGE MENU" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);
            
            System.out.println("[1] Add New Package");
            System.out.println("[2] View All Packages");
            System.out.println("[3] View Package");
            System.out.println("[4] Search Package");
            System.out.println("[5] Update Package");
            System.out.println("[6] Archive Package");
            System.out.println("[7] Restore Package");
            System.out.println("[8] Permanently Delete Package");
            System.out.println("[9] Logout");
            System.out.println("[10] Back");
            System.out.println("[11] Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
        
            switch(choice){
                case 1:
                    packageController.createPackage();
                break;
                case 2:
                    packageController.displayPackages();
                break;
                case 3:
                    packageController.displayPackage();
                break;
                case 4:
                    packageController.searchPackages();
                break;
                case 5:
                    packageController.updatePackage();
                break;
                case 6:
                    packageController.archivePackage();
                break;
                case 7:
                    packageController.restorePackage();
                break;
                case 8:
                    packageController.deletePackage();
                break;
                case 9:
                    if(showConfirmMessage("logout")){
                        
                        System.out.println(th.COLOR_BLUE + "You have successfully logged out!" + th.COLOR_RESET);
                        showMainDashBoard();
                    }
                break;
                case 10:
                    showAdminDashboard();
                break;
                case 11:
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.out.println(th.COLOR_BLUE + "Thank you using GymBro. Ang Gym Na PangMasa.. Ng Pandesal Na Abs!" +  th.COLOR_RESET);
                        System.exit(0);
                    }
                break;
                default:
                    System.out.println(th.COLOR_RED + "Invalid Input. Try Again" + th.COLOR_RESET);
                break;
            }
        }    
    }
    
    public void showPaymentMenu(){
        boolean running = true; 
        
        while(running) {
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tPAYMENT MENU" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);
            
            System.out.println("[1] Process Payment");
            System.out.println("[2] View Payment Transaction");
            System.out.println("[3] Logout");
            System.out.println("[4] Back");
            System.out.println("[5] Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    paymentController.addPayment();
                break;
                case 2:
                    paymentController.displayPayment();
                break;
                case 3:
                    if(showConfirmMessage("logout")){
                        
                        System.out.println(th.COLOR_BLUE + "You have successfully logged out!" + th.COLOR_RESET);
                        showMainDashBoard();
                    }
                break;
                case 4:
                    showAdminDashboard();
                break;
                case 5:
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.out.println(th.COLOR_BLUE + "Thank you using GymBro. Ang Gym Na PangMasa.. Ng Pandesal Na Abs!" +  th.COLOR_RESET);
                        System.exit(0);
                    }
                break;
                default:
                    System.out.println(th.COLOR_RED + "Invalid Input. Try Again" + th.COLOR_RESET);
                break;
            }
        }
    }
    
    
    public void showMemberDashboard(){
        boolean running = true;
        
        while(running){
        
            System.out.println(th.DIVIDER);
            System.out.println(th.COLOR_BLUE + "\t\t\tMEMBER DASHBOARD" + th.COLOR_RESET);
            System.out.println(th.DIVIDER);

            System.out.println("[1] View Profile");
            System.out.println("[2] View Packages");
            System.out.println("[3] Payment History");
            System.out.println("[4] Change Password");
            System.out.println("[5] Logout");
            System.out.println("[6] Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice){
                case Const.VIEW_PROFILE:
                    userController.displayLoggedUser(loggedUser.getId());
                break;
                case Const.VIEW_PACKAGES :
                    packageController.displayPackage();
                break;
                case Const.PAYMENT_HISTORY :
                    paymentController.displayUserPayment(loggedUser.getId());
                break;
                case Const.CHANGE_PASSWORD :
                    userController.updateUserPassword();
                break;
                case Const.LOGOUT :
                    if(showConfirmMessage("logout")){
                        
                        System.out.println(th.COLOR_BLUE + "You have successfully logged out!" + th.COLOR_RESET);
                        showMainDashBoard();
                    }  
                break;
                case Const.EXIT :
                    boolean confirm = showConfirmMessage("exit");
                    if(confirm) {
                        System.out.println(th.COLOR_BLUE + "Thank you using GymBro. May Gym Ka Na, May Papa Ka Pa!" +  th.COLOR_RESET);
                        System.exit(0);
                    }
                break;
                default:
                    System.out.println(th.COLOR_RED + "Invalid Output. Try Again." + th.COLOR_RESET);
                break;
            }
        }
    }
    
  
    public boolean showConfirmMessage(String msg){
        System.out.println(th.COLOR_RED + "Are you sure you want to " + msg + "? Y/N" + th.COLOR_RESET);
        if(msg.equals("exit") || msg.equals("logout")) sc.nextLine(); //buffer upon exit
        String response =  sc.nextLine();
        if(response.equalsIgnoreCase("y")){
            return true; 
        }
        else if(response.equalsIgnoreCase("n")){
            return false;
        }
        else{
            System.out.println("Invalid Input"); 
            return false;
        }
    }
    
    public boolean showTryAgainMessage(){
        System.out.println(th.COLOR_BLUE + "Try again? YES/NO" + th.COLOR_RESET);
        String buffer = sc.nextLine();
        String response = sc.nextLine(); 
        if(response.equalsIgnoreCase("YES")){
            return true;
        }
        else if(response.equalsIgnoreCase("NO")){
            return false;
        }
        else{
            System.out.println("Invalid Input"); 
            System.exit(0);
            return false;
        }
    }
}
