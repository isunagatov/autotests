package utils;

import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class RestApiJMix {
    @Step("getToken. Получение токена")
    public static String getTokenJMix() throws Exception {
        ReadConfig.SubSystemClass.SubSystem system = ReadConfig.SubSystemClass.getSubSystem();
        String url = system.getUrl() +  "/oauth2" + "/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String encoding = Base64.getEncoder().encodeToString((system.getClient() + ":" + system.getSecret()).getBytes());
        headers.set("Authorization", "Basic " + encoding);
        headers.set("Content-type", "application/x-www-form-urlencoded");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        HttpEntity requestEntity = new HttpEntity<>(body, headers);

        String serverUrl = url;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        System.out.println("Response code: " + response.getStatusCode());

        String jsonStr = response.getBody();
        String tokenStr = "";
        try {
            JSONObject json = new JSONObject(jsonStr);
            tokenStr = (String) json.get("access_token");
            Assert.assertTrue(tokenStr.isEmpty()==false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tokenStr;
    }
    @Step("getUsers. Получение списка пользователей")
    public static JSONArray getUsers() throws Exception{
        ReadConfig.SubSystemClass.SubSystem system = ReadConfig.SubSystemClass.getSubSystem();
        String source1 = system.getUrl() + "/rest/entities/User";

        HttpClient client;

            client = HttpClients.createDefault();


        HttpGet get = new HttpGet(source1);
        get.setHeader("Authorization", "Bearer " + getTokenJMix());
        get.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(get);
        System.out.println("\nSending 'Get' request to URL /users: " + source1);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);
        String ResultRunID = result.toString();
        JSONArray jsa = new JSONArray(ResultRunID);

        return jsa;
    }
    public static JSONObject getObjectFromArray(int index, JSONArray array){
        JSONObject jso = new JSONObject();
        if(array.length()>0) {
            JSONObject jso1;
            jso1 = array.getJSONObject(index);
            return jso1;
        }else return jso;
    }
}
