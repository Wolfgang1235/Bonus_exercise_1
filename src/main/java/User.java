import java.util.Objects;

public class User {
    private String fName, lName, pw, phone, address;

    public User(String fName, String lName, String phone, String address) {
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.address = address;
    }

    public User(String fName, String lName, String pw, String phone, String address) {
        this.fName = fName;
        this.lName = lName;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPw() {
        return pw;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fName, user.fName) && Objects.equals(lName, user.lName) && Objects.equals(pw, user.pw) && Objects.equals(phone, user.phone) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", pw='" + pw + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
