package com.example.Memo.DTOJoins;

public class CustomerDTO {
    private Integer Id;
    private String client_no;
	private String fname;
	private String lname;
	private String address;
	private String customer_name;
	private String mobile_no;
	private String whatsapp_no;
	private String email;
	private String gstinNo;
    
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getClient_no() {
        return client_no;
    }
    public void setClient_no(String client_no) {
        this.client_no = client_no;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getMobile_no() {
        return mobile_no;
    }
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
    public String getWhatsapp_no() {
        return whatsapp_no;
    }
    public void setWhatsapp_no(String whatsapp_no) {
        this.whatsapp_no = whatsapp_no;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGstinNo() {
        return gstinNo;
    }
    public void setGstinNo(String gstinNo) {
        this.gstinNo = gstinNo;
    }


    

}
