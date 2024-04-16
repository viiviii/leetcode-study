import java.util.*;

// https://leetcode.com/problems/01-matrix/
//
// memo:
// - 처음에 bfs로 풀었다가 아닌가? dfs인가?싶어 다시 바꿨다가 결국 bfs로 돌아왔음
// - 헷갈릴 땐 내가 손으로 직접 풀어볼 때의 그 순서대로 탐색 순서를 구현하면 된다
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] mat;

    public int[][] updateMatrix(int[][] mat) {
        this.mat = mat;

        search();

        return mat;
    }

    void search() {
        Queue<Offset> search = new LinkedList<>();

        for(int y=0; y<mat.length; y++) {
            for(int x=0; x<mat[0].length; x++) {
                var node = new Offset(x, y);
                if(node.getDistance() == 0) {
                    search.add(node);
                } else {
                    node.setDistance(-1);
                }
            }
        }

        while(!search.isEmpty()) {
            var node = search.poll();
            for(var dir : directions) {
                var next = node.add(dir);
                if(!next.inMap() || next.getDistance() != -1) {
                    continue;
                }
                next.setDistance(node.getDistance() + 1);
                search.add(next);
            }
        }
    }

    class Offset {
        final int x;
        final int y;

        Offset(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Offset add(int[] dir) {
            return new Offset(x + dir[0], y + dir[1]);
        }

        boolean inMap() {
            return x >= 0 && x < mat[0].length && y >= 0 && y < mat.length;
        }

        int getDistance() {
            return mat[y][x];
        }

        void setDistance(int distance) {
            mat[y][x] = distance;
        }
    }
}
