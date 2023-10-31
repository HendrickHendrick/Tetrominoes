/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Hendrick
 */
public class Shape_T extends Tetromino{
    public Shape_T() {
        cells[0] = new Cell(0,3, Tetromino.T);
        cells[1] = new Cell(0,4, Tetromino.T);
        cells[2] = new Cell(0,5, Tetromino.T);
        cells[3] = new Cell(1,4, Tetromino.T);
    }
    
    @Override
    public void rotate() {
    //karena class SHAPE_I extend tetromino, makan class shape_i akan punya semua method & variable yang ada di Tetromino
    //variabel state di initialize di class Tetromino, jadi disini bisa langsung pakai tanpa inisialisai ulang
    //disini karena bentuk I ada 2 maka setiap inputan ganjil akan merubah jadi bentuk 1, dan inputan genap akan jadi bentuk 2
    state = (state + 1) % 4;

    switch (state) {
        case 1:
            cells[0] = new Cell(cells[0].getRow() , cells[0].getCol() + 1 , Tetromino.T);
            cells[1] = new Cell(1 + cells[1].getRow() , cells[1].getCol() , Tetromino.T);
            cells[2] = new Cell(2 + cells[2].getRow() , cells[2].getCol() - 1 , Tetromino.T);
            cells[3] = new Cell(cells[3].getRow() , cells[3].getCol() - 1 , Tetromino.T);
            break;
        case 2:
            cells[0] = new Cell(1 + cells[0].getRow() , cells[0].getCol() + 1 , Tetromino.T);
            cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() , Tetromino.T);
            cells[2] = new Cell( cells[2].getRow() - 1 , cells[2].getCol() - 1 , Tetromino.T);
            cells[3] = new Cell(cells[3].getRow() - 1 , cells[3].getCol() + 1 , Tetromino.T);
            break;
        case 3:
            cells[0] = new Cell(1 + cells[0].getRow() , cells[0].getCol() - 2 , Tetromino.T);
            cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() - 1 , Tetromino.T);
            cells[2] = new Cell(cells[2].getRow() - 1 , cells[2].getCol() , Tetromino.T);
            cells[3] = new Cell(1 + cells[3].getRow() , cells[3].getCol() , Tetromino.T);
            break;
        case 0:
            cells[0] = new Cell(cells[0].getRow() - 1 , cells[0].getCol() , Tetromino.T);
            cells[1] = new Cell(cells[1].getRow() , cells[1].getCol() + 1 , Tetromino.T);
            cells[2] = new Cell(1 + cells[2].getRow() , cells[2].getCol() + 2 , Tetromino.T);
            cells[3] = new Cell(1 + cells[3].getRow() , cells[3].getCol() , Tetromino.T);
            break;
    }
  }
}