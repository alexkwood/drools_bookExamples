package com.tacoshop.model;

/**
 * Fire POJO, data model for our fire object
 *
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
public class Fire {

    /**
     * name of the room where the fire is occurring
     */
    private String room;

    /**
     * constructor initializing with room name
     *
     * @param room where fire is occurring
     */
    public Fire(String room) {
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
}