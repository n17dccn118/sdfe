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
import m_enum.KindOfBook;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Welcome
 */
public class Book implements Serializable{

    private String fullName, author;
    private KindOfBook kindOfBook;
    private int Quantity, id, publishYear;

    public Book() {
    }

    public Book(int id, String fullName, String author, int publishYear, KindOfBook kindOfBook, int Quantity) {
        this.id = id;
        this.fullName = fullName;
        this.author = author;
        this.publishYear = publishYear;
        this.kindOfBook = kindOfBook;
        this.Quantity = Quantity;
    }

    public boolean writeFile(ArrayList<Book> b) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("SACH.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            Book book;
            while(b.size() > 0){
                book = b.get(b.size() - 1);
                oos.writeObject(book);
                b.remove(b.size() - 1);
            }
                
            oos.close();
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Book> readFile() {
        ArrayList<Book> b = new ArrayList<>();
        try {
            FileInputStream fis = null;
            ObjectInputStream ois = null;

            File f = new File("SACH.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while(fis.available() > 0)
                b.add((Book) ois.readObject());

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    public void addFromKeyBoard(int id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Book ID: " + id);
        this.id = id;
        System.out.println("Enter Book Name: ");
        this.fullName = scan.nextLine();

        System.out.println("Enter Author: ");
        this.author = scan.nextLine();

        boolean isOK = false;
        do {
            try {
                int major;
                do {
                    System.out.println("Enter Major(1.KHTN 2.VH-NT 3.DT-VT 4.CNTT): ");
                    major = Integer.parseInt(scan.nextLine());
                } while (major > 4 && major < 1);

                switch (major) {
                    case 1:
                        this.kindOfBook = KindOfBook.KhoaHọcTựNhiên;
                        break;
                    case 2:
                        this.kindOfBook = KindOfBook.VănHóaNghệThuật;
                        break;
                    case 3:
                        this.kindOfBook = KindOfBook.ĐiệnTửViễnThông;
                        break;
                    case 4:
                        this.kindOfBook = KindOfBook.CôngNghệThôngTin;
                        break;
                }

                System.out.println("Enter Publish Year: ");
                this.publishYear = Integer.parseInt(scan.nextLine());

                System.out.println("Enter Quantity: ");
                this.Quantity = Integer.parseInt(scan.nextLine());
                isOK = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter the wrong publish year, quantity or major!");
            }

        } while (!isOK);

    }

    public void Display() {
        System.out.println("Book ID: " + this.id);
        System.out.println("Book Name: " + this.fullName);
        System.out.println("Author: " + this.author);
        System.out.println("Publish Year: " + this.publishYear);
        System.out.println("Kind Of Book: " + this.kindOfBook);
        System.out.println("Quantity: " + this.Quantity);
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public KindOfBook getKindOfBook() {
        return kindOfBook;
    }

    public void setKindOfBook(KindOfBook kindOfBook) {
        this.kindOfBook = kindOfBook;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    private int PublishYear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Book{" + "fullName=" + fullName + ", author=" + author + ", kindOfBook=" + kindOfBook + ", Quantity=" + Quantity + ", id=" + id + ", publishYear=" + publishYear + "}\n";
    }

    
}
