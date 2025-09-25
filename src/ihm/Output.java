package ihm;

import java.util.List;

public class Output {
    private boolean status;
    private String message;
    private Object obj;

    public Output(boolean success, String message, Object obj) {
        this.status = success;
        this.message = message;
        this.obj = obj;
    }

    public boolean istrue() { return status; }
    public String getMessage() {
        return message;
    }
    public Object getObj() {
        return obj;
    }
    public void showoutput(){
        if(obj==null){
            System.out.println(message);
        }
        else{
            if(obj instanceof List<?>){
                List list = (List) obj;
                for(Object o : list){
                    System.out.println(o);
                }
            }
            else{
                System.out.println(obj);
            }
        }
    }
}
