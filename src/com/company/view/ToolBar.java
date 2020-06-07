package com.company.view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    final int WIDTH = 100;
    final int HEIGHT = 40;
    public JButton add;
    public JButton delete;
    public JButton find;
    public ToolBar(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add = new JButton("Add");
        add.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        add(add);
        add(Box.createHorizontalGlue());
        delete = new JButton("Delete");
        delete.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        add(delete);
        add(Box.createHorizontalGlue());
        find = new JButton("Find");
        find.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        add(find);
    }
}