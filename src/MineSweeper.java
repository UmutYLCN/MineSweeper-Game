import java.util.Scanner;
import java.util.Random;

public class MineSweeper {

    int row_number;
    int col_number;
    int size;
    char map[][];
    boolean mines [][];

    MineSweeper(int rowNumber , int colNumber){
        this.col_number = colNumber;
        this.row_number = rowNumber;
        this.map = new char[rowNumber][colNumber];
        this.mines = new boolean[rowNumber][colNumber];
        this.size = rowNumber * colNumber;
    }
    public static void main(String[] args) throws Exception {

        int mapRow,mapCol;
        Scanner scanner = new Scanner(System.in);
        System.out.println("===========================");
        System.out.println("MineSweeper Oyuna Hoşgeldiniz!");
        System.out.println("===========================");
        System.out.print("Satır sayısını giriniz: ");
        mapRow = scanner.nextInt();
        System.out.print("Sütun sayısını giriniz: ");
        mapCol = scanner.nextInt();
        MineSweeper game = new MineSweeper(mapRow, mapCol);
        game.playRun();
        
        scanner.close();
    }

    public void playRun(){
        int row, col,success=0;
        boolean gameOn = true;
        Scanner scan = new Scanner(System.in);
        configureMap(mines);
        // printMines(mines); 
        createMap(map);

        while(gameOn){
            printMap(map);
            System.out.print("Satir giriniz : ");
            row = scan.nextInt();
            System.out.print("Sutun giriniz : ");
            col = scan.nextInt();
            
            if(row < 0  || row >= row_number){
                System.out.println("Gecersiz koordinat !");
                continue;
            }

            if(col < 0  || col >= col_number){
                System.out.println("Gecersiz koordinat !");
                continue;
            }

            if (mines[row][col] != true) {
                check(row, col);
                success++;
                if(success == (size - (size/4))){
                    System.out.println("======= Game Won =======");
                    break;
                }
                
            } else {
                gameOn = false;
                System.out.println("======= Game Over! =======");
            }
        }

        scan.close();
    }

    public void check(int row, int col){
        
        int mineCount=0;

        if (map[row][col] == '-'){
            //sag
            if( (col < col_number - 1) && (mines[row][col + 1] == true)){
                mineCount++;
            }

            //alt
            if ( (row < row_number -1) && (mines[row + 1][col] == true)) {
                mineCount++;
            }
            
            //ust
            if ( (row > 0)  && (mines[row - 1][col] == true)) {
                mineCount++;
            }

            //sol
            if ( (col > 0)  && (mines[row][col - 1] == true)) {
                mineCount++;
            }

            //Duzeltilecek
            //solust +
            if((row > 0) && (col > 0) && (mines[row - 1][col - 1] == true)){
                mineCount++;
            }

            //sagust + 
            if((row > 0)  && (col < col_number -1) && (mines[row - 1][col + 1] == true)){
                mineCount++;
            }

            //sagalt +
            if((col < col_number -1) && (row < row_number -1) && (mines[row + 1][col + 1] == true)){
                mineCount++;
            }

            //solalt
            if((row < row_number -1) && (col > 0) && (mines[row + 1][col - 1] == true)){
                mineCount++;
            }
            
            if(map[row][col] == '-'){
               map[row][col] = (char) (mineCount + '0');
            }

        }
    }
    
    public void configureMap(boolean[][] arr) {

        int randRow,randCol,count=0;
        Random rand = new Random();

        while (count != (size / 4)) {
            randRow = rand.nextInt(arr.length);
            randCol = rand.nextInt(arr[0].length);
            if(arr[randRow][randCol] != true){
                arr[randRow][randCol] = true;
                count++;
            } 
        }
    }

    public  void createMap(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public  void printMap(char[][] arr) {
        System.out.println("===========================");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }
    

    public void printMines(boolean[][] arr) {
        System.out.println("===========================");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

}
