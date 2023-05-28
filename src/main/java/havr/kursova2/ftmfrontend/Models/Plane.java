package havr.kursova2.ftmfrontend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;

public class Plane {
    @JsonIgnore
    private ObjectId id;
    private int num;
    private String typeOfPlane;
    private String numberOfSeats;
    private String specs;

    public Plane(ObjectId id, int num, String typeOfPlane, String numberOfSeats, String specs) {
        this.id = id;
        this.num = num;
        this.typeOfPlane = typeOfPlane;
        this.numberOfSeats = numberOfSeats;
        this.specs = specs;
    }

    public Plane() {
    }

    public ObjectId getId() {
        return id;
    }

    public Plane setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public int getNum() {
        return num;
    }

    public Plane setNum(int num) {
        this.num = num;
        return this;
    }

    public String getTypeOfPlane() {
        return typeOfPlane;
    }

    public Plane setTypeOfPlane(String typeOfPlane) {
        this.typeOfPlane = typeOfPlane;
        return this;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public Plane setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public String getSpecs() {
        return specs;
    }

    public Plane setSpecs(String specs) {
        this.specs = specs;
        return this;
    }
}
