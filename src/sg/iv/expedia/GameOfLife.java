package sg.iv.expedia;
// The world of the Game of Life is an two-dimensional M by N orthogonal grid of square cells, each of which is in one of two possible states, 
// alive or dead. Every cell interacts with its eight neighbors, which are the cells that are horizontally, vertically, or diagonally adjacent. At // each step in time, the following transitions occur:
// •   Any live cell with fewer than two live neighbors dies, as if caused by under-population.
// •   Any live cell with two or three live neighbors lives on to the next generation.
// •   Any live cell with more than three live neighbors dies, as if by overcrowding.
// •   Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

/*
o x o
o x o
o x o

o - dead
x - live

o o o
x x x
o o o

Goal: write (in Java) a transition function, which takes in the current state of world and returns the next state.
*/
class Cell {
    boolean alive;
    String symbol;
    
    public boolean isAlive() {
    	return alive;
    }
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
}

class World {

int maxRows;
int maxColumns;

public World (int m, int n) {
    maxRows = m;
    maxColumns = n;
    
}

public Cell[][] transition(Cell[][] currentState, int m, int n) {
    Cell[][] newstate = new Cell[m][n];
    //initialize newstate with all false
    
    for (int i = 0; i < m ; i++) {
        for (int j = 0; j < n ; j++) {
            int neighbourCount = getNeighbourCount(currentState, i,j);
            if (currentState[i][j].isAlive()) {
                if (neighbourCount < 2) {
                    newstate [i][j].setAlive(false);
                } else if ( neighbourCount <= 3) {
                    newstate[i][j].setAlive(true);
                } else if ( neighbourCount > 3) {
                 newstate [i][j].setAlive(false);
                 }
            } else {    // cell is currently dead
                 if (neighbourCount == 3) {
                      newstate [i][j].setAlive(true);
                 }
            }    //end of check alive if block
        }    //for j
    } //for i;
    return newstate;
}

private int getNeighbourCount(Cell[][] currentState, int m, int n) {
    //test eight directions here
    int ret = 0;
    
      //cases: top row, first column, others
    int prevRow = m - 1;
    int prevCol = n - 1;
    
    //cases: bottom row, last column, others
    int nextRow = m + 1;
    int nextCol = n + 1;
    
    // eight positions to chek:
    // top left, top up, top right -- case 1
    //left, right
    //bottom left, bottom down, bottom right
    if (prevRow > 0) {    
        
        if (prevCol > 0) { // top left
            if (currentState[prevRow][prevCol].isAlive()) {
              ret ++;
            }
        } 
        
        //top up
        if (currentState[prevRow][n].isAlive()) {
            ret ++;
        }
        
        //top right
        
        if (nextCol < maxColumns) {
             if (currentState[prevRow][nextCol ].isAlive()) {
            ret ++;
            }
        }
        
        
        
    }    //end of (prevRow > 0)
    
   
    if (prevCol > 0) {
     // check left
        if (currentState[m][prevCol].isAlive()) {
            ret ++;
         }
         
         if (nextRow < maxRows) {    // bottom left 
           if (currentState[nextRow ][prevCol ].isAlive()) {
            ret ++;
         }
      }    // end of (prevCol > 0)
      
      if (nextCol < maxColumns) {
          //bottom right
         if (nextRow < maxRows) {  
         if (currentState[nextRow ][nextCol].isAlive()) {
            ret ++;
         }
         }
         
          //right
        if (currentState[m][nextCol].isAlive()) {  
            ret ++;
         }
        
      }
      
      // check for chell below current cell - bottom
      if (nextRow < maxRows ) {
      
       if (currentState[nextRow ][n].isAlive()) {  
            ret ++;
         }
         
      }
      
     
        
    }
		return ret;
    
}

}