package org.example.PT;

import org.example.PostgreSQL.ManageDataBase;

import java.sql.SQLException;

public class Route {
    int distanceAB ,distanceAC, distanceBC;
    boolean aBranch=false, bBranch=false, cBranch=false;
    ManageDataBase base =  new  ManageDataBase();


    public void setDistance(int a, int b)  {

            try {
                base.connectToDataBase();
                for (int i = 1; i < 4; i++) {
                    base.insertDistance(a, i);
                }
                for (int i = 1; i < 4; i++) {
                    base.insertDistance(b, i);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    public void setDistanceABC(){
    try{
        base.connectToDataBase();
        distanceAB = base.getDistance(1, 2);
        distanceAC = base.getDistance(1, 3);
        distanceBC = base.getDistance(2, 3);

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    public void calculateRoute(int idAddressA,int  idAddressB) {
        setDistanceABC();
        setDistance(idAddressA, idAddressB);
        int distance;
        int min = 1000;
        int minA = 1000;
        int branch = 0;

        try {
            base.connectToDataBase();
            for (int i = 1; i < 4; i++) {
                distance = base.getDistance(idAddressA, i);
                if (distance <= minA) {
                    minA = distance;
                    branch = i;
                }
            }
            distance = base.getDistance(idAddressB, branch);
            min = minA + distance;
            if (branch == 1) {
                aBranch = true;
            } else if (branch == 2) {
                bBranch = true;
            } else if (branch == 3) {
                cBranch = true;
            }

            if (branch == 1) {
                distance = base.getDistance(idAddressB, 2);
                if (distance + distanceAB + minA < min) {
                    min = distance + distanceAB + minA;
                    bBranch = true;
                }
                distance = base.getDistance(idAddressB, 3);
                if (distance + distanceAC + minA < min) {
                    min = distance + distanceAC + minA;
                    cBranch = true;
                }
            }
            if (branch == 2) {
                distance = base.getDistance(idAddressB, 1);
                if (distance + distanceAB + minA < min) {
                    min = distance + distanceAB + minA;
                    aBranch = true;
                }
                distance = base.getDistance(idAddressB, 3);
                if (distance + distanceBC + minA < min) {
                    min = distance + distanceBC + minA;
                    cBranch = true;
                }
            }
            if (branch == 3) {
                distance = base.getDistance(idAddressB, 1);
                if (distance + distanceAC + minA < min) {
                    min = distance + distanceAC + minA;
                    aBranch = true;
                }
                distance = base.getDistance(idAddressB, 2);
                if (distance + distanceBC + minA < min) {
                    min = distance + distanceBC + minA;
                    bBranch = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println(min);
    }

}
