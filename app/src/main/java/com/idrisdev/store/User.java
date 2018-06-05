package com.idrisdev.store;

/**
 * Created by Idris on 6/5/2018.
 */

public class User {
    private int mId;
    private String mName;
    private String mEmail;
    private boolean mActive;

    /**
     * Basic Constructor for the User Object/Model
     * @param id int - ID of the User
     * @param name String - Name of the User
     * @param email String - Email of the User
     */
    public User(int id, String name, String email) {
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
    }

    /**
     * Gets the User's ID
     * @return String - This User's ID
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets the User's Name
     * @return String - This User's Name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets this User's Email
     * @return String - This User's Email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Used to check if the User is Active
     * @return boolen This User's active status
     */
    public boolean isActive() {
        return mActive;
    }

    /**
     * Sets the User's ID
     * @param id int
     */
    public void setId(int id) {
        this.mId = id;
    }

    /**
     * Sets the User's Name
     * @param name String
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Sets the User's Email
     * @param email String
     */
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     * Sets the User's Active Status
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.mActive = active;
    }
}
