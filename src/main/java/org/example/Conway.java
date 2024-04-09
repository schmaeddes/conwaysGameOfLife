package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Conway extends JFrame {

    HashMap<Integer, Cell[]> cellMap = new HashMap<>();

    public Conway() {
        setLayout(new GridLayout(19,19, 3, 3));
        setBackground(Color.black);
        setSize(new Dimension(500, 500));
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        int row = 1;
        int column = 1;

        Cell[] cellArray = new Cell[19];

        for (int i = 0; i < 361; i++) {
            if (column % 20 == 0) {
                cellMap.put(row, cellArray);
                cellArray = new Cell[19];

                row++;
                column = 1;
            }

            Cell cell = new Cell(row, column);
            cell.setOpaque(true);
            cell.setPreferredSize(new Dimension(50, 50));
            cell.setMinimumSize(new Dimension(50, 50));
            cell.setText(Integer.toString(i));

            //if (i % 2 == 0) {
            //    cell.setBackground(Color.lightGray);
            //} else {
            //    cell.setBackground(Color.white);
            //}

            cellArray[column - 1] = cell;
            add(cell);
            column++;
        }

        revalidate();

        cellMap.get(3)[7].setAlive(true);
        cellMap.get(3)[8].setAlive(true);
        cellMap.get(4)[7].setAlive(true);

    }

    private int aliveNeighbours(Cell cell) {
        int sum = 0;

        if (cell.getRow() > 1) {
            if (cell.getColumn() > 1) {
                // top left
                cellMap.get(cell.getRow() - 1)[cell.getColumn() - 1].isAlive() ? sum++

            }





            // top
            // top right


        }

        // left
        // right
        // bottom left
        // bottom
        // bottom right


    }



}
