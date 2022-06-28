package io.adarsh.springdatajpaexp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ACCESS_CARD")
public class AccessCard {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Temporal(TemporalType.TIMESTAMP)
    Date issuedDate;

    Boolean isActive;

    String firmwareVersion;

    @OneToOne
    @JsonManagedReference
    Employee owner;

    @ManyToOne
    @JsonBackReference
    Employee founder;

    @Override
    public String toString() {
        return "AccessCard{" +
                "id=" + id +
                ", issuedDate=" + issuedDate +
                ", isActive=" + isActive +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }
}
