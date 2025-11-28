package org.simplecash.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CompteCourant extends Compte {

    private double decouvert = 1000.0;
}
