package MenuAll;

import UserAll.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class LogIn extends Menu {

    private static boolean pass = false;

    static Deque<User> loadUser = loadUser();

    public LogIn(String name) {
        super(name);
    }


    public static Deque<User> loadUser()
    {
        Deque<User> userList = new ArrayDeque<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/UserAll/user.txt"))))
        {
            String[] fields = null;
            String line = null;

            for (int i = 0 ; ((line = reader.readLine()) != null); i++) {
                fields = line.split(",");

                User user = new User();

                user.setName(fields[0]);
                user.setPassword(fields[1]);

                userList.addLast(user);

            }

        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }


        return userList;

    }

    public User start(User user)
    {

        if (pass == false) {

            Shop shop = new Shop("Shop");
            Library library = new Library("Library");

            addMenuList(shop);
            addMenuList(library);

            showName();


            String userNameInput = "";
            String passwordNameInput = "";


            do {
                System.out.print("User Name : ");
                userNameInput = keyboard.nextLine();

                if (userNameInput.equals("0")) {
                    user.setSelection(0);
                    return user;
                }

                for (User loopUser : loadUser) {
                    if (userNameInput.equals(loopUser.getName())) {
                        user = loopUser;
                        userNameInput = "Found";

                        break;
                    }

                    userNameInput = "Not Found";
                    System.out.println("Not Found");

                }

            } while (userNameInput.equals("Not Found"));

//
//
//
//
//        do
//        {
//            System.out.print("Password : ");
//            passwordNameInput = keyboard.nextLine();
//
//            if (passwordNameInput.equals("0"))
//            {
//                return -1;
//            }
//
//
//            else if (passwordNameInput.equals(User.getPassword()))
//            {
//                    passwordNameInput = "Match";
//                    break;
//            }
//
//                passwordNameInput = "Not Match";
//                System.out.println("Not Match");
//
//
//
//        } while (passwordNameInput.equals("Not Match"));

        }
        pass = true;


        showName();
        showMenu();


        user.setSelection(selection());

        if (user.getSelection() == -1)
        {
            pass = false;
            return new User();
        }

        return user;
    }

}
