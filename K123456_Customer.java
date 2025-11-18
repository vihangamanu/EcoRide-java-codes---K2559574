// File: K123456_Customer.java

public class K123456_Customer {
    private String nicOrPassport;
    private String name;
    private String phone;
    private String email;

    public K123456_Customer(String nicOrPassport, String name, String phone, String email) {
        this.nicOrPassport = nicOrPassport;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getNicOrPassport() {
        return nicOrPassport;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NIC/Passport: " + nicOrPassport +
               ", Name: " + name +
               ", Phone: " + phone +
               ", Email: " + email;
    }
}
