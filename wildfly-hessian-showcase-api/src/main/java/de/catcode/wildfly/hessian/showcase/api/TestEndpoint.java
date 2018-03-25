package de.catcode.wildfly.hessian.showcase.api;

import de.catcode.wildfly.hessian.showcase.api.to.ComplexTo;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Endpoint interfaces to be shared to consumers.
 *
 * @author namtar on 25.03.2018.
 */
public interface TestEndpoint {

    String TEST_STRING = "Ein TestString";

    String setAndGetString(String value);

    Integer setAndGetInteger(Integer value);

    Long setAndGetLong(Long value);

    Float setAndGetFloat(Float value);

    Double setAndGetDouble(Double value);

    LocalDate setAndGetLocalDate(LocalDate value);

    Boolean setAndGetBoolean(Boolean value);

    byte[] setAndGetByteArray(byte[] value);

    int setInputStream(InputStream is);

    InputStream getInputStream();

    LocalDateTime setAndGetLocalDateTime(LocalDateTime value);

    ComplexTo setAndGetComplexTo(ComplexTo value);
}
