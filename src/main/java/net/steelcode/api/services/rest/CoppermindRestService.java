package net.steelcode.api.services.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CoppermindRestService {

    public HashMap<String, String> searchForTag(String tag) {

        HashMap<String, String> result = new HashMap<>();

        String page;
        String link;

        RestTemplate restClient = new RestTemplate();
        ArrayList<ArrayList<String>> queryResult = restClient.getForObject("https://es.coppermind.net/w/api.php?action=opensearch&limit=1&format=json&search=" + tag, ArrayList.class);

        try {
            if (queryResult != null) {
                if (queryResult.get(3) != null) {
                    if (queryResult.get(3).get(0) != null) {
                        link = queryResult.get(3).get(0);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

                if (queryResult.get(1) != null) {
                    if (queryResult.get(1).get(0) != null) {
                        page = queryResult.get(1).get(0);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }

            result.put("link", link);
            result.put("page", page);

            return result;

        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @SuppressWarnings("raw")
    public String getDataFromCoppermind(String page) {


        RestTemplate restClient = new RestTemplate();
        HashMap queryResult = restClient.getForObject("https://es.coppermind.net/w/api.php?action=query&prop=extracts&exsentences=8&titles=Shallan Davar&format=json", HashMap.class);

        if (queryResult == null) {
            return null;
        }

        HashMap textResult = restClient.getForObject("https://es.coppermind.net/w/api.php?action=query&prop=extracts&exsentences=8&format=json&explaintext=1&formatversion=2&titles=" + page, HashMap.class);

        String text = getTextFromHashMap(textResult);
        if (hasQuotes(queryResult)) {
            if (text.split("\\n”\\n").length > 2) {
                text = text.split("\\n”\\n")[1];
            }
        }

        return text;
    }

    @SuppressWarnings("raw")
    private boolean hasQuotes(HashMap content) {
        HashMap<String, HashMap> firstLevel = (HashMap<String, HashMap>) content.get("query");
        HashMap<String, HashMap> secondLevel = (HashMap<String, HashMap>) firstLevel.get("pages");

        HashMap<String, String> thirdLevel = secondLevel.values().stream().toList().get(0);

        return thirdLevel.get("extract").contains("blockquote");

    }

    @SuppressWarnings("raw")
    private String getTextFromHashMap(HashMap content) {
        HashMap<String, HashMap> firstLevel = (HashMap<String, HashMap>) content.get("query");
        List<HashMap<String, String>> secondLevel = (List<HashMap<String, String>>) firstLevel.get("pages");

        HashMap<String, String> thirdLevel = secondLevel.get(0);

        return thirdLevel.get("extract");

    }
}
