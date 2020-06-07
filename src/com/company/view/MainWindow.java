package com.company.view;

import com.company.controller.Controller;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainWindow extends JFrame {
    private Controller controller;
    private AddDialog addDialog;
    private SearchDialog seachDialog;
    private DeleteDialog deleteDialog;
    private TablePanel tablePanel;
    public MainWindow(Controller controller) {
        super("Second lab");
        this.controller = controller;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Container mainView = new Container();
        BoxLayout mainViewLayout = new BoxLayout(mainView, BoxLayout.Y_AXIS);
        mainView.setLayout(mainViewLayout);
        FlowLayout layout = new FlowLayout();
        panel.setLayout(layout);
        addDialog = new AddDialog(this.controller);
        deleteDialog = new DeleteDialog(this.controller);
        seachDialog = new SearchDialog(this.controller);
        FindListener findListener = new FindListener();
        AddListener addListener = new AddListener();
        DelListener delListener = new DelListener();
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.getSave().addActionListener(new SaveListener());
        menuPanel.getLoadFile().addActionListener(new LoadListener());
        mainView.add(menuPanel);
        tablePanel = new TablePanel(this.controller);
        mainView.add(tablePanel);
        ToolBar controlPanel = new ToolBar();
        controlPanel.delete.addActionListener(delListener);
        controlPanel.find.addActionListener(findListener);
        controlPanel.add.addActionListener(addListener);
        panel.add(mainView);
        panel.add(controlPanel);
        deleteDialog.getDelete().addActionListener(actionEvent -> {
            int amount = deleteDialog.deleteClients();
            JOptionPane.showMessageDialog(this, "Deleted: " + amount);
            tablePanel.showTable(this.controller);
        });
        addDialog.add.addActionListener(actionEvent -> {
            addDialog.addCustomer();
            tablePanel.showTable(this.controller);
            addDialog.dispose();
        });
        add(panel);
        setSize(700, 300);
        setVisible(true);
    }
    private class FindListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            seachDialog.setVisible(!seachDialog.isVisible());
        }
    }
    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            addDialog.setVisible(!addDialog.isVisible());
        }
    }
    private class DelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            deleteDialog.setVisible(!deleteDialog.isVisible());
        }
    }
    private class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                controller.Write(path);
            }
        }
    }
    private class LoadListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Special xml file", "xml");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(chooser);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                try {
                    controller.Read(path);
                    tablePanel.showTable(controller);
                } catch (ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}