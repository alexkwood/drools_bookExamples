package com.tacoshop.model;

/**
 * Sprinkler POJO, data model for our sprinkler object
 *
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class Sprinkler {

    /**
     * name of the room where the fire is occurring
     */
    private String room;

    /**
     * boolean representing if sprinkler is currently on or not
     */
    private boolean on = false;

    /**
     * constructor initializing with room name
     *
     * @param room where fire is occurring
     */
    public Sprinkler(String room) {
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
     * check if siren is turned on or not
     *
     * @return true/false if sprinkler is on
     */
    public boolean isOn() {
        return on;
    }

    /**
     * set running state of sprinkler
     *
     * @param on state to set
     */
    public void setOn(boolean on) {
        this.on = on;
    }
}