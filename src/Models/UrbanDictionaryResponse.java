package Models;

/***
 * A class to store a list of Definition objects, nothing to exciting here
 */
public class UrbanDictionaryResponse {

    private Definition[] list;

    public UrbanDictionaryResponse(Definition[] list) {
        setList(list);
    }

    public Definition[] getList() {
        return list;
    }

    public void setList(Definition[] list) {
        this.list = list;
    }
}