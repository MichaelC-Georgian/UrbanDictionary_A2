/*
 * Michael Caldwell, 200445010, 2020/12/08
 * Adv Java (Section 1) - Assignment 2
 */

package Utilities;

/*
 * This class is designed to call the urban dictionary API and return various definitions
 */

import Models.UrbanDictionaryResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {
    /**
     * This method will receive a search string and send that to the Urban Dictionary API
     * to receive a JSON file. The file will be written to definitionInfo.json
     * @param searchText The text or term that is being searched in the api
     */
    public static UrbanDictionaryResponse callUrbanDictionary(String searchText) throws IOException, InterruptedException {
        //Where to store results locally
        String jsonLocation = "src/Utilities/definitionInfo.json";

        //Remove leading and trailing spaces, replace the rest of them with %20
        searchText = searchText.trim().replaceAll(" ", "%20");

        //Calls the urban dictionary API with the entered search text
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=" + searchText))
                .header("x-rapidapi-key", "e8fef65828msh86ab5a0fa6beb1cp115847jsn2d104024ef59")
                .header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        //Saves the response to the path location
        HttpResponse<Path> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonLocation)));

        //Returns the json path location to wherever the method was called from
        return getDefinitionsFromJSON(new File(jsonLocation));
    }


    /**
     * This method receives a JSON file and extracts definition object data out of it
     * in a UrbanDictionaryResponse object
     * @param jsonFile this file must adhere to UrbanDictionary standard response
     * @return the result of the search
     */
    public static UrbanDictionaryResponse getDefinitionsFromJSON(File jsonFile)
    {
        Gson gson = new Gson();
        UrbanDictionaryResponse searchResult = null;
        //Using try with resources
        try
                (
                        FileReader fileReader = new FileReader(jsonFile);
                        JsonReader jsonReader = new JsonReader(fileReader)
                )
        {
            searchResult = gson.fromJson(jsonReader, UrbanDictionaryResponse.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return searchResult;
    }

}
