/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vladimir
 */
public class ModeloPersonajeTest {

    public ModeloPersonajeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of MoverPersonaje method, of class ModeloPersonaje.
     */
    @Ignore
    public void testMoverPersonaje() {
        System.out.println("MoverPersonaje");
        int accion = 0;
        ModeloPersonaje instance = null;
        Movimiento expResult = null;
        Movimiento result = instance.MoverPersonaje(accion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MoverConReturn method, of class ModeloPersonaje.
     */
    @Test
    public void testMoverConReturn() {
        System.out.println("MoverConReturn");
        char[][] Mapa = new char[5][5];

        for(int i=0;i<5;

        }
        Movimiento m = null;
        ModeloPersonaje instance = null;
        instance.MoverConReturn(Mapa, m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccion method, of class ModeloPersonaje.
     */
    @Ignore
        public void testGetAccion() {
        System.out.println("getAccion");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getAccion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccion method, of class ModeloPersonaje.
     */
    @Ignore
    public void testSetAccion() {
        System.out.println("setAccion");
        int Accion = 0;
        ModeloPersonaje instance = null;
        instance.setAccion(Accion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFactordeMovimiento method, of class ModeloPersonaje.
     */
    @Ignore
    public void testGetFactordeMovimiento() {
        System.out.println("getFactordeMovimiento");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getFactordeMovimiento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFactordeMovimiento method, of class ModeloPersonaje.
     */
    @Ignore
    public void testSetFactordeMovimiento() {
        System.out.println("setFactordeMovimiento");
        int FactordeMovimiento = 0;
        ModeloPersonaje instance = null;
        instance.setFactordeMovimiento(FactordeMovimiento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosicionRelativaX method, of class ModeloPersonaje.
     */
    @Ignore
    public void testGetPosicionRelativaX() {
        System.out.println("getPosicionRelativaX");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getPosicionRelativaX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosicionRelativaX method, of class ModeloPersonaje.
     */
    @Ignore
    public void testSetPosicionRelativaX() {
        System.out.println("setPosicionRelativaX");
        int PosicionRelativaX = 0;
        ModeloPersonaje instance = null;
        instance.setPosicionRelativaX(PosicionRelativaX);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosicionRelativaY method, of class ModeloPersonaje.
     */
    @Test
    public void testGetPosicionRelativaY() {
        System.out.println("getPosicionRelativaY");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getPosicionRelativaY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosicionRelativaY method, of class ModeloPersonaje.
     */
    @Test
    public void testSetPosicionRelativaY() {
        System.out.println("setPosicionRelativaY");
        int PosicionRelativaY = 0;
        ModeloPersonaje instance = null;
        instance.setPosicionRelativaY(PosicionRelativaY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosicionX method, of class ModeloPersonaje.
     */
    @Test
    public void testGetPosicionX() {
        System.out.println("getPosicionX");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getPosicionX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosicionX method, of class ModeloPersonaje.
     */
    @Test
    public void testSetPosicionX() {
        System.out.println("setPosicionX");
        int PosicionX = 0;
        ModeloPersonaje instance = null;
        instance.setPosicionX(PosicionX);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosicionY method, of class ModeloPersonaje.
     */
    @Test
    public void testGetPosicionY() {
        System.out.println("getPosicionY");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getPosicionY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosicionY method, of class ModeloPersonaje.
     */
    @Test
    public void testSetPosicionY() {
        System.out.println("setPosicionY");
        int PosicionY = 0;
        ModeloPersonaje instance = null;
        instance.setPosicionY(PosicionY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMuerto method, of class ModeloPersonaje.
     */
    @Test
    public void testIsMuerto() {
        System.out.println("isMuerto");
        ModeloPersonaje instance = null;
        boolean expResult = false;
        boolean result = instance.isMuerto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TodasLasJoyas method, of class ModeloPersonaje.
     */
    @Test
    public void testTodasLasJoyas() {
        System.out.println("TodasLasJoyas");
        ModeloPersonaje instance = null;
        boolean expResult = false;
        boolean result = instance.TodasLasJoyas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBordeCriticoX method, of class ModeloPersonaje.
     */
    @Test
    public void testIsBordeCriticoX() {
        System.out.println("isBordeCriticoX");
        ModeloPersonaje instance = null;
        boolean expResult = false;
        boolean result = instance.isBordeCriticoX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBordeCriticoY method, of class ModeloPersonaje.
     */
    @Test
    public void testIsBordeCriticoY() {
        System.out.println("isBordeCriticoY");
        ModeloPersonaje instance = null;
        boolean expResult = false;
        boolean result = instance.isBordeCriticoY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AumentarVida method, of class ModeloPersonaje.
     */
    @Test
    public void testAumentarVida() {
        System.out.println("AumentarVida");
        ModeloPersonaje instance = null;
        instance.AumentarVida();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PerderVida method, of class ModeloPersonaje.
     */
    @Test
    public void testPerderVida() {
        System.out.println("PerderVida");
        ModeloPersonaje instance = null;
        instance.PerderVida();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCantidadJoyasPorNivel method, of class ModeloPersonaje.
     */
    @Test
    public void testGetCantidadJoyasPorNivel() {
        System.out.println("getCantidadJoyasPorNivel");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getCantidadJoyasPorNivel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContadorJoyas method, of class ModeloPersonaje.
     */
    @Test
    public void testGetContadorJoyas() {
        System.out.println("getContadorJoyas");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getContadorJoyas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContadorVidas method, of class ModeloPersonaje.
     */
    @Test
    public void testGetContadorVidas() {
        System.out.println("getContadorVidas");
        ModeloPersonaje instance = null;
        int expResult = 0;
        int result = instance.getContadorVidas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCantidadJoyasPorNivel method, of class ModeloPersonaje.
     */
    @Test
    public void testSetCantidadJoyasPorNivel() {
        System.out.println("setCantidadJoyasPorNivel");
        int CantidadJoyasPorNivel = 0;
        ModeloPersonaje instance = null;
        instance.setCantidadJoyasPorNivel(CantidadJoyasPorNivel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombreUsuario method, of class ModeloPersonaje.
     */
    @Test
    public void testGetNombreUsuario() {
        System.out.println("getNombreUsuario");
        ModeloPersonaje instance = null;
        String expResult = "";
        String result = instance.getNombreUsuario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreUsuario method, of class ModeloPersonaje.
     */
    @Test
    public void testSetNombreUsuario() {
        System.out.println("setNombreUsuario");
        String NombreUsuario = "";
        ModeloPersonaje instance = null;
        instance.setNombreUsuario(NombreUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}