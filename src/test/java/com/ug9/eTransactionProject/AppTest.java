package com.ug9.eTransactionProject;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static com.ug9.eTransactionProject.App.printSaldo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    static DigitalPayment angelBRI = new BRImo("Angel", 5000000, "250071190490");
    static DigitalPayment felixBNI = new BNImo("Felix", 2500000, "250071190494");
    static DigitalPayment joyceBRI = new BRImo("Joyce", 1500000, "250071190495");
    static DigitalPayment oliveDana = new Dana("Olive", 0, "081234567891");
    static DigitalPayment adrianOvo = new Ovo("Adrian", 150000, "081578961543");
    static DigitalPayment nolaDana = new Dana("Nola", 550000, "081734566789");

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() {

    }

    @AfterAll
    public static void destory(){
        System.out.println("AKHIRRR");
        printSaldo(angelBRI);
        printSaldo(felixBNI);
        printSaldo(joyceBRI);
        printSaldo(oliveDana);
        printSaldo(adrianOvo);
        printSaldo(nolaDana);
    }
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private static Stream<Arguments> provideInputTidakValidTest() {
        return Stream.of(
                Arguments.of(-150000), // null strings should be considered blank
                Arguments.of(-100000)
        );
    }

    /**
     * Rigorous Test :-)
     */
    @ParameterizedTest
    @MethodSource("provideInputTidakValidTest")
    public void inputTidakValidTest(int input) {
        joyceBRI.transfer(nolaDana, input);
        assertEquals("Nominal yang Anda input tidak valid!",
                outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> provideInputSaldoTidakMencukupiTest() {
        return Stream.of(
                Arguments.of(1555000), // null strings should be considered blank
                Arguments.of(2000000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputSaldoTidakMencukupiTest")
    public void saldoTidakMencukupiTest(int input) {
        nolaDana.transfer(oliveDana, input);
        assertEquals("Transfer gagal! Saldo Anda tidak mencukupi.",
                outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> providetransferSesamaBankTest() {
        return Stream.of(
                Arguments.of(2000000)
        );
    }

    @ParameterizedTest
    @MethodSource("providetransferSesamaBankTest")
    public void transferSesamaBankTest(int input) {
        angelBRI.transfer(joyceBRI, input);
        assertEquals("Transfer ke BRI Mobile atas nama Joyce sebesar Rp " + input + " sukses",
                outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> providetransferBedaBankTest() {
        return Stream.of(
                Arguments.of(angelBRI, felixBNI, 300000, "Transfer ke BNI Mobile atas nama Felix sebesar Rp 300000 sukses"),
                Arguments.of(joyceBRI, angelBRI, 10000, "Transfer ke BRI Mobile atas nama Angel sebesar Rp 10000 sukses")
        );
    }

    @ParameterizedTest
    @MethodSource("providetransferBedaBankTest")
    public void transferBedaBankTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected,
                outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> providetransferEWalletTest() {
        return Stream.of(
                Arguments.of(angelBRI, oliveDana, 1000000, "Transfer ke DANA atas nama Olive sebesar Rp 1000000 sukses")
        );
    }

    @ParameterizedTest
    @MethodSource("providetransferEWalletTest")
    public void transferEWalletTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected,
                outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> provideOvoTransferKeBankTest() {
        return Stream.of(
                Arguments.of(adrianOvo, felixBNI, 100000, "Transfer ke BNI Mobile atas nama Felix sebesar Rp 100000 sukses")
        );
    }

    @ParameterizedTest
    @MethodSource("provideOvoTransferKeBankTest")
    public void ovoTransferKeBankTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> provideDanaTransferKeBankTest() {
        return Stream.of(
                Arguments.of(nolaDana, angelBRI, 100000, "Transfer ke BRI Mobile atas nama Angel sebesar Rp 100000 sukses")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDanaTransferKeBankTest")
    public void danaTransferKeBankTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> provideDanaTransferKeDanaTest() {
        return Stream.of(
                Arguments.of(nolaDana,oliveDana, 150000, "Transfer ke DANA atas nama Olive sebesar Rp 150000 sukses")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDanaTransferKeDanaTest")
    public void danaTransferKeDanaTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> providedanaTransferKeOvoTest() {
        return Stream.of(
                Arguments.of(oliveDana, adrianOvo, 10000, "Akun Dana tidak valid!")
        );
    }

    @ParameterizedTest
    @MethodSource("providedanaTransferKeOvoTest")
    public void danaTransferKeOvoTest(DigitalPayment trfSrc, DigitalPayment trfDest, int input, String expected) {
        trfSrc.transfer(trfDest, input);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
