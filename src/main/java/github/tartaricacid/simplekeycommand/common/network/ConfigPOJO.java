package github.tartaricacid.simplekeycommand.common.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfigPOJO {

    @SerializedName("pos")
    private List<Double> pos;

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("command")
    private String command;

    @SerializedName("desc")
    private String desc;

    public void setPos(List<Double> pos) {
        this.pos = pos;
    }

    public List<Double> getPos() {
        return pos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}