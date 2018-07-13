package me.gqz.enums;

/**
 * <p>Title: SerialNoEnum. </p>
 * <p>Description 附件枚举 </p>
 * @author dragon
 * @date 2018/7/13 下午3:22
 */
public enum SerialNoEnum {

    /**
     * 图片
     */
    GQZ_IMAGE("GQZ_IMAGE_", "附件流水号", 6);

    String type;
    String name;
    int length;

    SerialNoEnum(String type, String name, int length) {
        this.type = type;
        this.name = name;
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public static String getName(String type) {
        for (SerialNoEnum ele : SerialNoEnum.values()) {
            if (type.equals(ele.getType())) {
                return ele.getName();
            }
        }
        return null;
    }

    public static Integer getLength(String type) {
        for (SerialNoEnum ele : SerialNoEnum.values()) {
            if (type.equals(ele.getType())) {
                return ele.getLength();
            }
        }
        return null;
    }

    public static SerialNoEnum getEnum(String type) {
        for (SerialNoEnum ele : SerialNoEnum.values()) {
            if (type.equals(ele.getType())) {
                return ele;
            }
        }
        return null;
    }
}
