import users.Counselor;
import users.Parent;
import users.Student;
import users.User;

import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainPage {

    public static void main(String[] args) {

        Scanner scanM = new Scanner(System.in);
        User user;
        String newLine = System.getProperty("line.separator");
        System.out.println("------------Welcome To Zero Dawn------------");
        System.out.println("1.Sign Up");
        System.out.println("2.Login");
        System.out.println("3.Exit");

        int mSelect = scanM.nextInt();
        while (mSelect != 1 && mSelect != 2 && mSelect != 3)
        {
            System.out.print("Wrong Input, try again: ");
            mSelect = scanM.nextInt();
        }
        while(true) {
            if (mSelect == 1) {
                user = sign_up();
                if(user.getClass().equals(Student.class))
                    StudentMenu((Student) user);
                if(user.getClass().equals(Counselor.class))
                    CounselorMenu((Counselor) user);
                if(user.getClass().equals(Parent.class))
                    ParentMenu((Parent) user);
            }
            if (mSelect == 2) {
                user = login();
                if(user.getClass().equals(Student.class))
                    StudentMenu((Student) user);
                if(user.getClass().equals(Counselor.class))
                    CounselorMenu((Counselor) user);
                if(user.getClass().equals(Parent.class))
                    ParentMenu((Parent) user);
            }
            if (mSelect == 3) {
                System.exit(0);
            }
        }
    }



    public static User sign_up(){

        String ID;
        String pass;
        String fName;
        String lName;
        Date BirthDate;
        String email;
        char type;

        Scanner scanObj = new Scanner(System.in);

        //Id input
        while (true) {
            System.out.print("Enter user id: ");
            ID = scanObj.nextLine();
            String regex = "[0-9]+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(ID);
            if (ID.length() == 9 && m.matches()) {
                break;
            }
            else {
                System.out.println("you didn't insert an ID, please try again..");
            }
        }

        //Password input
        while (true) {
            System.out.print("Enter user password: ");
            pass = scanObj.nextLine();
            if (!pass.isEmpty()) {
                break;
            }
            else {
                System.out.println("you didn't insert a password, please try again..");
            }
        }

        //First name input
        while (true) {
            System.out.print("Enter user first name: ");
            fName = scanObj.nextLine();
            if (!fName.isEmpty()) {
                break;
            }
            else {
                System.out.println("you didn't insert your first name, please try again..");
            }
        }

        //Last name input
        while (true) {
            System.out.print("Enter user last name: ");
            lName = scanObj.nextLine();
            if (!lName.isEmpty()) {
                break;
            }
            else {
                System.out.println("you didn't insert your last name, please try again..");
            }
        }

        //Date input
        while (true) {
            System.out.print("Enter user birth date (example: 1999-02-23 ): ");
            String bDate = scanObj.nextLine();
            try {
                BirthDate = Date.valueOf(bDate);
            }
            catch (Exception exception){
                BirthDate = null;
            }
            if(BirthDate != null)
                break;
            else {
                System.out.println("Invalid date format");
            }
        }

        //Email input
        while (true) {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex);
            System.out.print("Enter user email: ");
            email = scanObj.nextLine();
            Matcher match = pattern.matcher(email);
            if (!email.isEmpty() && match.matches()) {
                break;
            }
            else {
                System.out.println("you didn't insert an email or the email is Invalid, please try again..");
            }
        }

        //User type input
        while (true) {
            String newLine = System.getProperty("line.separator");
            System.out.print("Enter 1 for student" + newLine + "Enter 2 for parent" + newLine + "Enter 3 for counselor" + newLine + "Enter Type: ");
            type = scanObj.next().charAt(0);
            while (type != '1' && type != '2' && type != '3') {
                System.out.println("Wrong input, try again..");
                System.out.print("Enter 1 for student" + newLine + "Enter 2 for parent" + newLine + "Enter 3 for counselor" + newLine + "Enter Type: ");
                type = scanObj.next().charAt(0);
            }
            if (type == '3') {
                Scanner pScan = new Scanner(System.in);
                System.out.println("Please enter password for counselor creation: ");
                String tPass = pScan.nextLine();
                while (!tPass.equals("1111")) {
                    System.out.println("Wrong password, try again..");
                    System.out.println("To cancel the action and exit the system enter 1 in the password");
                    System.out.println("Please enter password for counselor creation: ");
                    tPass = pScan.nextLine();
                    if(tPass.equals("1")) {
                        System.exit(0);
                    }
                }
            }
            if (type != ' ') {
                break;
            }
            else {
                System.out.println("you didn't insert your type, please try again..");
            }
        }
        switch (type){
            case '1':
                Student student = new Student();
                student.SignUp(ID,pass, fName, lName, BirthDate, email);
                return student;
            case '2':
                Parent parent = new Parent();
                parent.SignUp(ID, pass, fName, lName, BirthDate, email);
                return parent;
            case '3':
                Counselor counselor = new Counselor();
                counselor.SignUp(ID, pass, fName, lName, BirthDate, email);
                return counselor;
        }


        return null;

    }

    public static User login() {

        Scanner scan = new Scanner(System.in);
        String ID;
        String pass;

        //Id input
        while (true) {
            System.out.print("Enter user id: ");
            ID = scan.nextLine();
            String regex = "[0-9]+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(ID);
            if (ID.length() == 9 && m.matches()) {
                break;
            }
            else {
                System.out.println("you didn't insert an ID, please try again..");
            }
        }

        //Password input
        while (true) {
            System.out.print("Enter user password: ");
            pass = scan.nextLine();
            if (!pass.isEmpty()) {
                break;
            }
            else {
                System.out.println("you didn't insert a password, please try again..");
            }
        }

        return User.Login(ID,pass);

    }

    public static void StudentMenu(Student student) {
        Scanner scanM = new Scanner(System.in);
        int Opt;
        while(true) {
            System.out.println("1.Edit profile");
            System.out.println("2.Exit");
            Opt = scanM.nextInt();


            switch (Opt) {
                case 1:
                    Edit(student);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void CounselorMenu(Counselor counselor) {
        Scanner scanM = new Scanner(System.in);
        int Opt;
        System.out.println("1.Edit profile");
        System.out.println("2.Exit");
        Opt = scanM.nextInt();

        while(true) {
            switch (Opt) {
                case 1:
                    Edit(counselor);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    public static void ParentMenu(Parent parent) {
        Scanner scanM = new Scanner(System.in);
        int Opt;
        System.out.println("1.Edit profile");
        System.out.println("2.Exit");
        Opt = scanM.nextInt();

        while(true) {
            switch (Opt) {
                case 1:
                    Edit(parent);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void Edit(User user){
        Scanner scanM = new Scanner(System.in);
        int Opt;
        while (true) {
            System.out.println("1.Edit password");
            System.out.println("2.Edit first name");
            System.out.println("3.Edit last name");
            System.out.println("4.Edit email");
            System.out.println("5.Back to main menu");

            Opt = scanM.nextInt();

            switch (Opt) {
                case 1:
                    user.EditPassword();
                    break;
                case 2:
                    user.EditFristName();
                    break;
                case 3:
                    user.EditLastName();
                    break;
                case 4:
                    user.EditEmail();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }


}
