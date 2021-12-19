package Seo;

import java.util.*;
// 빛의 경로 사이클
// https://programmers.co.kr/learn/courses/30/lessons/86052
class Cycle {
    public static boolean[][][] tables;
    public static String[] grid_g;
    public static int N,M;
    // 직진은 0, 왼쪽 -1, 오른쪽 +1
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};
    public int[] solution(String[] grid) {
        List<Integer> list = new ArrayList<>();
        grid_g = grid;
        N = grid.length;
        M = grid[0].length();
        tables = new boolean[N][M][4];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int k=0;k<4;k++){
                    if(tables[i][j][k] == false){
                        list.add(getCycle(i,j,k));     
                    }
                }
            }
        }
        int[] answer = list.stream().mapToInt(num->num).sorted().toArray();
        return answer;
    }
    public int getCycle(int y, int x, int direction){
        int cur_y = y;
        int cur_x = x;
        int cur_direction = direction;
        int count =0;
        while(!tables[cur_y][cur_x][cur_direction]){
            count++;
            tables[cur_y][cur_x][cur_direction] = true;
            cur_direction = getNextDirection(cur_y,cur_x,cur_direction);
            cur_y = (cur_y + dy[cur_direction]+N)%N;
            cur_x = (cur_x + dx[cur_direction]+M)%M;
            
        }
        return count;
    }
    public int getNextDirection(int y, int x, int direction){
        int next_direction = -1;
        switch(grid_g[y].charAt(x)){
            case 'S' :
                next_direction = direction;
                break;
            case 'L':
                next_direction = direction -1;
                break;
            case 'R':
                next_direction = direction +1;
                break;
        }
        return (next_direction+4)%4;
    }
}