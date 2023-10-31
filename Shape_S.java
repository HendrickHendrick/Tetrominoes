/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Hendrick
 */
public class Shape_S extends Tetromino{
    public Shape_S() {
        cells[0] = new Cell(0,4, Tetromino.S);
        cells[1] = new Cell(0,5, Tetromino.S);
        cells[2] = new Cell(1,3, Tetromino.S);
        cells[3] = new Cell(1,4, Tetromino.S);
    }
        @Override
    public void rotate() {
        //karena class SHAPE_I extend tetromino, makan class shape_i akan punya semua method & variable yang ada di Tetromino
        //variabel state di initialize di class Tetromino, jadi disini bisa langsung pakai tanpa inisialisai ulang
        //disini karena bentuk Z ada 4 maka setiap inputan ganjil akan merubah jadi bentuk 1, dan inputan genap akan jadi bentuk 2
        state = (state + 1) % 2;

        switch (state) {
            case 1:
                cells[0] = new Cell(cells[0].getRow() , cells[0].getCol() - 1 , Tetromino.S);
                cells[1] = new Cell(2 + cells[1].getRow() , cells[1].getCol() - 1 , Tetromino.S);
                cells[2] = new Cell(cells[2].getRow() , cells[2].getCol() , Tetromino.S);
                cells[3] = new Cell(cells[3].getRow() , cells[3].getCol() , Tetromino.S);
                break;

            case 0:
                cells[0] = new Cell(cells[0].getRow() , cells[0].getCol() + 1, Tetromino.S);
                cells[1] = new Cell(cells[1].getRow() - 2 , cells[1].getCol() + 1, Tetromino.S);
                cells[2] = new Cell(cells[2].getRow() , cells[2].getCol() , Tetromino.S);
                cells[3] = new Cell(cells[3].getRow() , cells[3].getCol() , Tetromino.S);
                break;
        }
    }
}
