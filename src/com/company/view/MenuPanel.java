package com.company.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JMenuItem save;
    private JMenuItem loadFile;
    public MenuPanel(){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JMenuBar menu = new JMenuBar();
        menu.setMaximumSize(new Dimension(100, 20));
        JMenu fileMenu = new JMenu("File");
        save = new JMenuItem("Save");
        loadFile = new JMenuItem("Open");
        fileMenu.add(save);
        fileMenu.add(loadFile);
        menu.add(fileMenu);
        add(menu);
        add(Box.createHorizontalGlue());
    }
    public JMenuItem getSave() {
        return save;
    }
    public JMenuItem getLoadFile() {
        return loadFile;
    }
}