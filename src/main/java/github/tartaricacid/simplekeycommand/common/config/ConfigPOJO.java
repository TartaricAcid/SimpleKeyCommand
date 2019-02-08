package github.tartaricacid.simplekeycommand.common.config;

import com.google.gson.annotations.SerializedName;
import github.tartaricacid.simplekeycommand.SimpleKeyCommand;

import java.util.List;

public class ConfigPOJO implements Cloneable {
    @SerializedName("name")
    private String name;

    @SerializedName("command")
    private String command;

    @SerializedName("desc")
    private String desc;

    @SerializedName("text")
    private Text text;

    @SerializedName("sound")
    private Sound sound;

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 实现对象的潜复制功能
     *
     * @return 潜复制对象
     */
    @Override
    public ConfigPOJO clone() {
        ConfigPOJO configPOJO = null;
        try {
            configPOJO = (ConfigPOJO) super.clone();
        } catch (CloneNotSupportedException cnse) {
            SimpleKeyCommand.logger.warn("Object Clone Failed: ", cnse);
        }
        return configPOJO;
    }

    public class Sound {
        @SerializedName("name")
        private String name;

        @SerializedName("volume")
        private float volume;

        @SerializedName("pitch")
        private float pitch;

        public float getVolume() {
            return volume;
        }

        public void setVolume(float volume) {
            this.volume = volume;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPitch() {
            return pitch;
        }

        public void setPitch(float pitch) {
            this.pitch = pitch;
        }
    }

    public class Text {
        @SerializedName("show")
        private boolean show;

        @SerializedName("pos")
        private List<Double> pos;

        public List<Double> getPos() {
            return pos;
        }

        public void setPos(List<Double> pos) {
            this.pos = pos;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }
    }
}