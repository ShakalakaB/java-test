package com.awesome.wow.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.awesome.wow.unittest.*")
public class CollaboratorForPartialMockingTest {

    @Test
    public void staticMethod() {
    }

    @Test
    public void finalMethod() {
    }

    @Test
    public void privateMethodCaller() throws Exception {
        CollaboratorForPartialMocking collaborator = new CollaboratorForPartialMocking();
        CollaboratorForPartialMocking mock = spy(collaborator);

        String returnValue = "I am a private mock method.";
        when(mock, "privateMethod").thenReturn(returnValue);
        String result = mock.privateMethodCaller();
        verifyPrivate(mock).invoke("privateMethod");
        assertEquals(returnValue, result);
    }
}