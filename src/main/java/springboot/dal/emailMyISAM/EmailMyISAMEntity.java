package springboot.dal.emailMyISAM;

public class EmailMyISAMEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column email_myisam.email_id
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    private Integer email_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column email_myisam.email_address
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    private String email_address;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column email_myisam.email_id
     *
     * @return the value of email_myisam.email_id
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    public Integer getEmail_id() {
        return email_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column email_myisam.email_id
     *
     * @param email_id the value for email_myisam.email_id
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    public void setEmail_id(Integer email_id) {
        this.email_id = email_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column email_myisam.email_address
     *
     * @return the value of email_myisam.email_address
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    public String getEmail_address() {
        return email_address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column email_myisam.email_address
     *
     * @param email_address the value for email_myisam.email_address
     *
     * @mbg.generated Thu Apr 26 00:10:47 CST 2018
     */
    public void setEmail_address(String email_address) {
        this.email_address = email_address == null ? null : email_address.trim();
    }
}