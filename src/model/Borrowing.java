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
public class Borrowing implements Serializable{
    private int id;
    private Reader reader =  new Reader();
    private ArrayList<BookState> bookState = new ArrayList<>();

    public Borrowing() {
    }

    public Borrowing(int id, Reader reader, ArrayList<BookState> bookState) {
        this.id = id;
        this.reader = reader;
        this.bookState = bookState;
    }
    
    public boolean writeFile(ArrayList<Borrowing> b) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            File f = new File("QLMS.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            Borrowing borrowing;
            while(b.size() > 0){
                borrowing = b.get(b.size() - 1);
                oos.writeObject(borrowing);
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

    public ArrayList<Borrowing> readFile() {
        ArrayList<Borrowing> b = new ArrayList<>();
        try {
            FileInputStream fis = null;
            ObjectInputStream ois = null;

            File f = new File("QLMS.DAT");
            if (!f.exists()) {
                f.createNewFile();
            }
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            while(fis.available() > 0)
                b.add((Borrowing) ois.readObject());

        } catch (IOException | ClassNotFoundException ex) {
        }
        return b;
    }
    
    public void addFromKeyBoard(Reader reader, BookState bookState){
        this.bookState.add(bookState);
        this.reader = reader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<BookState> getBookState() {
        return bookState;
    }

    public void setBookState(ArrayList<BookState> bookState) {
        this.bookState = bookState;
    }

    @Override
    public String toString() {
        return "Borrowing{" + " reader=" + reader + ", bookState=" + bookState + "}\n";
    }
    
    
}
