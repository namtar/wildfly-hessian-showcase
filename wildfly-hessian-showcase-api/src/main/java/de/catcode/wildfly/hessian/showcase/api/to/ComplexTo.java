package de.catcode.wildfly.hessian.showcase.api.to;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A complex transport object.
 *
 * @author namtar on 25.03.2018.
 */
public class ComplexTo implements Serializable {

    public String stringVal;
    public Integer integerVal;
    public int primitiveIntVal;

    public LocalDate localDateVal;
    public LocalDateTime localDateTimeVal;

    public ComplexTo selfContained;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
