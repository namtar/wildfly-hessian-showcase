package de.catcode.wildfly.hessian.showcase;

import com.caucho.hessian.client.HessianProxyFactory;
import de.catcode.wildfly.hessian.showcase.api.TestEndpoint;
import de.catcode.wildfly.hessian.showcase.api.to.ComplexTo;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Test to call the TestEndpoint via Hessian.
 *
 * @author namtar on 25.03.2018.
 */
public class HessianTestEndpointTest {

    private static final Logger log = LoggerFactory.getLogger(HessianTestEndpointTest.class);

    private TestEndpoint testEndpoint;

    @Before
    public void before() throws MalformedURLException {

        final String url = "http://localhost:8080/hessian-showcase/test";
        final HessianProxyFactory proxyFactory = new HessianProxyFactory();
        testEndpoint = (TestEndpoint) proxyFactory.create(TestEndpoint.class, url);
    }

    @Test
    public void testSetAndGetString() {

        final String value = "Ein TestString";
        final String result = testEndpoint.setAndGetString(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Test
    public void testSetAndGetInteger() {
        final Integer value = 123;
        final Integer result = testEndpoint.setAndGetInteger(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Test
    public void testSetAndGetLong() {
        final Long value = 123L;
        final Long result = testEndpoint.setAndGetLong(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Test
    public void testSetAndGetFloat() {
        final Float value = 123.123F;
        final Float result = testEndpoint.setAndGetFloat(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Test
    public void testSetAndGetDouble() {
        final Double value = 123.123D;
        final Double result = testEndpoint.setAndGetDouble(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Ignore("Ignored because of StackOverflowError on Java 8 Time Datatypes")
    @Test
    public void testSetAndGetLocalDate() {
        final LocalDate value = LocalDate.now();
        final LocalDate result = testEndpoint.setAndGetLocalDate(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Ignore("Ignored because of StackOverflowError on Java 8 Time Datatypes")
    @Test
    public void testSetAndGetLocalDateTime() {
        final LocalDateTime value = LocalDateTime.now();
        final LocalDateTime result = testEndpoint.setAndGetLocalDateTime(value);
        Assert.assertNotNull(result);
        Assert.assertEquals(value, result);
    }

    @Test
    public void testSetAndGetBoolean() {
        final Boolean value = Boolean.TRUE;
        final Boolean result = testEndpoint.setAndGetBoolean(value);
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    public void testSetAndGetByteArray() {
        final String valueString = "Test String für ByteArray";
        final byte[] value = valueString.getBytes();
        final byte[] result = testEndpoint.setAndGetByteArray(value);
        Assert.assertNotNull(result);
        final String resultString = new String(result);
        Assert.assertEquals(valueString, resultString);
    }

    @Test
    public void testSetInputStream() throws IOException {

        final String valueForInputStream = "Test String für InputStream";
        final InputStream is = new ByteArrayInputStream(valueForInputStream.getBytes());
        final int numberOfBytes = valueForInputStream.length();
        log.info("NumberOfBytes {}", numberOfBytes);
        final int resultNumberOfBytes = testEndpoint.setInputStream(is);
        log.info("NumberOfBytesResult {}", resultNumberOfBytes);
        Assert.assertTrue(numberOfBytes == resultNumberOfBytes);
    }

    @Test
    public void testGetInputStream() throws IOException {

        final InputStream is = testEndpoint.getInputStream();
        Assert.assertNotNull(is);
        final String result = IOUtils.toString(is, StandardCharsets.UTF_8);
        Assert.assertNotNull(result);
        log.info(result);
        Assert.assertEquals(TestEndpoint.TEST_STRING, result);
    }

    @Ignore("Ignored because of StackOverflowError on Java 8 Time Datatypes")
    @Test
    public void testSetAndGetComplexTo() {
        final ComplexTo complexTo = createComplexTo();
        final ComplexTo result = testEndpoint.setAndGetComplexTo(complexTo);
        Assert.assertNotNull(result);
        Assert.assertEquals(complexTo, result);
    }

    private ComplexTo createComplexTo() {

        final ComplexTo complexTo = new ComplexTo();
        complexTo.integerVal = 123;
        complexTo.localDateTimeVal = LocalDateTime.now();
        complexTo.localDateVal = LocalDate.now();
        complexTo.primitiveIntVal = 234;
        complexTo.stringVal = TestEndpoint.TEST_STRING;
        complexTo.selfContained = new ComplexTo();
        complexTo.selfContained.integerVal = 345;
        complexTo.selfContained.primitiveIntVal = 456;
        complexTo.selfContained.stringVal = "Ein anderer Teststring";
        complexTo.selfContained.localDateVal = LocalDate.of(2018, 2, 1);
        complexTo.selfContained.localDateTimeVal = LocalDateTime.of(2018, 3, 2, 5, 7, 0);

        return complexTo;
    }
}
