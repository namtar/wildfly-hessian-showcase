package de.catcode.wildfly.hessian.showcase;

import com.caucho.hessian.server.HessianServlet;
import de.catcode.wildfly.hessian.showcase.api.TestEndpoint;
import de.catcode.wildfly.hessian.showcase.api.to.ComplexTo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Implementation of {@link TestEndpoint}.
 *
 * @author namtar on 25.03.2018.
 */
@WebServlet("/test")
public class TestEndpointImpl extends HessianServlet implements TestEndpoint {

    private static final Logger log = LoggerFactory.getLogger(TestEndpointImpl.class);

    @Override
    public String setAndGetString(String value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public Integer setAndGetInteger(Integer value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public Long setAndGetLong(Long value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public Float setAndGetFloat(Float value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public Double setAndGetDouble(Double value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public LocalDate setAndGetLocalDate(LocalDate value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public Boolean setAndGetBoolean(Boolean value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public byte[] setAndGetByteArray(byte[] value) {
        log.info("Got Value Number of Bytes {}", value.length);
        return value;
    }

    @Override
    public int setInputStream(InputStream is) {
        try {
            // is available gibt 0 zurück und ist daher an dieser Stelle nicht brauchbar.
//            int available = is.available();
            String stringFromStream = IOUtils.toString(is, StandardCharsets.UTF_8);
            log.info("StringFromSteam: {}", stringFromStream);
            log.info("Got InputStream with available bytes: {}", stringFromStream.length());
            return stringFromStream.length();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // closing of inputStream is essential in the use with hessian
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public InputStream getInputStream() {
        log.info("Number of bytes {}", TEST_STRING.getBytes().length);
        return new ByteArrayInputStream(TEST_STRING.getBytes());
    }

    @Override
    public LocalDateTime setAndGetLocalDateTime(LocalDateTime value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }

    @Override
    public ComplexTo setAndGetComplexTo(ComplexTo value) {
        log.info("Got Value {} of type {}", value, value.getClass().getName());
        return value;
    }
}
