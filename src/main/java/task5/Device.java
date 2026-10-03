package task5;

import java.util.ArrayList;
import java.util.List;

public class Device {

    private String name;
    private String origin;
    private double price;

    private boolean peripheral;
    private int powerConsumption;
    private boolean cooler;
    private String group;
    private List<String> ports;

    private boolean critical;


    public Device() {
        ports = new ArrayList<>();
    }


    public Device(String name,
                  String origin,
                  double price,
                  boolean peripheral,
                  int powerConsumption,
                  boolean cooler,
                  String group,
                  List<String> ports,
                  boolean critical) {

        this.name = name;
        this.origin = origin;
        this.price = price;
        this.peripheral = peripheral;
        this.powerConsumption = powerConsumption;
        this.cooler = cooler;
        this.group = group;
        this.ports = ports;
        this.critical = critical;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }


    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }


    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }


    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }


    @Override
    public String toString() {

        return "Device{" +
                "name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", peripheral=" + peripheral +
                ", powerConsumption=" + powerConsumption +
                ", cooler=" + cooler +
                ", group='" + group + '\'' +
                ", ports=" + ports +
                ", critical=" + critical +
                '}';
    }
}
