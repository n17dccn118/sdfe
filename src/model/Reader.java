package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Welcome
 */
public class Reader implements Serializable {

    private String fullName, address;
    private int id;
    private int phoneNumber;
    private final static int number = 9999;

    public Reader() {
        this.fullName = "";
    }

    public Reader(int id, String fullName, String address, int phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public boolean writeFile(ArrayList<Reader> r) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("BD.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            Reader reader;
            while (r.size() > 0) {
                reader = r.get(r.size() - 1);
                oos.writeObject(reader);
                r.remove(r.size() - 1);
            }
            oos.close();
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList<Reader> readFile() {
        ArrayList<Reader> r = new ArrayList<>();
        try {
            FileInputStream fis = null;
            ObjectInputStream oos = null;

            File f = new File("BD.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            oos = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                r.add((Reader) oos.readObject());
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void addFromKeyBoard(int id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Reader ID: " + id);
        this.id = id;
        System.out.println("Enter Full Name: ");
        this.fullName = scan.nextLine();
        System.out.println("Enter Address: ");
        this.address = scan.nextLine();
        boolean isOK = false;
        do {
            try {
                System.out.println("Enter Phone Number: ");
                this.phoneNumber = Integer.parseInt(scan.nextLine());
                isOK = true;
            } catch (NumberFormatException e) {
            }
        } while (!isOK);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" + "fullName=" + fullName + ", address=" + address + ", id=" + id + ", phoneNumber=" + phoneNumber + "}\n";
    }

}
