package me.gqz.model.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class TencentWsPlaynum implements Serializable {

    private static final long serialVersionUID = -7299086559904487040L;

    private String playnum;

    private String _idc;
}
