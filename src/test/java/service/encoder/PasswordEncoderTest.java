package service.encoder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PasswordEncoderTest {
    private static final String WEAK_PASSWORD = "qwerty";
    private static final String PASS_WITH_DIGITS = "psdTg34kl";
    private static final String LONG_PASSWORD = "VdfgkjUkjdfdflK34jdfdlek5jSLkjdfs2Llkd5fID32OIJFjdfsle383";
    private static final String STRONG_PASSWORD = "$#%(*Dsdf2FHSk44ljh1sdf(*E&$%HsdfKL342JDFSkjdhsfiuyewr32";

    private PasswordEncoder encoder;


    @Before
    public void init() {
        encoder = new PasswordEncoder();
    }

    @Test
    public void shouldCorrectEncodeWeakPassword() {
        String actual = encoder.encode(WEAK_PASSWORD);
        boolean isMatched = encoder.verify(WEAK_PASSWORD, actual);
        assertTrue(isMatched);
    }

    @Test
    public void shouldCorrectEncodePasswordWithDigits() {
        String actual = encoder.encode(PASS_WITH_DIGITS);
        boolean isMatched = encoder.verify(PASS_WITH_DIGITS, actual);
        assertTrue(isMatched);
    }

    @Test
    public void shouldCorrectEncodeLongPassword() {
        String actual = encoder.encode(LONG_PASSWORD);
        boolean isMatched = encoder.verify(LONG_PASSWORD, actual);
        assertTrue(isMatched);
    }

    @Test
    public void shouldCorrectEncodeStrongPassword() {
        String actual = encoder.encode(STRONG_PASSWORD);
        boolean isMatched = encoder.verify(STRONG_PASSWORD, actual);
        assertTrue(isMatched);
    }

}
