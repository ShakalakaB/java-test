package com.awesome.wow;

import com.awesome.wow.dto.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TestTest {
    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "studentA", Student.Gender.FEMALE, 3);
    }

    @Test
    void serializationTest() throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("./serialization.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(student);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    @Test
    void unserializeTest() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("./serialization.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Student unserializedStudent = (Student) objectInputStream.readObject();
        objectInputStream.close();

        assertEquals(student.getId(), unserializedStudent.getId());
    }

    @ParameterizedTest
    @DisplayName("parameter test")
    @ValueSource(strings = {"radar", "car", "door"})
    void parameterizedTest(String string) {
        assertTrue(string.startsWith("r"));
    }
}