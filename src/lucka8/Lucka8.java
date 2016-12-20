package lucka8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Lucka8 {

    int[][] grid = new int[50][6];
    
    
    public void printGrid() {
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 50; x++) {
                System.out.print(grid[x][y]==1?"#":".");
            }
            System.out.println();

        }
    }
    
    public void executeIntruction(String input) {
        
        if (input.startsWith("rect")) {
            rect(Integer.parseInt(input.substring(input.indexOf(" ")+1, input.indexOf("x"))),
                    Integer.parseInt(input.substring(input.indexOf("x")+1)));
        } else if (input.startsWith("rotate row")) { // rotate row y=1 by 20
            rotateRow(Integer.parseInt(input.substring(input.indexOf("=")+1, input.indexOf(" by "))), 
                    Integer.parseInt(input.substring(input.lastIndexOf(" ")+1)));
        } else if (input.startsWith("rotate column")) {
            rotateColumn(Integer.parseInt(input.substring(input.indexOf("=")+1, input.indexOf(" by"))),
                    Integer.parseInt(input.substring(input.lastIndexOf(" ")+1)));
        } else {
            throw new IllegalArgumentException();
        }
        
    }
    
    public void multiInstruction(String filePath) {
      
        List<String> listOfInstructions = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("could not read " + filePath);
        }

        while (scanner.hasNext()) {
            listOfInstructions.add(scanner.nextLine().trim());
        }

        for (String s : listOfInstructions) {
            executeIntruction(s);
        }
        
    }
    

    public static void main(String[] args) {

        Lucka8 lucka8 = new Lucka8();
        lucka8.printGrid();
        lucka8.rect(3, 2);
        lucka8.printGrid();
        lucka8.rotateColumn(1,1);
        lucka8.printGrid();
        lucka8.rotateRow(0,4);
        lucka8.printGrid();

        lucka8 = new Lucka8();
        lucka8.multiInstruction("c:/java/Christmas/src/lucka8/data.txt"); //  ZJHRKCPLYJ
        lucka8.printGrid();
        System.out.println(lucka8.countLit());
    }

    private void rect(int x_size, int y_size) {
        System.out.println("rect " + x_size + " " + y_size);
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                   grid[x][y]=1;
            }
        }
    }

    private void rotateRow(int row, int steps) {
        System.out.println("Rotate row " + row + " " + steps);
        for (int i=0; i<steps; i++) rotateRow(row);
    }

    private void rotateRow(int row) {
        int[] memory = new int[50];
        for (int i=0; i<50; i++) {
            memory[i] = grid[i][row];
        }
        
        grid[0][row]=memory[49];
        for (int i=1; i<50; i++) {
            grid[i][row] = memory[i-1];
        }
        
    }
    
     private void rotateColumn(int col, int steps) {
         System.out.println("Rotate column " + col + " " + steps);
        for (int i=0; i<steps; i++) rotateCol(col);
    }

    private void rotateCol(int col) {
        int[] memory = new int[6];
        for (int i=0; i<6; i++) {
            memory[i] = grid[col][i];
        }
        
        grid[col][0]=memory[5];
        for (int i=1; i<6; i++) {
            grid[col][i] = memory[i-1];
        }
        
    }

    private int countLit() {
        int count =0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                count += grid[i][j];
            } 
        }
        return count;
    }
}
