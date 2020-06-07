package com.company.controller;

import com.company.model.Model;
import com.company.parsers.DomParser;
import com.company.parsers.SaxParser;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<Model> tableData;
    private boolean bName;
    private boolean bSurname;
    private boolean bAccountNumber;
    private boolean bPhoneNumber;
    public Controller(){
        tableData = new ArrayList<Model>();
    }
    private List<Model> FindTemplate(String surname, String name, String accountNumber, String phoneNumber) {
        List<Model> temp = new ArrayList<>();
        for (Model tableDatum : tableData) {
            boolean bIsFits = (tableDatum.getName().equals(name) || bName)
                    && (tableDatum.getSurname().equals(surname) || bSurname)
                    && (tableDatum.getAccountNumber().equals(accountNumber) || bAccountNumber)
                    && (tableDatum.getMobileNumber().equals(phoneNumber) ||
                    tableDatum.getLandLineNumber().equals(phoneNumber) || bPhoneNumber);
            if(bIsFits){
                temp.add(tableDatum);
            }
        }
        return temp;
    }
    public List<Model> FindClients(String surname, String name, String accountNumber, String phoneNumber){
        bSurname = (surname.equals(""));
        bName = (name.equals(""));
        bAccountNumber = (accountNumber.equals(""));
        bPhoneNumber = (phoneNumber.equals(""));
        return FindTemplate(surname, name, accountNumber, phoneNumber);
    }
    public int DeleteClients(String surname, String name, String accountNumber, String phoneNumber){
        bSurname = (surname.equals(""));
        bName = (name.equals(""));
        bAccountNumber = (accountNumber.equals(""));
        bPhoneNumber = (phoneNumber.equals(""));
        List<Model> temp = FindTemplate(surname, name, accountNumber, phoneNumber);
        int amount = temp.size();
        tableData.removeAll(temp);
        return amount;
    }
    public void Add(Model customer){
        tableData.add(customer);
    }
    public void Read(String pathToFile) throws ParserConfigurationException, SAXException, IOException {
        SaxParser sax = new SaxParser();
        sax.parse(pathToFile);
        tableData = sax.getClients();
    }
    public void Write(String pathToFile){
        DomParser dom = new DomParser();
        dom.writeStudents(tableData, pathToFile);
        dom.writeBooks();
    }
    public Model atIndex(int index){
        return tableData.get(index);
    }
    public boolean isExists(int index){
        return index < tableData.size();
    }
    public void setClients(List<Model> data){
        tableData = data;
    }
}