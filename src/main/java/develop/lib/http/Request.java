package develop.lib.http;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KKK on 2017/9/4.
 */

public class Request {
    private String url;
    private String method = "GET";
    private List<String> pramsKey = new ArrayList<>();
    private List<Object> pramsValue = new ArrayList<>();

    public void addPrams(String key, Object value) {
        pramsKey.add(key);
        pramsValue.add(value);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        switch (method){
            case POST:
                this.method = "POST";
                break;
            case GET:
            default:
                this.method = "GET";
                break;
        }
    }

    public enum RequestMethod{
        GET,POST
    }

    public String getPrams(){
        String sPrams = "";
        for (int i = 0;i<pramsKey.size();i++){
            sPrams += pramsKey.get(i)+"="+pramsValue.get(i);
            if (i < pramsKey.size()-1){
                sPrams +="&";
            }
        }
        return sPrams;
    }
}
