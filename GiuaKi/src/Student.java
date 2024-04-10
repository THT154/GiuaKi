import java.time.LocalDate;
import java.time.Period;



public class Student {
    private String ID;
    private String name;
    private String address;
    private LocalDate dateOfBirth;
    
    public Student() {
    	
    }

    public Student(String ID, String name, String address, LocalDate dateOfBirth) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

	@Override
	public String toString() {
		return "Student [ID=" + ID + ", name=" + name + ", address=" + address + ", dateOfBirth=" + dateOfBirth + "]";
	}
    
}	

   