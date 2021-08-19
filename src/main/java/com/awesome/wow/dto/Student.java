package com.awesome.wow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = -8014595503790067711L;
//    private static final long serialVersionUID = -3476594292995559968L;

    public enum Gender {
        MALE, FEMALE;
    }

    private Long id;

    private String name;

    private Gender gender;

    private int grade;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                '}';
    }
}
