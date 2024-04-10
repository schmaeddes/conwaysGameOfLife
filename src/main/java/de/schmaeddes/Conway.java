package de.schmaeddes;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Conway extends JFrame {

    private static final int GRID_SIZE = 50;
    private static final int NUMBER_OF_CELLS = GRID_SIZE * GRID_SIZE;

    HashMap<Integer, Cell[]> cellMap = new HashMap<>();

    public Conway() throws InterruptedException {
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE, 3, 3));
        setSize(new Dimension(700, 700));
        setTitle("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        int row = 0;
        int column = 0;

        Cell[] cellArray = new Cell[GRID_SIZE];

        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            Cell cell = new Cell(row, column);
            cell.setOpaque(true);
            cell.setPreferredSize(new Dimension(20, 20));
            cell.setMinimumSize(new Dimension(20, 20));

            cellArray[column] = cell;
            add(cell);
            column++;

            if (column == GRID_SIZE) {
                cellMap.put(row, cellArray);
                cellArray = new Cell[GRID_SIZE];

                row++;
                column = 0;
            }
        }

        revalidate();

        randomizer(NUMBER_OF_CELLS / 2);
        loopOfLife();
    }

    private void randomizer(int numberOfRandomCells) {
        Random random = new Random();
        int sum = 0;

        while (sum < numberOfRandomCells) {
            int randomRow = random.nextInt(GRID_SIZE);
            int randomColumn = random.nextInt(GRID_SIZE);

            sum++;
            cellMap.get(randomRow)[randomColumn].setAlive(true);
        }
    }

    private void loopOfLife() throws InterruptedException {
        while (true) {
            Set<Cell> bornCells = new HashSet<>();
            Set<Cell> dyingCells = new HashSet<>();

            for (int i = 0; i < GRID_SIZE; i++) {

                for (Cell cell : cellMap.get(i)) {
                    int aliveNeighbours = aliveNeighbours(cell);

                    if (cell.isAlive()) {
                        if (aliveNeighbours < 2) {
                            dyingCells.add(cell);
                        }

                        if (aliveNeighbours > 3 ) {
                            dyingCells.add(cell);
                        }

                    } else {
                        if (aliveNeighbours == 3) {
                            bornCells.add(cell);
                        }
                    }
                }
            }

            for (Cell cell : bornCells) {
                cell.setAlive(true);
            }

            for (Cell cell : dyingCells) {
                cell.setAlive(false);
            }

        Thread.sleep(75);
        }
    }

    private int aliveNeighbours(Cell cell) {
        int sum = 0;

        if (cell.getRow() > 0) {
            if (cell.getColumn() > 0) {
                // top left
                if (cellMap.get(cell.getRow() - 1)[cell.getColumn() - 1].isAlive())
                    sum++;
            }

            // top
            if (cellMap.get(cell.getRow() - 1)[cell.getColumn()].isAlive())
                sum++;

            // top right
            if (cell.getColumn() < GRID_SIZE - 1) {
                // top left
                if (cellMap.get(cell.getRow() - 1)[cell.getColumn() + 1].isAlive())
                    sum++;
            }
        }

        // left
        if (cell.getColumn() > 0 && (cellMap.get(cell.getRow())[cell.getColumn() - 1].isAlive()))
            sum++;


        // right
        if (cell.getColumn() < GRID_SIZE - 1 && (cellMap.get(cell.getRow())[cell.getColumn() + 1].isAlive()))
            sum++;

        if (cell.getRow() < GRID_SIZE - 1) {
            if (cell.getColumn() > 0) {
                // bottom left
                if (cellMap.get(cell.getRow() + 1)[cell.getColumn() - 1].isAlive())
                    sum++;
            }

            // bottom
            if (cellMap.get(cell.getRow() + 1)[cell.getColumn()].isAlive())
                sum++;

            // bottom right
            if (cell.getColumn() < GRID_SIZE - 1) {
                if (cellMap.get(cell.getRow() + 1)[cell.getColumn() + 1].isAlive())
                    sum++;
            }
        }


        return sum;

    }



}
