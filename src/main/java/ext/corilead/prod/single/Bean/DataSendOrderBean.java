package ext.corilead.prod.single.Bean;

import java.util.List;
import java.util.Map;

public class DataSendOrderBean {
    private String name;//投产单名称
    private String sendtype;//投产单类型
    private String describe;//投产单单描述
    private String sender;
    private List<Map<String,Object>> sendObjects;//发送内容
    @Override
    public String toString() {
        return "DataSendOrderBean [name=" + name + ", sendtype=" + sendtype + ", describe=" + describe + ", sender="
                + sender + ", sendObjects=" + sendObjects + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSendtype() {
        return sendtype;
    }
    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public List<Map<String, Object>> getSendObjects() {
        return sendObjects;
    }
    public void setSendObjects(List<Map<String, Object>> sendObjects) {
        this.sendObjects = sendObjects;
    }

}
