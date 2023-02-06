package com.app.crud.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    @Getter
    @Setter
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    @Getter
    @Setter
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    @Getter
    @Setter
    @NotEmpty(message = "El email es obligatorio")
    @Email
    private String email;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
    }
}
