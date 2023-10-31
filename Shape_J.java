/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Hendrick
 */
public class Shape_J extends Tetromino{
    public Shape_J() {
        cells[0] = new Cell(0,4, Tetromino.J);
        cells[1] = new Cell(0,3, Tetromino.J);
        cells[2] = new Cell(0,5, Tetromino.J);
        cells[3] = new Cell(1,5, Tetromino.J);
    }
            @Override
    public void rotate() {
        //karena class SHAPE_I extend tetromino, makan class shape_i akan punya semua method & variable yang ada di Tetromino
        //variabel state di initialize di class Tetromino, jadi disini bisa langsung pakai tanpa inisialisai ulang
        //disini karena bentuk J ada 4 maka setiap inputan ganjil akan merubah jadi bentuk 1, dan inputan genap akan jadi bentuk 2
        state = (state + 1) % 4;

        switch (state) {
            case 1:
                cells[0] = new Cell(cells[0].getRow(), cells[0].getCol() + 1 , Tetromino.J);
                cells[1] = new Cell( 1 + cells[1].getRow() , cells[1].getCol() + 2 , Tetromino.J);
                cells[2] = new Cell( 2 + cells[2].getRow() , cells[2].getCol() - 1 , Tetromino.J);
                cells[3] = new Cell( 1 + cells[3].getRow() , cells[3].getCol(), Tetromino.J);
                break;
            case 2:
                cells[0] = new Cell(cells[0].getRow() , cells[0].getCol() - 2 , Tetromino.J);
                cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() - 2 , Tetromino.J);
                cells[2] = new Cell(cells[2].getRow() - 1 , cells[2].getCol() , Tetromino.J);
                cells[3] = new Cell(cells[3].getRow() - 1 , cells[3].getCol() , Tetromino.J);
                break;
            case 3:
                cells[0] = new Cell(1 + cells[0].getRow() , cells[0].getCol() , Tetromino.J);
                cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() + 1 , Tetromino.J);
                cells[2] = new Cell(1 + cells[2].getRow() , cells[2].getCol() - 1 , Tetromino.J);
                cells[3] = new Cell(2 + cells[3].getRow() , cells[3].getCol() - 2 , Tetromino.J);
                break;
            case 0:
                cells[0] = new Cell(cells[0].getRow() , cells[0].getCol() + 1 , Tetromino.J);
                cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() - 1 , Tetromino.J);
                cells[2] = new Cell(cells[2].getRow() - 1 , cells[2].getCol() + 2 , Tetromino.J);
                cells[3] = new Cell(cells[3].getRow() - 1 , cells[3].getCol() + 2 , Tetromino.J);
                break;
        }
    }
}
