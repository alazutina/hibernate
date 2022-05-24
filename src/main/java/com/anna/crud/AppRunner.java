package  com.anna.crud;

import com.anna.crud.view.MainView;


public class AppRunner {

    public static void main(String[]args){
        MainView mainView = new MainView();
        mainView.start();
        System.exit(0);
    }
}