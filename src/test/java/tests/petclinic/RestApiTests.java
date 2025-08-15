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

}
