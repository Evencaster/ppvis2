package com.company.view;

import com.company.controller.Controller;
import javax.swing.*;
import java.util.List;

public class SearchDialog extends JFrame {
    private Controller DC;
    private JButton findButton;
    private TablePanel tablePanel;
    private OptionPanel choosePanel = new OptionPanel();
    public SearchDialog(Controller DC){
        super("Find clients");
        this.DC = DC;
        setSize(800, 320);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        findButton = new JButton("Find");
        findButton.addActionListener(actionEvent -> {
            List<String> list = choosePanel.getCustomerFromFields();
            Controller temp = new Controller();
            temp.setClients(this.DC.FindClients(list.get(0), list.get(1), list.get(2), list.get(3)));
            tablePanel.setClients(temp);
            tablePanel.showTable(temp);
        });
        tablePanel = new TablePanel(DC);
        JPanel pan = new JPanel();
        pan.add(findButton);
        add(choosePanel);
        add(pan);
        add(tablePanel);
    }
}