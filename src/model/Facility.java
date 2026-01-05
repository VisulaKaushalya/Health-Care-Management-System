package model;

public class Facility {
    private String facilityID;
    private String name;
    private String type;
    private String address;
    private String postcode;
    private String phone;
    private String email;
    private String openingHours;
    private String managerName;
    private String capacity;
    private String specialities;

    public Facility(String facilityID, String name, String type, String address,
                    String postcode, String phone, String email, String openingHours,
                    String managerName, String capacity, String specialities) {
        this.facilityID = facilityID;
        this.name = name;
        this.type = type;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialities = specialities;
    }

    // Getters
    public String getFacilityID() { return facilityID; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getOpeningHours() { return openingHours; }
    public String getManagerName() { return managerName; }
    public String getCapacity() { return capacity; }
    public String getSpecialities() { return specialities; }



    // toString for Dropdowns
    @Override
    public String toString() {
        return facilityID + " - " + name;
    }
}