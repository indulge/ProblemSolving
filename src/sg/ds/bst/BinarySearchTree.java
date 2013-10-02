/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.ds.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author sachin
 */
public class BinarySearchTree {

    Node root = null;

    class Node {

        Node left = null;
        Node right = null;
        int data = 0;

        public Node(int data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }
        @Override
        public String toString() {
            return data+"";
        }
    }

    public void insert(int data) {
        root = insert(data, root);
    }

    public boolean lookup(int data) {
        return lookup(data, root);
    }

    public void buildTree(int numNodes) {
        root=null;
        for (int i = 0; i < numNodes; i++) {
            int num = (int) (Math.random() * 10);
            System.out.println("Inserting number:" + num);
            insert(num);
        }
    }

    public int size() {
        return size(root);
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    public int minValue() {
        return minValue(root);
    }

    public int maxValue() {
        return maxValue(root);
    }

    public void printTree() {   //inorder traversal
        System.out.println("inorder traversal:");
        printTree(root);
        System.out.println("\n--------------");
    }

    public void printPostorder() {   //inorder traversal
        System.out.println("printPostorder traversal:");
        printPostorder(root);
        System.out.println("\n--------------");
    }

    public int buildTreeFromOutputString(String op) {
        root = null;
        int i = 0;
        StringTokenizer st = new StringTokenizer(op);
        while (st.hasMoreTokens()) {
            String stNum = st.nextToken();
            int num = Integer.parseInt(stNum);
            System.out.println("buildTreeFromOutputString: Inserting number:" + num);
            insert(num);
            i++;
        }
        return i;
    }

    public boolean hasPathSum(int pathsum) {
        return hasPathSum(pathsum, root);
    }

    public void mirror() {
        mirror(root);
    }

    public void doubleTree() {
        doubleTree(root);
    }
 public int maxPathSum() {
        return maxPathSum(root,0);
    }
    public boolean sameTree(BinarySearchTree bst) { 
    	//is this tree same as another given tree?
        // return sameTree(this.root, bst.getRoot());
        return sameTree(this.root, bst.root);
    }

    public void printPaths() {
        if (root == null) {
            System.out.println("print path sum: tree is empty");
        }

        List<Integer> pathSoFar = new ArrayList<Integer>();
        printPaths(root, pathSoFar,0);

    }

    public boolean isBST() {
        return isBST(root);
    }

    public boolean isBST2() {
        return isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int countTrees(int numKeys) {
        if (numKeys <= 1) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= numKeys; i++) {
            //we are counting Binary search trees, so all keys smaller than given key go to left and greater go to right
            sum = sum + (countTrees(i - 1) * countTrees(numKeys - i));

        }
        return sum;
    }
    
    private Node nthInorder(int n) {
        return nthInorder(root,n);
    }
    ///-------------------------------------------Public helper functions
//    public Node getRoot() {
//        return root;
//    }
    //Exporting a non public Type through public API
    ///-------------------------------------------Helper Functions
    private int maxPathSum(Node node, int pathsum) {
        if (node==null) {
            return pathsum;
        }
        pathsum+=node.data;
        int sumL= maxPathSum(node.left,pathsum);
        int sumR= maxPathSum(node.right,pathsum);
        if (sumR>sumL) {
            return sumR;
        }
        return sumL;
    }
    private boolean isLeaf(Node node) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

   public void printLevelOrder() {
       
       Queue<Node> que = new LinkedList<Node>();
       if (root==null) return;
       que.add(root);
       System.out.println("\nLevel Order Traversal: ");
       while (!que.isEmpty()) {
           Node temp = que.remove();
           System.out.print(temp+", ");
           if (temp.left!=null)
           que.add(temp.left);

           if (temp.right!=null)
           que.add(temp.right);


       }
       System.out.println("\n");
   }

    ///-----------------------------------------------------------
    private boolean isBST2(Node node, int min_so_far, int max_so_far) {
        if (node == null) {
            return true;
        }
        //System.out.println (" node.data :"+node.data+" min so far :"+min_so_far+" max so far :"+max_so_far);
        //node must lie between  min_so_far and max_so_far
        if (min_so_far < node.data && node.data <= max_so_far) {
            return isBST2(node.left, min_so_far, node.data)
                    && isBST2(node.right, node.data, max_so_far);

        }
        return false;
    }

    private boolean isBST(Node node) {
        if (node == null) {
            return true;
        }
        int minVal = Integer.MIN_VALUE;
        int maxVal = Integer.MAX_VALUE;
        if (node.left != null) {
            minVal = this.minValue(node);
        }
        if (node.right != null) {
            maxVal = this.maxValue(node);
        }
        if (minVal <= node.data && maxVal > node.data) {
            return isBST(node.left) && isBST(node.right);
        }
        return false;
    }

    private boolean sameTree(Node n1, Node n2) {
        if ((n1 == null && n2 == null)) {
            return true;
        } else {
            if ((n1 == null || n2 == null)) {
                return false;
            } else {
                if ((n1.data == n2.data)) {
                    return (sameTree(n1.left, n2.left) 
                    		&& sameTree(n1.right, n2.right));
                }
            }
        }

        return false;


    }

    private void doubleTree(Node node) {
        //create a copy
        //bypass the copy to continue looping
        if (node == null) {
            return;
        }
        Node copyNode = new Node(node.data);
        Node temp = node.left;
        node.left = copyNode;
        copyNode.left = temp;
        doubleTree(copyNode.left);
        doubleTree(node.right);
    }

    private void mirror(Node node) {
        if (node == null) {
            return;
        }
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        mirror(node.left);
        mirror(node.right);
    }

    private void printPaths(Node node, List<Integer> pathSoFar, int pathSize) {
        if (node == null) {
            return;
        }
        
        if (isLeaf(node)) {
            pathSoFar.add(pathSize, node.data);
            System.out.println("path in tree:" + pathSoFar.subList(0, pathSize+1));
            pathSoFar.remove(pathSize); //only the current node, a node.data may be duplicated
            return;
        } else {
            pathSoFar.add(pathSize, node.data);
            pathSize++;
            printPaths(node.left, pathSoFar,pathSize);
            printPaths(node.right, pathSoFar,pathSize);
        }
    }

    private boolean hasPathSum(int pathsum, Node node) {
        if (node == null) {
            return false;
        }
        int val = pathsum - node.data;
        boolean ret = false;
        if (val == 0 && isLeaf(node)) {
            ret = true;
        } else if (val == 0 && !isLeaf(node)) {
            ret = false;
        } else if (val != 0 && isLeaf(node)) {
            ret = false;
        } else if (val != 0 && !isLeaf(node)) {
            //recurse further
            ret = hasPathSum(val, node.left) 
            || hasPathSum(val, node.right);
        }
        return ret;
    }

    public List<Integer> getInorder() {
       
        List<Integer> ret = new ArrayList<Integer>();
        getInorder(root, ret);
        return ret;
    }

    private List<Integer> getInorder(Node node, List<Integer> ret) {

        if (node == null) {
            return ret;
        }

        getInorder(node.left, ret);
        ret.add(node.data);
        getInorder(node.right, ret);
        return ret;
        //System.out.print(" " + node.data);
    }

    private void printPostorder(Node node) {  //inorder traversal
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(" " + node.data);

    }

    private void printTree(Node node) {  //inorder traversal
        if (node == null) {
            return;
        }
        printTree(node.left);
        System.out.print(" " + node.data);
        printTree(node.right);
    }

    private int minValue(Node node) {
        if (node == null) {
            //error case: this is not supported
            return -1;
        }
        if (node.left == null) {
            return node.data;
        } else {
            return minValue(node.left);
        }
    }

    private int maxValue(Node node) {
        if (node == null) {
            //error case: this is not supported
            return -1;
        }
        if (node.right == null) {
            return node.data;
        } else {
            return maxValue(node.right);
        }
    }
    private Node nthInorder(Node node,int n) {     
        if (node==null) {
            return null;
        }
         int lsize = size(node.left);
         if ((n-1)==(lsize)) {
             return node;
         }
         if ((n-1)<lsize) {
             return nthInorder(node.left,n);
         }         
        return nthInorder(node.right,n-(lsize+1));
    }

    private int maxDepth(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        int ldepth = 1 + maxDepth(node.left);
        int rdepth = 1 + maxDepth(node.right);

        if (ldepth > rdepth) {
            return ldepth;
        } else {
            return rdepth;
        }
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    private Node insert(int data, Node node) {
        if (node == null) {
            node = new Node(data);

        } else if (data <= node.data) {
            node.left = insert(data, node.left);
        } else {
            node.right = insert(data, node.right);
        }
        return node;
    }

    private boolean lookup(int data, Node node) {
        if (node == null) {
            return false;
        }
        if (node.data == data) {
            return true;
        }
        if (data < node.data) {
            return lookup(data, node.left);
        } else {
            return lookup(data, node.right);
        }
    }

    private void deleteNode (Node node) {
//        Deleting a leaf (node with no children): Deleting a leaf is easy, as we can simply remove it from the tree.
//        Deleting a node with one child: Delete the node and replace it with its child.
//        Deleting a node with two children: Call the node to be deleted "N". Do not delete N. 
//                Instead, choose either its in-order successor node or its in-order predecessor node, "R".
//                Replace the value of N with the value of R, then delete R. (Note: R itself has up to one child.)
//                        Successor = leftmost node in right subtree of node
//                        predesessor = rightmost in left subtree

    }
    public int getKthSmallest(int k) {
       // System.out.println("\n-getKthSmallest begin-");
        int ret = getKthSmallest(k,root);
        // System.out.println("\n-getKthSmallest end-");
        return ret;
    }
    public int countLeaves() {
        return countLeaves(root);
    }
    private int countLeaves(Node node) {
        if (node==null) {
            return 0;
        }
        if (isLeaf(node)) {
            return 1;
        }
        int lleaves =0;
        int rleaves=0;
        lleaves += countLeaves(node.left);
        rleaves += countLeaves(node.right);

        return lleaves+rleaves;
        
    }
    private int getKthSmallest(int k, Node n) {
        if (n==null) {
            return -1;  //error conditon
        }
        int lcount = size(n.left);
        //System.out.print("\nnode: "+n.data);
        //System.out.print(": k: "+k);System.out.println(" lcount: "+lcount);
        
        
        if (k==lcount+1) {
           // System.out.print("Found kth  smallest");
            return n.data;
            
        } else if (k>lcount+1) {
           // System.out.print("Moving right");
            k = k - (lcount+1);   //all the left nodes + node we just left
            return getKthSmallest(k,n.right);
            
        } else if (k<lcount+1){
            //still need to find kth smallest in left subtree
            // System.out.print("Moving left");
            return getKthSmallest(k,n.left);
        }
        return -1;

    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int treesize = 5;
        bst.buildTree(treesize);
        //treesize = bst.buildTreeFromOutputString("4 4 4 6 7");
        treesize = bst.buildTreeFromOutputString("3 4 6 3 6");
        //treesize = bst.buildTreeFromOutputString("10");
        for (int i = 0; i < treesize; i++) {
            System.out.println("Searching:" + i + " found:" + bst.lookup(i));
        }
        System.out.println("tree size:" + bst.size());
        System.out.println("maxDepth :" + bst.maxDepth());
        System.out.println("minvalue :" + bst.minValue());
        System.out.println("maxvalue :" + bst.maxValue());
        bst.printTree();
        bst.printPostorder();
        int pathSum = 10;
        System.out.println("hasPathSum " + pathSum + ":" + bst.hasPathSum(pathSum));
        pathSum = 6;
        System.out.println("hasPathSum " + pathSum + ":" + bst.hasPathSum(pathSum));
        pathSum = 19;
        System.out.println("hasPathSum " + pathSum + ":" + bst.hasPathSum(pathSum));
        bst.printPaths();

        bst.printTree();
        bst.mirror();
        System.out.println("Tree after mirror function:");
        bst.printTree();
        //bst.doubleTree();
        System.out.println("Tree after double function:");
        bst.printTree();
       
        System.out.println("tree size:" + bst.size());

        System.out.println("Same tree:" + bst.sameTree(bst));
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.buildTree(treesize);
        treesize = bst2.buildTreeFromOutputString("3 4 6 3 6");
        bst2.printTree();
        System.out.println("Same tree:" + bst.sameTree(bst2));
        int numKeys = 4;
        System.out.println("Number of binary Trees for '" + numKeys + "' keys=" + BinarySearchTree.countTrees(4));
        System.out.println("is Binary search Tree:" + bst.isBST());
        System.out.println("is Binary search Tree:" + bst2.isBST());
        System.out.println("is Binary search Tree 2:" + bst.isBST2());
        System.out.println("is Binary search Tree 2:" + bst2.isBST2());
        System.out.println("---");
        //treesize = bst2.buildTreeFromOutputString("5 4 11 7 2 8 13");
        System.out.println("maxPathSum:" + bst2.maxPathSum());

        treesize = 5;
        bst.buildTree(treesize);
        bst.printTree();
        System.out.println("nthInorder:"+bst.nthInorder(3));

        treesize = bst.buildTreeFromOutputString("6 2 1 4 3 5 7 9 8");
        treesize = bst.buildTreeFromOutputString("8 3 10 1 6 14 4  13");
        bst.printPostorder();
        bst.printTree();
        bst.printLevelOrder();
        System.out.println("Leaves:"+bst.countLeaves());


    }
}
///implementation tips:
/*
 * remember to keep data on left in comparisons like: data<=node.data, this simplifies the recurtion, if true go left else go right
 * in depth function remember special case of empty tree and Only root node Both are depth 0
 * in min/max value functions tree must be non empty or it is an error condition
 * we can optimize haspath sum by checking error as: if root==null in public function itself and in private function check node==null ad sum==0, thus shorten the conditions
 * in double tree if we do recurtion prior to copying, we can use internal stack to make sure only original nodes are parsed
 * in same tree important condition is if (node1==null && node2==null) { then true } else {if (node1==null || node2==null) { false } } Ie. both are not null at same time and only one of them is not null either.
 * ie. to be same both of them must be null at same tiime, only one of them being null or data being different means not same.
 * 
 *
 * In theory, all recursive solutions can be rewritten iteratively. In practice, you end up creating and maintaining a stack frame for a lot of them
 * 
 */
