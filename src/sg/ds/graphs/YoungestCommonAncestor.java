package sg.ds.graphs;

import java.util.HashMap;
import java.util.Map;

public class YoungestCommonAncestor {
    public static void main(String[] args) {
        SimpleGraph test = SimpleGraph.buildSample1();
        test.bfs(1);
    }

}


class Program {
    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor,
            AncestralTree descendantOne,
            AncestralTree descendantTwo) {
        Map<Character, Boolean> ancestors = new HashMap<>();
        AncestralTree ancestor = descendantOne;
        while (ancestor != topAncestor) {
            ancestors.put(ancestor.name, true);
            ancestor = ancestor.ancestor;
        }
        ancestor = descendantTwo;
        while (ancestor != topAncestor) {
            if (ancestors.get(ancestor)) return ancestor;
            ancestor = ancestor.ancestor;
        }
        return null;
    }



    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }
}
