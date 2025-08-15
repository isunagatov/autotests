package tests.petclinic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RestApiJMix;
import utils.TestData;

import java.util.UUID;

public class RestApiTests {
    @Test
    public static void getTokenJ() throws Exception {
        String token = utils.RestApiJMix.getTokenJMix();
        Assert.assertTrue(token.length()>5, "Токен получен");
    }
    @Test
    public static void getUsers() throws Exception {
        SoftAssert sa = new SoftAssert();
        JSONArray jsa = utils.RestApiJMix.getUsers();
        sa.assertTrue(jsa.length()>1, "Получено пользователей больше 1го: " + jsa.length() + ".");
        JSONObject jso1 =  RestApiJMix.getObjectFromArray(0, jsa);
        System.out.println("First user: " + jso1.get("username"));
        sa.assertAll();
    }
}
