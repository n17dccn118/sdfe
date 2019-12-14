/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Welcome
 */
public class QL {

    private ArrayList<Borrowing> borrowingList = new ArrayList<>();
    private ArrayList<Book> bookList;
    private ArrayList<Reader> readerList;
    public int bookID, readerID;

    public QL() {
    }

    public int getBookID() {
        int id = 9999;
        for (Book book : bookList) {
            if (book.getId() > id) {
                id = book.getId();
            }
        }
        return ++id;
    }

    public int getReaderID() {
        int id = 9999;
        for (Reader reader : readerList) {
            if (reader.getId() > id) {
                id = reader.getId();
            }
        }
        return ++id;
    }

    public void ReadFileBook() {
        bookList = new Book().readFile();
    }

    public void ReadFileReader() {
        readerList = new Reader().readFile();
    }

    public void ReadFileBorrowing() {
        borrowingList = new Borrowing().readFile();
    }

    public void prepare() {
        ReadFileBook();
        ReadFileReader();
        ReadFileBorrowing();
    }

    public void finish() {
        WriteFileBook();
        WriteFileReader();
        WriteFileBorrowing();
    }

    public void WriteFileBook() {
        new Book().writeFile(bookList);
    }

    public void WriteFileReader() {
        new Reader().writeFile(readerList);
    }

    public void WriteFileBorrowing() {
        new Borrowing().writeFile(borrowingList);
    }

    public void Menu() {
        Scanner scan = new Scanner(System.in);
        Book b;
        Reader r;
        Borrowing br;
        boolean isOK = false;
        int key = 0;
        while (true) {
            do {
                try {
                    do {
                        System.out.println("========MENU=========");
                        System.out.println("1.Nhap sach! ");
                        System.out.println("2.In DS sach!");
                        System.out.println("3.Nhap doc gia! ");
                        System.out.println("4.In DS doc gia!");
                        System.out.println("5.Xoa DS sach!");
                        System.out.println("6.Xoa DS doc gia!");
                        System.out.println("7.Muon sach!");
                        System.out.println("8.In DS muon sach!");
                        System.out.println("9.Sap xep DS QLMS theo ten!");
                        System.out.println("10.Xoa DS QLMS!");
                        System.out.println("Nhap lua chon: ");
                        key = Integer.parseInt(scan.nextLine());
                        isOK = true;
                    } while (key > 10 && key < 0);
                } catch (NumberFormatException e) {
                    System.out.println("Nhap sai! Nhap lai");
                }
            } while (!isOK);
            switch (key) {
                case 0:
                    return;
                case 1:
                    b = new Book();
                    b.addFromKeyBoard(getBookID());
                    bookList.add(b);
                    break;
                case 2:
                    System.out.println(bookList.toString());
                    break;
                case 3:
                    r = new Reader();
                    r.addFromKeyBoard(getReaderID());
                    readerList.add(r);
                    break;
                case 4:
                    System.out.println(readerList.toString());
                    break;
                case 5:
                    bookList.removeAll(bookList);
                    break;
                case 6:
                    readerList.removeAll(readerList);
                    break;
                case 7:
                    BorrowingBook();
                    break;
                case 8:
                    System.out.println(borrowingList.toString());
                    break;
                case 9:
                    Collections.sort(borrowingList, new Comparator<Borrowing>() {    
                        @Override
                        public int compare(Borrowing b1 , Borrowing b2) {
                            return b1.getReader().getFullName().compareTo(b2.getReader().getFullName());
                        }
                    }
                    );
                    break;
                case 10:
                    borrowingList.removeAll(borrowingList);
                    break;
            }
        }
    }

    public void BorrowingBook() {
        Scanner scan = new Scanner(System.in);
        Reader r = new Reader();
        BookState bs = new BookState();
        Borrowing br = new Borrowing();

        boolean isOK = false;
        int key = 0;
        do {
            try {
                do {
                    isOK = false;
                    System.out.println("Ban co the muon chua?(1.Co 2.Khong)");
                    key = Integer.parseInt(scan.nextLine());
                    isOK = true;
                } while (key > 2 && key < 1);
            } catch (NumberFormatException e) {
                System.out.println("Nhap sai! nhap lai");
            }
        } while (!isOK);
        isOK = false;
        int id;
        if (key == 1) {
            do {
                try {
                    System.out.println("Nhap ma doc gia(Nhap 0 de thoat): ");
                    id = Integer.parseInt(scan.nextLine());
                    if (isReaderIDValid(id)) {
                        isOK = true;
                        r = getReader(id);
                    } else {
                        System.out.println("Ma doc gia khong ton tai!");
                    }
                    if (id == 0) {
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nhap sai! nhap lai");
                }
            } while (!isOK);

        } else {
            r.addFromKeyBoard(getReaderID());
            readerList.add(r);
        }

        isOK = false;
        id = 0;
        do {
            try {
                do {
                    System.out.println("Nhap ma sach: ");
                    id = Integer.parseInt(scan.nextLine());
                } while (!isBookIDValid(id));
                isOK = true;
            } catch (NumberFormatException e) {
                System.out.println("Nhap sai! nhap lai");
            }
        } while (!isOK);
        bs.addFromKeyBoard(getBook(id));
        br.addFromKeyBoard(r, bs);
        borrowingList.add(br);
    }

    public Book getBook(int id) {
        Book b = new Book();
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return b;
    }

    public Reader getReader(int id) {
        Reader r = new Reader();
        for (Reader reader : readerList) {
            if (reader.getId() == id) {
                return reader;
            }
        }
        return r;
    }

    public boolean isReaderIDValid(int id) {
        for (Reader reader : readerList) {
            if (reader.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isBookIDValid(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
