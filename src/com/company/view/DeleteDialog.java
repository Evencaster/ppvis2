package com.company.view;

import com.company.controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeleteDialog extends JFrame {
    private Controller Controller;
    private OptionPanel choosePanel = new OptionPanel();
    private JButton delete;
    public DeleteDialog(Controller Controller){
        super("Delete client");
        this.Controller = Controller;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        init();
        setSize(800, 120);
        setResizable(false);
    }
    private void init() {
        delete = new JButton("Delete");
        delete.setMaximumSize(new Dimension(100, 20));
        add(choosePanel);
        add(delete);
    }
    public int deleteClients(){
        List<String> list = choosePanel.getCustomerFromFields();
        return  this.Controller.DeleteClients(list.get(0), list.get(1), list.get(2), list.get(3));
    }
    public JButton getDelete() {
        return delete;
    }
}