/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Hendrick
 */
public class Tetris extends JPanel {

    private Tetromino current = Tetromino.randomOne();
    private Tetromino next = Tetromino.randomOne();
    
    private final int row = 20;
    private final int col = 10;
    private int score = 0;

    private boolean gameOver = false;
    
    private final Cell[][] wall = new Cell[row][col];
    private static final int CELL_SIZE = 26;
    
    void drawWall(Graphics g) {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                int x = CELL_SIZE * j;
                int y = CELL_SIZE * i;
                
                Cell cell = wall[i][j];
                if (cell == null) {
                    g.setColor(Color.pink);//warna wallpaper pada wall
                    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.white);//warna garis pada wall
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.gray);//warna tulisan
                } else {
                    g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }
    } 
    
    void drawCurrent(Graphics g) {
        Cell[] cells = current.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
    }
    
    void drawNext(Graphics g) {
        Cell[] cells = next.cells;
        for (Cell cell : cells) {
            g.drawString("Next puzzle : ", 335, 260);
            g.drawString("`````````````````````````````````` ", 320, 300);
            g.drawString("< ", 320, 306);
            g.drawString("> ", 320, 310);
            g.drawString("< ", 320, 314);
            g.drawString("> ", 320, 318);
            g.drawString("< ", 320, 322);
            g.drawString("> ", 320, 326);
            g.drawString("< ", 320, 330);
            g.drawString("> ", 320, 334);
            g.drawString("< ", 320, 338);
            g.drawString("> ", 320, 342);
            g.drawString("< ", 320, 346);
            g.drawString("> ", 320, 350);
            g.drawString("< ", 320, 354);
            g.drawString("> ", 320, 358);
            g.drawString("< ", 320, 362);
            g.drawString("> ", 320, 366);
            g.drawString("< ", 320, 370);
            g.drawString("> ", 320, 374);
            g.drawString("< ", 320, 378);
            
            int x = cell.getCol() * CELL_SIZE + 260;
            int y = cell.getRow() * CELL_SIZE + 310;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
            g.drawString("`````````````````````````````````` ", 320, 390);
        }
    }
    
   @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.gray);//warna tulisan
        if(gameOver){
            g.drawString("GAME OVER", 218, 160);
            // akan muncul game over ketika mentok di atas 
            g.drawString("          Your Score  "+score+"      ", 180, 190);
            //akan menampilkan angka score ketika puzzle ada dlm wall sampai game over
            g.drawString("--- Do it better next time --- ", 182, 220);
            g.drawString("       --- Do'nt give up ---      ", 180, 235);
            g.drawString("Press [ENTER] To Start Again", 173, 280);
            //ketika game over , bisa menekan tombol space untuk memainkan ulang game ini
        }else{
            //untuk draw kotak2 pagar
            drawWall(g);
            //ini untuk draw yang lagi turun, turun pertama akan tetap jalan walau ini di tutup
            drawCurrent(g);
            //yang ada di atas kann, next akan turun object apaa
            drawNext(g);
            //untuk gambar score di atas
            g.drawString("Press [UP] to rotate", 270, 20);
            g.drawString("Press [ARROW] to go rigth and left ", 270, 40);
            g.drawString("Score: "+score+"", 335, 200);
            //ini untuk munculin kalau seandainya sudah game over
        }
    }
    
    boolean outOfBound() { // di bawah
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celRow = cell.getRow();
            if (celRow <= 0 || celRow >= row-1){
                return true;
            }
        }
        return false;
    }
    
    boolean tooLeft() { // terlalu ke kiri
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (celCol <= 0){
                return true;
            }
            if (wall[celRow][celCol-1] != null){
                return true;
            }   
        }
        return false;
    }
        
    boolean tooRight() { // terlalu ke kanan
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (celCol >= col-1){
                return true;
            }
            if (wall[celRow][celCol+1] != null){
                return true;
            }
        }
     return false;
    }
    
    boolean coincide() { // kalau ketemu bidak lainnya
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (wall[celRow][celCol] != null){
                return true;
            }
        }
        return false;
    }
    
    protected void removeLine() { // mengecek baris yang sudah full dengan bidak akan hilang
        int bottomLine = wall.length - 1;
        for(int topLine = wall.length -1; 0 < topLine; topLine -- ) {
            int count = 0;
            /* 
                ini artinya kalau bottom line sudah gak ada yang kosong , berarti bottom line akan dihapus
                dan topline ditmbh 1 row lagi
            */
            for(int i = 0; i < wall[0].length; i++ ) {
                if(wall[topLine][i] != null) {
                    count++;
                }
                wall[bottomLine][i] = wall[topLine][i];
            }
            if (count < wall[0].length) {
                bottomLine --;
            } else if(count == wall[0].length){
                score = score + 10; // akan menambah score sebanyak 10 ketika terdapat 1 baris yang sudah full dan di hapus
            }
        }
    }
    
    boolean isDrop() { // pemeriksaan kesempatan turun
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if(celRow == row-1) {
                return false;
            }
            
            if (wall[celRow+1][celCol] != null){
                return false;
            }
        }
        return true;
        
    }
    
    void stopDropping () { // jika bidak sudah sampai bawah
        Cell[] cells = current.cells;
        for(Cell cell : cells) {
            int celrow = cell.getRow();
            int celcol = cell.getCol();
            wall[celrow][celcol] = cell;
        }
    }

    //drop pakai inputan atau drop sendri
    protected void softDrop() {
        if(isDrop()) {
            removeLine();
            current.softDrop();
        } else if (!coincide()) {
            stopDropping();
            current = next;
            score = score+1;
            next = Tetromino.randomOne();
        }
    }
    
    protected void moveLeft() {
        if(!tooLeft() && !outOfBound() && !coincide())
            current.moveLeft();
    }
    
    protected void moveRight() {
        if(!tooRight() && !outOfBound() && !coincide())
            current.moveRight();
    }
    

    /*  ketika mulai jalan, akan memasuki start, disini kita ada sebuah keylistener 
        untuk define jika ada inputan dari keyboard kita harus melakukan action apa, 
        saya menambahkan inputan key event up untuk rotate, key event enter untuk 
        berhenti bermain, key event enter untuk memulai game yang baru lagi
    */
    public void start() {
        KeyListener keylist = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent args) {
                int key = args.getKeyCode();
                
                switch(key) {
                    case KeyEvent.VK_DOWN : {
                        softDrop();
                        break;
                    } case KeyEvent.VK_LEFT : {
                        moveLeft();
                        break;
                    } case KeyEvent.VK_RIGHT : {
                        moveRight();
                        break;
                    } case KeyEvent.VK_UP : {
                        if(!tooLeft() && !outOfBound() && !coincide() && !tooRight()){
                            //method rotate ada di class Tetromino, lalu akan di overload di tiap2 class SHAPE 
                            //(hasil extend tetromino) untuk menentukan arah rotation
                            current.rotate();
                        }
                        break;
                    } case KeyEvent.VK_ENTER:{
                        if(gameOver){
			//ini maksudnya ketika sudah gameover ,makan tekan enter untuk membuka file tetris yang baru lagi
                            gameOver=false;
                            score=0;
                            main(null);
                        }
                        break;
                    }  
                }
                //artinya, ketika kita melakukan input sesuatu, contoh key down, maka kita harus mengambar ulang objectnya
                //1 row kebawah.
                repaint();
            }
        };
        //add keylistener
        this.addKeyListener(keylist);
        this.requestFocus();

        //ini adalah untuk setiap bentuk yang akan turun
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    //cek is drop terpenuhi atau enggak, selama masih akan turun dia akan oanggil softdrop
                    if (isDrop()){
                        softDrop();
                        /*ini akan dijalakan, walaupun if yang diatas sudah di jalankan adalah 
                          kondisi dimana jika sudah tidak bisa turun kebawah lagi, ketika sudah 
                          tidak bisa turun, maka kita akan
                        */
                    } else if (!coincide()) {
                        stopDropping();
                        current = next;
                        //ini berarti sudah sampai bawah, score kita tambah 1
                        score = score+1;
                        /*ketika sudah sampai di bawah, kita akan ngerandom 1 object untuk di turunin lagi, 
                        kemudian kita akan panggi repaint untuk gambar objectnya,
                        proses ini berlangsung sampai kon
                        */
                        next = Tetromino.randomOne();
                        /*ini akan di jalankan ketika kondisi id tidak terpenuhi, 
                          artinya sudah tidak bisa turun lagi yang artinya game sudah selesai
                        */
                    } else {
                        //set game over flag to true, kalau game over flag sudah true, maka kita akan gambar kata "GAME OVER"
                        gameOver = true;
                    }
                    //ini proses gambar ulang
                    repaint();
                }
            }
        }.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Tetris");
        frame.setSize(530, 580);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Tetris tetrisPanel = new Tetris();
        frame.add(tetrisPanel);
        frame.setVisible(true);
        tetrisPanel.start();
    }
    
}