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
    private String adress;

    @Size(min = 10)
    @Column(nullable = false)
    private String phoneNumber;

    @Lob
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

    public String getAdress() {
        return adress;
    }

    public Shipping setAdress(String adress) {
        this.adress = adress;
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
