/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.image.BufferedImage;

/**
 *
 * @author Hendrick
 */
public class Cell {
    private int row;
    private int col;
    private BufferedImage image;
    
    public Cell() { }
    
    public Cell(int row, int col, BufferedImage img) {
        this.row = row;
        this.col = col;
        this.image = img;
    }

    Cell(int row, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
    
    public void left() {
        col--;
    }
    
    public void right() {
        col++;
    }
    
    public void down() {
        row++;
    }
}
