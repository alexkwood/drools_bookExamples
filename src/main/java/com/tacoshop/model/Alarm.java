package com.tacoshop.model;

/**
 * Alarm POJO, data model for our alarm object
 *
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class Alarm {

    /**
     * name of the room where the fire is occurring
     */
    private String room;

    /**
     * constructor initializing with room name
     *
     * @param room where fire is occurring
     */
    public Alarm(String room) {
        this.room = room;
    }

    /**
     * getter for room
     *
     * @return the name of the room where the fire is occurring
     */
    public String getRoom() {
        return room;
    }

    /**
     * setter for room
     *
     * @param room name of the room where the fire is taking place
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * equals method, needed for inference
     *
     * @param object alarm to test equals
     * @return boolean if equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Alarm alarm = (Alarm) object;

        if (room != null ? !room.equals(alarm.room) : alarm.room != null) return false;

        return true;
    }

    /**
     * hashCode method, needed for inference
     *
     * @return int hash code
     */
    @Override
    public int hashCode() {
        return room != null ? room.hashCode() : 0;
    }
}