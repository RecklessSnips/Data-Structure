package Recursion;

public class Maze {

    /* https://www.youtube.com/watch?v=9lxiHNwvYyg&list=PLmOn9nNkQxJFvyhDYx0ya4F75uTtUHA_f&index=45
    在一个2D array里给定一个起点和终点，要求在console里打印出从启示点到终点的一条最短路径，用2表示
    规则：墙不能通过，用1表示，所走的路径用3表示，但是此路不通，最终成功路径用2表示
     */
    private int[][] maze;

    public Maze (){
        maze = new int[8][7];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[0][j] = 1;
                maze[7][j] = 1;
                maze[i][0] = 1;
                maze[i][6] = 1;
            }
        }
        // 这里可以设置挡板，让它走不过去
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[3][3] = 1;
        maze[3][4] = 1;
        maze[3][5] = 1;
    }

    // the starting index form maze[i][j], to the target maze[x][y]
    // 规定按照上下左右的顺序去寻找
    public boolean go(int i, int j, int x, int y){
        // 如果已经走到了，则返回
        if (maze[x][y] == 2){
            return true;
        }
        // 开始走
        if (maze[i][j] == 0){
            maze[i][j] = 2;
            // 按照顺序开始找路
            // 上
            if (go(i - 1, j, x, y)){
                return true;
            }
            // 下
            else if (go(i + 1, j, x, y)) {
                return true;
            }
            // 左
            else if (go(i, j - 1, x, y)) {
                return true;
            }
            // 右
            else if (go(i, j + 1, x, y)){
                return true;
            }else{
                // 该点走不通
                maze[i][j] = 3;
            }
        }
        // 走过了, 或者遇到了墙壁
        return false;
    }

    public void show(){
        for(int[] row: maze){
            for(int col: row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.show();
        System.out.println(maze.go(4, 1, 2, 1));
        maze.show();
    }

}
