package defeaut_package;
import java.math.BigInteger;

public class Person {
	
	private String firstName;
    private String lastName;
    private int age;
    private long ssn;
    private long creditCard;


	public Person(String firstName, String lastName, int age, long ssn, long creditCard) {
	        
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.age = age;
	        this.ssn = ssn;
	        this.creditCard = creditCard;
	    }


    public Person() {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getSsn() == person.getSsn();

    }

    @Override
    public int hashCode() {
        return (int) (getSsn() ^ (getSsn() >>> 32));
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", ssn=" + ssn +
                ", creditCard=" + creditCard +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }

}