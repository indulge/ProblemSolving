/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.archive.linkedlist;

/**
 *
 * @author sachin
 */
import java.io.IOException;

public class ParsePostfix {
  private Stack theStack;

  private String input;

  public ParsePostfix(String s) {
    input = s;
  }

  public int doParse() {
    theStack = new Stack(20);
    char ch;
    int j;
    int num1, num2, interAns;

    for (j = 0; j < input.length(); j++) {
      ch = input.charAt(j);
      theStack.displayStack("" + ch + " ");
      if (ch >= '0' && ch <= '9') // if a number push it
        theStack.push((int) (ch - '0'));
      else // it's an operator
      {
        num2 = theStack.pop();
        num1 = theStack.pop();
        switch (ch) {
        case '+':
          interAns = num1 + num2;
          break;
        case '-':
          interAns = num1 - num2;
          break;
        case '*':
          interAns = num1 * num2;
          break;
        case '/':
          interAns = num1 / num2;
          break;
        default:
          interAns = 0;
        }
        theStack.push(interAns);
      }
    }
    interAns = theStack.pop();
    return interAns;
  }

  public static void main(String[] args) throws IOException {
    String input = "1-2+3*4+5/6-7+8*9";
    int output;
    ParsePostfix aParser = new ParsePostfix(input);
    output = aParser.doParse();
    System.out.println("Evaluates to " + output);
  }
  class Stack {
    private int maxSize;

    private int[] stackArray;

    private int top;

    public Stack(int size) {
      maxSize = size;
      stackArray = new int[maxSize];
      top = -1;
    }

    public void push(int j) {
      stackArray[++top] = j;
    }

    public int pop() {
      return stackArray[top--];
    }

    public int peek() {
      return stackArray[top];
    }

    public boolean isEmpty() {
      return (top == -1);
    }

    public boolean isFull() {
      return (top == maxSize - 1);
    }

    public int size() {
      return top + 1;
    }

    public int peekN(int n) {
      return stackArray[n];
    }

    public void displayStack(String s) {
      System.out.print(s);
      System.out.print("Stack (bottom>top): ");
      for (int j = 0; j < size(); j++) {
        System.out.print(peekN(j));
        System.out.print(' ');
      }
      System.out.println("");
    }
  }
}
