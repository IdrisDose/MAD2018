package com.idrisdev.store;

/**
 * Created by Idris on 6/5/2018.
 */

public class Ticket {
    private int mId;
    private String mName;
    private User mAuthor;
    private String mContent; // ?? might need;
    private String mStatus;
    private boolean mHidden;

    /**
     * Basic Constructor for a Ticket Object/Model
     * @param mId int
     * @param name String
     * @param author User
     * @param content String
     * @param status String
     * @param hidden boolean
     */
    public Ticket(int mId, String name, User author, String content, String status, boolean hidden) {
        this.mId = mId;
        this.mName = name;
        this.mAuthor = author;
        this.mContent = content;
        this.mStatus = status;
        this.mHidden = hidden;
    }

    /**
     * Gets the Ticket's ID
     * @return id int
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets the Ticket's Name
     * @return name String
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets the Ticket's Author
     * @return author User
     */
    public User getAuthor() {
        return mAuthor;
    }

    /**
     * Gets the Ticket's Content Body.
     * @return content String
     */
    public String getContent() {
        return mContent;
    }

    /**
     * Gets the ticket's Status
     * @return status String
     */
    public String getStatus() {
        return mStatus;
    }

    /**
     * Gets if the Ticket is hidden
     * @return hidden boolean
     */
    public boolean isHidden() {
        return mHidden;
    }

    /**
     * Sets the Ticket's ID
     * @param id int
     */
    public void setId(int id) {
        this.mId = id;
    }

    /**
     * Sets the Name of the Ticket
     * @param name String
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Sets the Author of the Ticket
     * @param author User
     */
    public void setAuthor(User author) {
        this.mAuthor = author;
    }

    /**
     * Sets the Content of the Ticket
     * @param content String
     */
    public void setContent(String content) {
        this.mContent = content;
    }

    /**
     * Sets the Status of the Ticket
     * @param status String
     */
    public void setStatus(String status) {
        this.mStatus = status;
    }

    /**
     * Sets if the ticket is Hidden
     * @param hidden boolean
     */
    public void setHidden(boolean hidden) {
        this.mHidden = hidden;
    }
}
