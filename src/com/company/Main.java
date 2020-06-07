package com.company;

import com.company.controller.Controller;
import com.company.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        MainWindow window = new MainWindow(controller);
    }
}