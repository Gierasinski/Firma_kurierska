package org.example.parcel;

import org.example.PostgreSQL.ManageDataBase;
import org.example.global.Address;
import org.example.global.Branch;
import org.example.maps.OpenStreetMapUtils;
import org.json.simple.parser.JSONParser;

import java.sql.SQLException;
import java.util.List;

public class Route {

    private static Route instance = null;

    public static Route getInstance() {
        if (instance == null) {
            instance = new Route();
        }
        return instance;
    }
    Branch closestToShipper, closestToRecipient;
    double shipperClosestDistance = 0, recipientClosestDistance = 0;
    List<Branch> branches;

    ManageDataBase base =  new  ManageDataBase();
    public void updateBranches() throws SQLException {
        base.connectToDataBase();
        branches = base.getBranches();
    }
    private void getBranches() throws SQLException {
        if(branches==null){
            updateBranches();
        }
    }
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }
    public void calculateRoute(Parcel parcel) throws SQLException {
        base.connectToDataBase();
        getBranches();
        shipperClosestDistance = 0;
        recipientClosestDistance = 0;
        int stage = 0;

        closestToShipper = null;
        closestToRecipient = null;

        Address shipmentAddress = base.getAddressInfo(parcel.getShipment_address());
        Address recipientAddress = base.getAddressInfo(parcel.getDelivery_address());
        for (Branch br : branches) {
            double temp = calculateDistance(shipmentAddress.getLat(),shipmentAddress.getLon(),
                    br.getAddress().getLat(), br.getAddress().getLon(), "K");
            if(temp<shipperClosestDistance || shipperClosestDistance == 0){
                shipperClosestDistance = temp;
                closestToShipper = br;
            }
            temp = calculateDistance(recipientAddress.getLat(),recipientAddress.getLon(),
                    br.getAddress().getLat(), br.getAddress().getLon(), "K");
            if(temp<recipientClosestDistance || recipientClosestDistance == 0){
                recipientClosestDistance = temp;
                closestToRecipient = br;
            }
        }

        base.insertRoutePlan(parcel.getParcelNumber(), parcel.getShipment_address(), closestToShipper.getAddress().getId(),stage);
        stage++;
        if(!closestToShipper.getCode().equals("S01")){
            base.insertRoutePlan(parcel.getParcelNumber(), closestToShipper.getAddress().getId(), 1, stage);
            stage++;
        }

        if(!closestToRecipient.getCode().equals("S01")){
            base.insertRoutePlan(parcel.getParcelNumber(), 1, closestToRecipient.getAddress().getId(),stage);
            stage++;
        }
        base.insertRoutePlan(parcel.getParcelNumber(),closestToRecipient.getAddress().getId(), parcel.getDelivery_address(),stage);
        stage++;

    }
    public void calculateRoute(Parcel parcel, ManageDataBase manage) throws SQLException {
        getBranches();
        shipperClosestDistance = 0;
        recipientClosestDistance = 0;
        int stage = 0;

        closestToShipper = null;
        closestToRecipient = null;

        Address shipmentAddress = manage.getAddressInfo(parcel.getShipment_address());
        Address recipientAddress = manage.getAddressInfo(parcel.getDelivery_address());
        for (Branch br : branches) {
            double temp = calculateDistance(shipmentAddress.getLat(),shipmentAddress.getLon(),
                    br.getAddress().getLat(), br.getAddress().getLon(), "K");
            if(temp<shipperClosestDistance || shipperClosestDistance == 0){
                shipperClosestDistance = temp;
                closestToShipper = br;
            }
            temp = calculateDistance(recipientAddress.getLat(),recipientAddress.getLon(),
                    br.getAddress().getLat(), br.getAddress().getLon(), "K");
            if(temp<recipientClosestDistance || recipientClosestDistance == 0){
                recipientClosestDistance = temp;
                closestToRecipient = br;
            }
        }

        manage.insertRoutePlan(parcel.getParcelNumber(), parcel.getShipment_address(), closestToShipper.getAddress().getId(),stage);
        stage++;
        if(!closestToShipper.getCode().equals("S01")){
            manage.insertRoutePlan(parcel.getParcelNumber(), closestToShipper.getAddress().getId(), 1, stage);
            stage++;
        }

        if(!closestToRecipient.getCode().equals("S01")){
            manage.insertRoutePlan(parcel.getParcelNumber(), 1, closestToRecipient.getAddress().getId(),stage);
            stage++;
        }
        manage.insertRoutePlan(parcel.getParcelNumber(),closestToRecipient.getAddress().getId(), parcel.getDelivery_address(),stage);
        stage++;

    }


}
