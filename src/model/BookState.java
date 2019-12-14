package model;

import java.io.Serializable;
import java.util.Scanner;
import m_enum.StateOfBook;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Welcome
 */
public class BookState implements Serializable{

    private Book book;
    private int quanlity;
    private StateOfBook stateOfBook;

    public BookState() {
    }

    public BookState(Book book, int quanlity, StateOfBook stateOfBook) {
        this.book = book;
        this.quanlity = quanlity;
        this.stateOfBook = stateOfBook;
    }
    
    
    
    public void addFromKeyBoard(Book book) {
        Scanner scan = new Scanner(System.in);
        this.book = book;
        boolean isOk = false;
        do {
            try {
                System.out.println("Enter Quantity: ");
                this.quanlity = Integer.parseInt(scan.nextLine());
                System.out.println("Enter State Of Book(1.New 2.Old): ");
                int state = Integer.parseInt(scan.nextLine());
                switch (state) {
                    case 1:
                        this.stateOfBook = StateOfBook.New;
                        break;
                    case 2:
                        this.stateOfBook = StateOfBook.Old;
                        break;
                }
                isOk = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter the wrong quantity or state of book!");
            }
        } while (!isOk);

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public StateOfBook getStateOfBook() {
        return stateOfBook;
    }

    public void setStateOfBook(StateOfBook stateOfBook) {
        this.stateOfBook = stateOfBook;
    }

    @Override
    public String toString() {
        return "BookState{" + "book=" + book + ", quanlity=" + quanlity + ", stateOfBook=" + stateOfBook + "}\n";
    }
    
}
