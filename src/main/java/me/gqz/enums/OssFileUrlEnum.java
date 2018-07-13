package me.gqz.enums;

/**
 * <p>Title: OssFileUrlEnum. </p>
 * <p>Description OSS附件文件地址 </p>
 * @author dragon
 * @date 2018/7/13 下午2:52
 */
public enum OssFileUrlEnum {
    /**
     * 默认文件地址
     */
    IMAGE_URL("image", "图片地址");

    String url;
    String name;

    OssFileUrlEnum(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(String url) {
        for (OssFileUrlEnum ele : OssFileUrlEnum.values()) {
            if (url.equals(ele.getName())) {
                return ele.getName();
            }
        }
        return null;
    }

    public static OssFileUrlEnum getEnum(String url) {
        for (OssFileUrlEnum ele : OssFileUrlEnum.values()) {
            if (url.equals(ele.getName())) {
                return ele;
            }
        }
        return null;
    }
}
