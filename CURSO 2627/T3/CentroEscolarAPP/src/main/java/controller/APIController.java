package controller;

import com.google.gson.Gson;
import model.APIResponse;
import model.StudentAPI;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class APIController {

    private final String URL = "https://randomuser.me/api/?results=";

    public List<StudentAPI> getAllStudents(int number) {

        Gson gson = new Gson();
        List<StudentAPI> studentAPIList = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(URL+number)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            APIResponse apiResponse = gson.fromJson(jsonObject.toString(), APIResponse.class);
            return apiResponse.getResults();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return studentAPIList;
    }
}
