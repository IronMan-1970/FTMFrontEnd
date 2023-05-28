package havr.kursova2.ftmfrontend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;

public class Flight {
    @JsonIgnore
    private ObjectId id;
    private int num;
    private String passage;
    private String start;
    private String finish;
    private String dataAndTime;
    private String timeOfFlight;
    private String planeType;
    private String placeClass;

    public Flight(ObjectId id, int num, String passage, String start, String finish, String dataAndTime, String timeOfFlight, String planeType, String placeClass) {
        this.id = id;
        this.num = num;
        this.passage = passage;
        this.start = start;
        this.finish = finish;
        this.dataAndTime = dataAndTime;
        this.timeOfFlight = timeOfFlight;
        this.planeType = planeType;
        this.placeClass = placeClass;
    }

    public int getNum() {
        return num;
    }

    public Flight setNum(int num) {
        this.num = num;
        return this;
    }

    public ObjectId getId() {
        return id;
    }

    public Flight setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getPassage() {
        return passage;
    }

    public Flight setPassage(String passage) {
        this.passage = passage;
        return this;
    }

    public String getStart() {
        return start;
    }

    public Flight setStart(String start) {
        this.start = start;
        return this;
    }

    public String getFinish() {
        return finish;
    }

    public Flight setFinish(String finish) {
        this.finish = finish;
        return this;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }

    public Flight setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
        return this;
    }

    public String getTimeOfFlight() {
        return timeOfFlight;
    }

    public Flight setTimeOfFlight(String timeOfFlight) {
        this.timeOfFlight = timeOfFlight;
        return this;
    }

    public String getPlaneType() {
        return planeType;
    }

    public Flight setPlaneType(String planeType) {
        this.planeType = planeType;
        return this;
    }

    public String getPlaceClass() {
        return placeClass;
    }

    public Flight setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
        return this;
    }

    public Flight() {
    }
}
