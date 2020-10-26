package sg.rec;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonAncestor {

    public static void main(String[] args) {

    }

    static class Program {
        public static OrgChart getLowestCommonManager(
                OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
            System.out.println("\n~~Input:\n"+topManager);
            System.out.println("reportOne: "+reportOne.name);
            System.out.println("reportTwo: "+reportTwo.name);
            System.out.println("~~\n");
            List<OrgChart> path1 = new ArrayList<>();
            List<OrgChart> path2 = new ArrayList<>();
//            findEmployees(topManager, topManager, reportOne, path1);
//            findEmployees(topManager, topManager, reportTwo, path2);
//            Set<OrgChart> result = new HashSet<>();
//            solutionFindEmployees(topManager, reportOne, reportTwo, result);
//            return (OrgChart)result.toArray()[0];
            return solutionFindEmployees(topManager, reportOne, reportTwo).emp;
//            System.out.println("\n~~~Output:");
//            System.out.println("Path1: "+path1);
//            System.out.println("~~~");
//            System.out.println("Path2: "+path2);
//            System.out.println("~~~\n");
//            for (OrgChart emp1:path1) {
//                for (OrgChart emp2:path2) {
//                    if (emp1 == emp2);
//                     return emp1;
//                }
//            }
//            return null;
        }

        private static Pair solutionFindEmployees
                (OrgChart current, OrgChart emp1, OrgChart emp2) {
            int countAtCurrent = 0;
            if (current == emp1 || current == emp2) countAtCurrent++;
            for (OrgChart emp: current.directReports) {
                Pair result = solutionFindEmployees(emp, emp1, emp2);
                countAtCurrent += result.num;

                if (result.num >= 2) return result;

                if (countAtCurrent >= 2) {
                    System.out.println("Lowest Common Manager: " + current);
                    return new Pair(countAtCurrent, current);
                }
            }
            return new Pair(countAtCurrent, current);
        }

        private static Pair solutionFindEmployees
                (OrgChart current, OrgChart emp1, OrgChart emp2, Set<OrgChart> lcm) {
            int countAtCurrent = 0;
            if (current == emp1 || current == emp2) countAtCurrent++;
            for (OrgChart emp: current.directReports) {
                Pair result = solutionFindEmployees(emp, emp1, emp2, lcm);
                countAtCurrent += result.num;

                if (countAtCurrent >= 2 && lcm.size() == 0) {
                    lcm.add(current);
                    System.out.println("Lowest Common Manager: " + current);
                    return result;
                }
            }
            return new Pair(countAtCurrent, current);
        }
        private static class Pair {
            Integer num;
            OrgChart emp;

            public Pair(Integer num, OrgChart emp) {
                this.num = num;
                this.emp = emp;
            }
        }
        private static OrgChart findEmployees(OrgChart mgr, OrgChart current, OrgChart emp, List<OrgChart> path) {
            if (mgr == null) return null;
            if (current == null) return null;
            if (current == emp)  return mgr;

            for (OrgChart next : mgr.directReports) {
                OrgChart manager = findEmployees(current, next, emp, path);
                if (manager != null) {
                    path.add(manager);
                    return mgr;
                }
            }
            return null;
        }

        static class OrgChart {
            public char name;
            public List<OrgChart> directReports;

            OrgChart(char name) {
                this.name = name;
                this.directReports = new ArrayList<OrgChart>();
            }

            @Override
            public String toString() {
                return "OrgChart{" +
                        "name=" + name +
                        ", directReports=" + directReports +
                        '}';
            }

            // This method is for testing only.
            public void addDirectReports(OrgChart[] directReports) {
                for (OrgChart directReport : directReports) {
                    this.directReports.add(directReport);
                }
            }
        }
    }
}
