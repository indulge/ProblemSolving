package sg.practice.algoexpert.graph;

import java.util.HashMap;
import java.util.Map;

public class YoungestCommonAncestor {
    class Template {
        class Program {
            public AncestralTree getYoungestCommonAncestor(
                    AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
                // Write your code here.
                return null;
            }

            class AncestralTree {
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
    }
    class Solution1 {
        class Program {
            public AncestralTree getYoungestCommonAncestor(
                    AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
                Map<AncestralTree, Integer> descendantPath = new HashMap<>();
                descendantPath.put(descendantOne, 1);
                AncestralTree ancestor = descendantOne.ancestor;
                while (ancestor != null) {
                    descendantPath.put(ancestor, 1);
                    ancestor = ancestor.ancestor;
                }

                ancestor = descendantTwo;
                while (ancestor != null) {
                    if (descendantPath.get(ancestor) != null && descendantPath.get(ancestor) == 1) return ancestor;
                    ancestor = ancestor.ancestor;
                }
                return null;
            }

            class AncestralTree {
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
    }

    class SolutionGiven {
        class Program {
            public AncestralTree getYoungestCommonAncestor(
                    AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
               int depth1 = findDepth(descendantOne);
                int depth2 = findDepth(descendantTwo);
                if (depth1 > depth2) {
                    return findCommon(descendantOne, descendantTwo, depth1-depth2);
                } else {
                    return findCommon(descendantTwo, descendantOne, depth2-depth1);
                }
            }

            private AncestralTree findCommon(AncestralTree longer, AncestralTree shorter, int diff) {
                while (diff > 0) {
                    longer = longer.ancestor;
                    diff--;
                }
                while (longer != shorter) {
                    longer = longer.ancestor;
                    shorter = shorter.ancestor;
                }
                return longer;
            }

            private int findDepth(AncestralTree tree) {
                int depth = 0;
                while (tree != null) {
                    depth = 1;
                    tree = tree.ancestor;
                }
                return depth;
            }
            class AncestralTree {
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
    }

}
