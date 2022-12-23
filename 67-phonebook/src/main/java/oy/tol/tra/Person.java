package oy.tol.tra;

public class Person implements Comparable<Person>{
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public int compareTo(Person o) {
        int hash = this.hashCode();
        int oHash = o.hashCode();
        if(hash == oHash){
            return 0;
        }
        else if(hash > oHash){
            return 1;
        }
        return -1;
    }

    public boolean equals(Object input){
        if(input instanceof Person){
            return this.getFullName().equals(((Person)input).getFullName());
        }
        return false;
    }

    public int hashCode() {
        int hash = 0;
        String fullName = this.getFullName();
        for(int n = 0; n < fullName.length(); n++){
            hash = fullName.charAt(n) + (hash << 6) + (hash << 16) - hash;
        }
        return hash;
    }

    
}
