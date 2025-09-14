package lecture4;


public class Person {
    // Encapsulation: keep fields private
    private String name;
    private String cnic; // national ID, kept private

    // Public getters/setters to control access
    public String getName() { return name; }
    public void setName(String name) {
        // simple validation example
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public String getCnic() { return cnic; }
    public void setCnic(String cnic) {
        // keep it minimal—just demonstrate the idea
        this.cnic = cnic;
    }
}