package riverway.domain.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Embeddable
public class Shipping {

    @Column(nullable = false)
    private String recipient;

    @Size(min = 10)
    @Column(nullable = false)
    private String address;

    @Size(min = 10)
    @Column(nullable = false)
    private String phoneNumber;

    @Lob
    @Column
    private String specialNote;

    public Shipping() {
    }

    public String getRecipient() {
        return recipient;
    }

    public Shipping setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shipping setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Shipping setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public Shipping setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
        return this;
    }
}
